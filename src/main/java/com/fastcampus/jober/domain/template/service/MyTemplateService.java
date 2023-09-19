package com.fastcampus.jober.domain.template.service;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.template.domain.MyTemplate;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.dto.TemplateResponseDto;
import com.fastcampus.jober.domain.template.dto.TemplateResponseDto.ListDto;
import com.fastcampus.jober.domain.template.repository.MyTemplateRepository;
import com.fastcampus.jober.domain.template.repository.TemplateRepository;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.TemplateException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyTemplateService {

    private final MyTemplateRepository myTemplateRepository;
    private final TemplateRepository templateRepository;

    public Page<TemplateResponseDto.ListDto> findMyTemplates(int page, int size, Member member) {
        if (page < 0) {
            throw new TemplateException(ErrorCode.PAGE_BAD_REQUEST);
        }
        
        Pageable pageable = PageRequest.of(page, size);

        Page<Template> templatePage = myTemplateRepository.findTemplatesByMember(member, pageable);

        Page<ListDto> listDtos = templatePage.map(m -> ListDto.builder()
            .id(m.getId())
            .title(m.getTitle())
            .description(m.getDescription())
            .hashtags(m.getHashtags())
            .thumbnailUrl(m.getThumbnailImageUrl()).build()
        );

        return listDtos;
    }

    @Transactional
    public void removeMyTemplate(Member member, Long templateId) {
        Template template = templateRepository.findById(templateId)
            .orElseThrow(() -> new TemplateException(
                ErrorCode.TEMPLATE_NOT_FOUND));

        List<Template> templates = new ArrayList<>();
        templates.add(template);

        MyTemplate myTemplate = myTemplateRepository.findByMemberAndTemplatesIn(member, templates)
            .orElseThrow();

        myTemplateRepository.delete(myTemplate);
    }
}

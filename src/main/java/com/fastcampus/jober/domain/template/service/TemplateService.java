package com.fastcampus.jober.domain.template.service;

import com.fastcampus.jober.domain.member.Member;
import com.fastcampus.jober.domain.template.domain.MyTemplate;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.dto.TemplateResponseDto.ListDto;
import com.fastcampus.jober.domain.template.repository.MyTemplateRepository;
import com.fastcampus.jober.domain.template.repository.TemplateRepository;
import com.fastcampus.jober.domain.template.repository.TemplateTypeRepository;
import com.fastcampus.jober.global.constant.TemplateCategory;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateTypeRepository templateTypeRepository;
    private final MyTemplateRepository myTemplateRepository;

    public Page<ListDto> findTemplates(String type, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Template> templatePage = findTemplatePage(type, keyword, pageable);

        Page<ListDto> listDtos = templatePage.map(m -> ListDto.builder()
            .id(m.getId())
            .title(m.getTitle())
            .description(m.getDescription())
            .hashtags(m.getHashtags())
            .thumbnailUrl(m.getThumbnailImageUrl()).build()
        );

        return listDtos;
    }

    public int addTemplate(Member member, Long templateId) {
        Template template = templateRepository.findById(templateId).orElseThrow();

        List<Template> templates = new ArrayList<>();
        templates.add(template);

        MyTemplate myTemplate = MyTemplate.builder().member(member).templates(templates).build();

        myTemplateRepository.save(myTemplate);

        return 1;
    }

    private Page<Template> findTemplatePage(String type, String keyword, Pageable pageable) {

        if (StringUtils.isBlank(type) && StringUtils.isBlank(keyword)) {
            return templateRepository.findAll(pageable);
        }

        if (StringUtils.isBlank(type) && !StringUtils.isBlank(keyword)) {
            return templateRepository.findAllByKeywordContains(keyword, pageable);
        }

        TemplateCategory templateCategory = TemplateCategory.findByName(type);

        if (!StringUtils.isBlank(type) && StringUtils.isBlank(keyword)) {
            return templateTypeRepository.findByTemplateCategory(templateCategory, pageable);
        }

        return templateTypeRepository.findAllByKeywordAndType(keyword, templateCategory,
            pageable);
    }
}

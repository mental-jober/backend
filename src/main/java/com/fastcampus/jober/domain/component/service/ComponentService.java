package com.fastcampus.jober.domain.component.service;

import com.fastcampus.jober.domain.component.domain.Component;
import com.fastcampus.jober.domain.component.dto.ComponentRequest;
import com.fastcampus.jober.domain.component.dto.ComponentResponse;
import com.fastcampus.jober.domain.component.repository.ComponentRepository;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallTempRepository;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.repository.TemplateRepository;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.TemplateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {

    private final SpaceWallRepository spaceWallRepository;
    private final ComponentRepository componentRepository;


    /**
     * 공유페이지 조회시 컴포넌트 리스트를 반환
     * todo builder들 dto에 클래스화 시키기.
     * @param spaceWall
     * @return List<ComponentResponse.ComponentResponseDTO>
     *
     */
    @Transactional
    public List<ComponentResponse.ComponentResponseDTO> findComponentListByspaceWallId(SpaceWall spaceWall) {
        if (spaceWall == null) {
            throw new TemplateException(ErrorCode.SPACEWALL_NOT_FOUND);
        }
        List<Component> componentList = componentRepository.findComponentBySpaceWall(spaceWall);
        List<ComponentResponse.ComponentResponseDTO> responseDTOList = new ArrayList<ComponentResponse.ComponentResponseDTO>();

        for (int i = 0; i < componentList.size(); i++) {
            Component component = componentList.get(i);
            if (component.getType().equals("page") && component.getChildSpaceWall() != null) {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .spaceWallId(component.getSpaceWall().getId())
                    .childSpaceWallId(component.getChildSpaceWall().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getChildSpaceWall().getTitle())
                    .content(component.getChildSpaceWall().getDescription())
                    .sequence(component.getSequence())
                    .createdAt(component.getCreatedAt())
                    .updatedAt(component.getUpdatedAt())
                    .build());

            }
            else if (component.getType().equals("template") && component.getTemplate() != null) {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .spaceWallId(component.getSpaceWall().getId())
                    .templateId(component.getTemplate().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getTemplate().getTitle())
                    .content(component.getTemplate().getDescription())
                    .sequence(component.getSequence())
                    .createdAt(component.getCreatedAt())
                    .updatedAt(component.getUpdatedAt())
                    .build());
            }
            else {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .spaceWallId(component.getSpaceWall().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getTitle())
                    .content(component.getContent())
                    .sequence(component.getSequence())
                    .createdAt(component.getCreatedAt())
                    .updatedAt(component.getUpdatedAt())
                    .build());
            }
        }
        return responseDTOList;
    }


}

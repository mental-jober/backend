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
     * @param spaceWallId
     * @return List<ComponentResponse.ComponentResponseDTO>
     *
     */
    @Transactional
    public List<ComponentResponse.ComponentResponseDTO> findComponentListByspaceWallId(Long spaceWallId) {
        // TODO: 2023/09/28 성욱님에게 SpaceWall 객체 바로 받을수 있는지 물어보기.
        Optional<SpaceWall> spaceWall = spaceWallRepository.findById(spaceWallId);
        List<Component> componentList = componentRepository.findComponentBySpaceWall(spaceWall.get());
        List<ComponentResponse.ComponentResponseDTO> responseDTOList = new ArrayList<ComponentResponse.ComponentResponseDTO>();

        for (int i = 0; i < componentList.size(); i++) {
            Component component = componentList.get(i);
            if (component.getType().equals("page")) {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .space_wall_id(component.getSpaceWall().getId())
                    .child_space_wall_id(component.getChildSpaceWall().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getChildSpaceWall().getTitle())
                    .content(component.getChildSpaceWall().getDescription())
                    .sequence(component.getSequence())
                    .created_at(component.getCreatedAt())
                    .updated_at(component.getUpdatedAt())
                    .build());

            }
            else if (component.getType().equals("template")) {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .space_wall_id(component.getSpaceWall().getId())
                    .template_id(component.getTemplate().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getTemplate().getTitle())
                    .content(component.getTemplate().getDescription())
                    .sequence(component.getSequence())
                    .created_at(component.getCreatedAt())
                    .updated_at(component.getUpdatedAt())
                    .build());
            }
            else {
                responseDTOList.add(ComponentResponse.ComponentResponseDTO.builder()
                    .id(component.getId())
                    .space_wall_id(component.getSpaceWall().getId())
                    .type(component.getType())
                    .visible(component.isVisible())
                    .title(component.getTitle())
                    .content(component.getContent())
                    .sequence(component.getSequence())
                    .created_at(component.getCreatedAt())
                    .updated_at(component.getUpdatedAt())
                    .build());
            }
        }
        return responseDTOList;
    }


}

package com.fastcampus.jober.domain.spacewalltemp.service;

import com.fastcampus.jober.domain.component.domain.Component;
import com.fastcampus.jober.domain.component.repository.ComponentRepository;
import com.fastcampus.jober.domain.componentTemp.domain.ComponentTemp;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempRequest.ModifyDTOInSWT;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempResponse;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempResponse.ComponentTempResponseDTO;
import com.fastcampus.jober.domain.componentTemp.repository.ComponentTempRepository;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.domain.spacewalltemp.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.spacewalltemp.dto.SpaceWallTempRequest;
import com.fastcampus.jober.domain.spacewalltemp.dto.SpaceWallTempRequest.ModifyDTO;
import com.fastcampus.jober.domain.spacewalltemp.dto.SpaceWallTempResponse.SpaceWallTempResponseDTO;
import com.fastcampus.jober.domain.spacewalltemp.repository.SpaceWallTempRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallTempService {

    private final SpaceWallTempRepository spaceWallTempRepository;
    private final SpaceWallRepository spaceWallRepository;
    private final ComponentRepository componentRepository;
    private final ComponentTempRepository componentTempRepository;


    @Transactional
    public boolean checkSpaceWallTempExists(Long spaceWallId) {
        return spaceWallTempRepository.existsSpaceWallTempBySpaceWall(spaceWallId);
    }


    @Transactional
    public SpaceWallTempResponseDTO addSpaceWallTemp(Long spaceWallId) {
        SpaceWall spaceWall = spaceWallRepository.findById(spaceWallId).get();

        SpaceWallTemp spaceWallTemp = SpaceWallTemp.builder()
            .spaceWall(spaceWall)
            .title(spaceWall.getTitle())
            .description(spaceWall.getDescription())
            .profileImageUrl(spaceWall.getProfileImageUrl())
            .backgroundImageUrl(spaceWall.getBackgroundImageUrl())
            .sequence(spaceWall.getSequence())
            .build();

        SpaceWallTemp savedSWT = spaceWallTempRepository.save(spaceWallTemp);

        List<Component> componentList = componentRepository.findComponentBySpaceWall(spaceWall);

        for (int i = 0; i < componentList.size(); i++) {
            Component component = componentList.get(i);
            ComponentTemp componentTemp = ComponentTemp.builder()
                .parentSpaceWallTemp(savedSWT)
                .thisSpaceWall(component.getThisSpaceWall())
                .template(component.getTemplate())
                .componentId(component.getId())
                .type(component.getType())
                .visible(component.isVisible())
                .title(component.getTitle())
                .content(component.getContent())
                .sequence(component.getSequence())
                .build();

            componentTempRepository.save(componentTemp);
        }
        return findSpaceWallTemp(spaceWallId);
    }

    @Transactional
    public SpaceWallTempResponseDTO findSpaceWallTemp(Long spaceWallId) {
        SpaceWallTemp spaceWallTemp = spaceWallTempRepository.findSpaceWallTempBySpaceWallId(
            spaceWallId);

        List<ComponentTemp> componentTempList = componentTempRepository.findComponentTempBySpaceWallTemp(
            spaceWallTemp);

        return SpaceWallTempResponseDTO.builder()
            .spaceWallTempId(spaceWallTemp.getId())
            .spaceWallId(spaceWallTemp.getSpaceWall().getId())
            .title(spaceWallTemp.getTitle())
            .description(spaceWallTemp.getDescription())
            .profileImageUrl(spaceWallTemp.getProfileImageUrl())
            .backgroundImageUrl(spaceWallTemp.getBackgroundImageUrl())
            .sequence(spaceWallTemp.getSequence())
            .createdAt(spaceWallTemp.getCreatedAt())
            .updateddAt(spaceWallTemp.getUpdatedAt())
            .componentTempList(ComponentTempResponse.ComponentTempResponseDTO.listToDTO(componentTempList))
            .build();
    }


    @Transactional
    public SpaceWallTempResponseDTO modifySpaceWallTemp(Long spaceWallId, ModifyDTO modifyDTO) {

//
       SpaceWallTemp spaceWallTemp  = spaceWallTempRepository.findById(modifyDTO.getSpaceWallTempId()).get();

        spaceWallTemp.update(modifyDTO);
        
        List<ModifyDTOInSWT> componentTempList = modifyDTO.getComponentTempList();

        for (int i = 0; i < componentTempList.size(); i++) {
            ModifyDTOInSWT modifyDTOInSWT = componentTempList.get(i);
            ComponentTemp componentTemp = componentTempRepository.findById(
                modifyDTOInSWT.getComponentTempId()).get();

            if (modifyDTOInSWT.isDeleted()) {
                componentTempRepository.delete(componentTemp);
            } else {
                componentTemp.updateInSWT(modifyDTOInSWT);
            }
        }

        return findSpaceWallTemp(spaceWallId);
    }

}

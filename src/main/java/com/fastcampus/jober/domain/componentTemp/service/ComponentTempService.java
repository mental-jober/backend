package com.fastcampus.jober.domain.componentTemp.service;

import com.fastcampus.jober.domain.component.domain.Component;
import com.fastcampus.jober.domain.component.repository.ComponentRepository;
import com.fastcampus.jober.domain.componentTemp.domain.ComponentTemp;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempRequest;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempResponse;
import com.fastcampus.jober.domain.componentTemp.dto.ComponentTempResponse.ComponentTempResponseDTO;
import com.fastcampus.jober.domain.componentTemp.repository.ComponentTempRepository;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallTempRepository;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.repository.TemplateRepository;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.ComponentTempException;
import com.fastcampus.jober.global.error.exception.TemplateException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentTempService {

    private final ComponentTempRepository componentTempRepository;
    private final SpaceWallTempRepository spaceWallTempRepository;
    private final TemplateRepository templateRepository;

    @Transactional
    public ComponentTempResponseDTO addComponentTemp(
        ComponentTempRequest.ComponentTempRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new ComponentTempException(ErrorCode.DTO_NOT_EXISTS);
        }
        String type = requestDTO.getType();
        if (!(type.equals("text") || type.equals("line") || type.equals("link") || type.equals(
            "template") || type.equals("page"))) {
            throw new ComponentTempException(ErrorCode.INVALID_COMPONENT_TYPE);
        }



        SpaceWallTemp spaceWallTemp = spaceWallTempRepository.findById(requestDTO.getSpaceWallTempId()).get(); // 예외처리



        ComponentTemp componentTemp = ComponentTemp.builder()
            .spaceWallTemp(spaceWallTemp)
            .type(requestDTO.getType())
            .visible(false)
            .sequence(requestDTO.getSequence())
            .deleted(false)
            .build();

        ComponentTemp addedcomponentTemp = componentTempRepository.save(componentTemp); // 예외처리

        ComponentTempResponseDTO componentTempResponseDTO = ComponentTempResponseDTO.toDTO(addedcomponentTemp);

        return componentTempResponseDTO;
    }

    @Transactional
    public ComponentTempResponseDTO modifyComponentTemp(
        ComponentTempRequest.ComponentTempRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new ComponentTempException(ErrorCode.DTO_NOT_EXISTS);
        }

        ComponentTemp componentTemp = componentTempRepository.findById(requestDTO.getId()).get();


        String type = requestDTO.getType();
        if (type.equals("page")) {
            SpaceWallTemp childSpaceWallTemp = spaceWallTempRepository.findById(
                requestDTO.getChildSpaceWallTempId()).get();

            componentTemp.setChildSpaceWallTemp(childSpaceWallTemp);
            return ComponentTempResponseDTO.toDTOPageType(componentTemp);
        } else if (type.equals("template")) {
            Template template = templateRepository.findById(requestDTO.getTemplateId()).get();

            componentTemp.setTemplate(template);
            return ComponentTempResponseDTO.toDTOTemplateType(componentTemp);
        } else {
            componentTemp.setTitle(requestDTO.getTitle());
            componentTemp.setContent(requestDTO.getContent());
            return ComponentTempResponseDTO.toDTO(componentTemp);
        }

    }

    @Transactional(readOnly = true)
    public boolean checkComponentTempExists(Long id) {
        return componentTempRepository.existsComponentTempById(id);
    }

}

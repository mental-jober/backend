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

    @Transactional
    public ComponentTempResponseDTO addComponentTemp(
        ComponentTempRequest.ComponentTempRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new ComponentTempException(ErrorCode.SPACEWALLTEMP_NOT_FOUND);
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

}

package com.fastcampus.jober.domain.space.component.componentTemp.controller;

import com.fastcampus.jober.domain.space.component.componentTemp.dto.ComponentTempRequest.AddDTO;
import com.fastcampus.jober.domain.space.component.componentTemp.dto.ComponentTempRequest.ModifyDTO;
import com.fastcampus.jober.domain.space.component.componentTemp.dto.ComponentTempResponse.ComponentTempResponseDTO;
import com.fastcampus.jober.domain.space.component.componentTemp.service.ComponentTempService;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.ComponentTempException;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/componentTemps")
public class ComponentTempController {

    private final ComponentTempService componentTempService;

    /**
     * 임시 컴포넌트의 생성
     * todo 예외처리, 트랜잭션 관리
     *
     * @param addDTO
     * @return ComponentTempResponseDTO
     */
    @PostMapping("/new/{spaceWallId}")
    public ResponseEntity<ResponseDTO<ComponentTempResponseDTO>> componentTempAdd(
        @PathVariable("spaceWallId") Long spaceWallId,
        @RequestBody AddDTO addDTO) {

        ComponentTempResponseDTO componentTempResponseDTO = componentTempService.addComponentTemp(
            addDTO);

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.CREATED, "임시 컴포넌트가 추가 되었습니다.", componentTempResponseDTO),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/edit/{spaceWallId}")
    public ResponseEntity<ResponseDTO<ComponentTempResponseDTO>> componentTempModify(
        @PathVariable("spaceWallId") Long spaceWallId,
        @RequestBody ModifyDTO modifyDTO) {

        if (!componentTempService.checkComponentTempExists(modifyDTO.getComponentTempId())) {
            throw new ComponentTempException(ErrorCode.INVALID_COMPONENTTEMPID);
        }
        ComponentTempResponseDTO componentTempResponseDTO = componentTempService.modifyComponentTemp(
            modifyDTO);

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.OK, "임시 컴포넌트가 수정 되었습니다.", componentTempResponseDTO),
            HttpStatus.OK
        );
    }

    @GetMapping("/view/{spaceWallId}")
    public ResponseEntity<ResponseDTO<ComponentTempResponseDTO>> componentTempDetails(
        @PathVariable("componentTempId") Long componentTempId,
        @RequestBody ModifyDTO modifyDTO) {

        if (!componentTempService.checkComponentTempExists(componentTempId)) {
            throw new ComponentTempException(ErrorCode.INVALID_COMPONENTTEMPID);
        }

        ComponentTempResponseDTO componentTempResponseDTO = componentTempService.findComponentTemp(
            modifyDTO.getComponentTempId());

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.OK, "임시 컴포넌트가 조회 되었습니다.", componentTempResponseDTO),
            HttpStatus.OK
        );
    }
}

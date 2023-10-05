package com.fastcampus.jober.domain.space.spacewall.spacewalltemp.controller;

import com.fastcampus.jober.domain.space.spacewall.dto.SpaceWallResponse.ResponseDto;
import com.fastcampus.jober.domain.space.spacewall.service.SpaceWallService;
import com.fastcampus.jober.domain.space.spacewall.spacewalltemp.dto.SpaceWallTempRequest.ModifyDTO;
import com.fastcampus.jober.domain.space.spacewall.spacewalltemp.dto.SpaceWallTempResponse.SpaceWallTempResponseDTO;
import com.fastcampus.jober.domain.space.spacewall.spacewalltemp.service.SpaceWallTempService;
import com.fastcampus.jober.global.security.auth.session.MemberDetails;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.SpaceWallException;
import com.fastcampus.jober.global.error.exception.SpaceWallTempException;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaceTemps")
@Slf4j
public class SpaceWallTempController {

    private final SpaceWallTempService spaceWallTempService;
    private final SpaceWallService spaceWallService;

    @PostMapping("/new/{spaceWallId}")
    public ResponseEntity<ResponseDTO<SpaceWallTempResponseDTO>> spaceWallTempAdd(
        @PathVariable Long spaceWallId) {

        if (!spaceWallService.checkSpaceWallIdExists(spaceWallId)) {
            System.out.println("spaceWall은 실존한다! ID" + spaceWallId);
            throw new SpaceWallException(ErrorCode.INVALID_SPACEWALLID);
        }
        if (spaceWallTempService.checkSpaceWallTempExists(spaceWallId)) {
            throw new SpaceWallTempException(ErrorCode.ALREADY_EXSIST_SPACEWALLTEMP);
        }

        SpaceWallTempResponseDTO responseDTO = spaceWallTempService.addSpaceWallTemp(spaceWallId);

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.CREATED, "편집 페이지에 진입했습니다.", responseDTO),
            HttpStatus.CREATED);
    }

    @GetMapping("/view/{spaceWallId}")
    public ResponseEntity<ResponseDTO<SpaceWallTempResponseDTO>> spaceWallTempFind(
        @PathVariable Long spaceWallId) {

        if (!spaceWallTempService.checkSpaceWallTempExists(spaceWallId)) {
            throw new SpaceWallTempException(ErrorCode.NOT_EXSIST_SPACEWALLTEMP);
        }

        SpaceWallTempResponseDTO responseDTO = spaceWallTempService.findSpaceWallTemp(spaceWallId);
        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.OK, "편집 중인 페이지를 조회했습니다.", responseDTO),
            HttpStatus.OK);
    }

    @PutMapping("/save/{spaceWallId}")
    public ResponseEntity<ResponseDTO<SpaceWallTempResponseDTO>> spaceWallTempModify(
        @PathVariable Long spaceWallId,
        @RequestBody ModifyDTO modifyDTO) {

        if (!spaceWallTempService.checkSpaceWallTempExists(spaceWallId)) {
            throw new SpaceWallTempException(ErrorCode.NOT_EXSIST_SPACEWALLTEMP);
        }

        SpaceWallTempResponseDTO responseDTO = spaceWallTempService.modifySpaceWallTemp(spaceWallId,
            modifyDTO);

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.OK, "편집 중인 페이지를 임시저장했습니다.", responseDTO),
            HttpStatus.OK);
    }


    @PutMapping("/done/{spaceWallId}")
    public ResponseEntity<ResponseDTO<ResponseDto>> spaceWallTempDone(
        @PathVariable Long spaceWallId,
        @RequestBody ModifyDTO modifyDTO, @AuthenticationPrincipal
    MemberDetails memberDetails
    ) {
        if (!spaceWallTempService.checkSpaceWallTempExists(spaceWallId)) {
            throw new SpaceWallTempException(ErrorCode.NOT_EXSIST_SPACEWALLTEMP);
        }

        spaceWallTempService.modifySpaceWallTemp(spaceWallId, modifyDTO);

        ResponseDto responseDTO = spaceWallTempService.doneSpaceWallTemp(
            spaceWallId, memberDetails.getMemberId());

        return new ResponseEntity<>(
            new ResponseDTO<>(HttpStatus.OK, "편집 중인 페이지를 저장했습니다.", responseDTO),
            HttpStatus.OK);

    }


}

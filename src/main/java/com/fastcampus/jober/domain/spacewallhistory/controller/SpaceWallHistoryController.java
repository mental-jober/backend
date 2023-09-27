package com.fastcampus.jober.domain.spacewallhistory.controller;

import com.fastcampus.jober.domain.spacewall.service.SpaceWallService;
import com.fastcampus.jober.domain.spacewallhistory.service.SpaceWallHistoryService;
import com.fastcampus.jober.global.error.exception.SpaceWallNotFoundException;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fastcampus.jober.domain.spacewallhistory.dto.HistoryWrapper.HistoryRequestDTOWrapper;
import static com.fastcampus.jober.domain.spacewallhistory.dto.HistoryWrapper.HistoryResponseDTOWrapper;
import static com.fastcampus.jober.global.constant.ErrorCode.INVALID_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaces")
public class SpaceWallHistoryController {

    private final SpaceWallHistoryService spaceWallHistoryService;
    private final SpaceWallService spaceWallService;

    @PostMapping("/history/{spaceWallId}")
    public ResponseEntity<ResponseDTO<HistoryResponseDTOWrapper>> historyAdd(
            @RequestBody HistoryRequestDTOWrapper historyRequest,
            @PathVariable(name = "spaceWallId") Long spaceWallId
    ) {
        if (!spaceWallService.checkSpaceWallIdExists(spaceWallId))
            throw new SpaceWallNotFoundException(INVALID_REQUEST.getMessage());

        return ResponseEntity
                .ok(new ResponseDTO<>(
                        spaceWallHistoryService.addSpaceWallHistory(historyRequest),
                        "히스토리에 저장됩니다."
                ));
    }
}

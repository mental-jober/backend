package com.fastcampus.jober.domain.spacewall.controller;

import com.fastcampus.jober.domain.spacewall.dto.SpaceWallTempRequest;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallTempResponse;
import com.fastcampus.jober.domain.spacewall.service.SpaceWallTempService;
import com.fastcampus.jober.global.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaces/temp")
@RequiredArgsConstructor
public class SpaceWallTempController {

    private final SpaceWallTempService spaceWallTempService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTempSpaceWall(
            @RequestHeader(JwtTokenProvider.HEADER) String authorization,
            @RequestBody SpaceWallTempRequest.SaveDTO saveDTO) {
        String token = authorization.split(" ")[1];  // Bearer 제거
        spaceWallTempService.saveTemporarySpaceWall(token, saveDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listup")
    public ResponseEntity<SpaceWallTempResponse.LoadDTO> listupTempSpaceWall(
            @RequestHeader(JwtTokenProvider.HEADER) String authorization) {
        String token = authorization.split(" ")[1];
        return ResponseEntity.ok(spaceWallTempService.getTemporarySpaceWall(token));
    }

    // 삭제 로직도 일단 포함
    /*
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTempSpaceWall(
            @RequestHeader(JwtTokenProvider.HEADER) String authorization) {
        String token = authorization.split(" ")[1];
        spaceWallTempService.deleteTemporarySpaceWall(token);
        return ResponseEntity.ok().build();
    }
    */
}

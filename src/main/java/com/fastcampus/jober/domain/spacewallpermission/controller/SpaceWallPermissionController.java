package com.fastcampus.jober.domain.spacewallpermission.controller;

import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionRequest;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionResponse;
import com.fastcampus.jober.domain.spacewallpermission.service.SpaceWallPermissionService;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
public class SpaceWallPermissionController {

    private final SpaceWallPermissionService spaceWallPermissionService;

    @PutMapping("/{id}/authority")
    public ResponseEntity<ResponseDTO<SpaceWallPermissionResponse>> updatePermission(@PathVariable Long id, @RequestBody SpaceWallPermissionRequest requestDto) {
        SpaceWallPermissionResponse response = spaceWallPermissionService.updatePermission(id, requestDto);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "권한을 수정하였습니다.", response));
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<ResponseDTO<SpaceWallPermissionResponse>> moveSpaceWallPermission(@PathVariable Long id, @RequestBody SpaceWallPermissionRequest requestDto) {
        SpaceWallPermissionResponse response = spaceWallPermissionService.moveSpaceWallPermission(id, requestDto.getTargetSequence());
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "공유페이지의 위치를 수정하였습니다.", response));
    }
}

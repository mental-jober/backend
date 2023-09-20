package com.fastcampus.jober.domain.spacewall.controller;

import com.fastcampus.jober.domain.spacewall.dto.SpaceWallRequest;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse;
import com.fastcampus.jober.domain.spacewall.service.SpaceWallService;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fastcampus.jober.domain.spacewallpermission.service.SpaceWallPermissionService;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.ErrorCode;
import com.fastcampus.jober.global.error.exception.Exception403;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
public class SpaceWallController {

    private final SpaceWallService spaceWallService;
    private final SpaceWallPermissionService permissionService;

    @PostMapping
    public ResponseEntity<ResponseDTO<SpaceWallResponse.ResponseDto>> createSpaceWall(@RequestBody SpaceWallRequest.CreateDto createDto) {
        SpaceWallResponse.ResponseDto createdSpaceWallDto = spaceWallService.create(createDto);
        if (createdSpaceWallDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        permissionService.assignPermissionToSpaceWall(Auths.OWNER, createdSpaceWallDto.toEntity());
        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.CREATED, "생성되었습니다.", createdSpaceWallDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<SpaceWallResponse.ResponseDto>> getSpaceWall(@PathVariable Long id) {
        SpaceWallResponse.ResponseDto foundSpaceWallDto = spaceWallService.findById(id);

        SpaceWallPermission permission = permissionService.getPermissionForSpaceWall(foundSpaceWallDto.toEntity());
        if (permission == null || (permission.getAuths() != Auths.VIEWER && permission.getAuths() != Auths.EDITOR && permission.getAuths() != Auths.OWNER)) {
            throw new Exception403(ErrorCode.INVALID_ACCESS.getMessage());
        }

        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "완료되었습니다.", foundSpaceWallDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<SpaceWallResponse.ResponseDto>> updateSpaceWall(@PathVariable Long id, @RequestBody SpaceWallRequest.UpdateDto updateDto) {
        SpaceWallResponse.ResponseDto updatedSpaceWallDto = spaceWallService.update(id, updateDto);
        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK, "수정되었습니다.", updatedSpaceWallDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteSpaceWall(@PathVariable Long id) {
        spaceWallService.delete(id);
        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK, "공유페이지 " + id + " 삭제되었습니다.", "Success"), HttpStatus.OK);
    }
}

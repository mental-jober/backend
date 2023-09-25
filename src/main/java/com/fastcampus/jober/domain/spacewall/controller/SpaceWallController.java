package com.fastcampus.jober.domain.spacewall.controller;

import com.fastcampus.jober.domain.spacewall.dto.SpaceWallRequest;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse.SessionDTO;
import com.fastcampus.jober.domain.spacewall.service.SpaceWallService;
import com.fastcampus.jober.domain.spacewallpermission.service.SpaceWallPermissionService;
import com.fastcampus.jober.global.auth.session.MemberDetails;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
public class SpaceWallController {

    private final SpaceWallService spaceWallService;
    private final SpaceWallPermissionService permissionService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<SpaceWallResponse.ResponseDto>> getSpaceWall(
            @PathVariable Long id) {
        SpaceWallResponse.ResponseDto foundSpaceWallDto = spaceWallService.findById(id);

//        SpaceWallPermission permission = permissionService.getPermissionForSpaceWall(foundSpaceWallDto.toEntity());
//        if (permission == null || (permission.getAuths() != Auths.VIEWER && permission.getAuths() != Auths.EDITOR && permission.getAuths() != Auths.OWNER)) {
//            throw new Exception403(ErrorCode.INVALID_ACCESS.getMessage());
//        }

        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "완료되었습니다.", foundSpaceWallDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<SpaceWallResponse.ResponseDto>> updateSpaceWall(
            @PathVariable Long id, @RequestBody SpaceWallRequest.UpdateDto updateDto,
            HttpSession httpSession) {
        SpaceWallResponse.ResponseDto updatedSpaceWallDto = spaceWallService.update(id, updateDto,
                httpSession);
        return new ResponseEntity<>(
                new ResponseDTO<>(HttpStatus.OK, "수정되었습니다.", updatedSpaceWallDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteSpaceWall(@PathVariable Long id) {
        spaceWallService.delete(id);
        return new ResponseEntity<>(
                new ResponseDTO<>(HttpStatus.OK, "공유페이지 " + id + " 삭제되었습니다.", "Success"),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/check")
    public ResponseEntity<?> checkEditMode(@PathVariable Long id, HttpSession session,
                                           @AuthenticationPrincipal
                                           MemberDetails memberDetails) {
        SessionDTO sessionDTO = spaceWallService.checkEditSession(memberDetails.getMember().getId(),
                id, session);

        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK, "정상적으로 처리되었습니다.", sessionDTO),
                HttpStatus.OK);
    }
}
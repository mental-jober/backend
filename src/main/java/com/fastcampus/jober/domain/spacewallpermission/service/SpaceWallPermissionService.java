package com.fastcampus.jober.domain.spacewallpermission.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionRequestDto;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionResponseDto;
import com.fastcampus.jober.domain.spacewallpermission.repository.SpaceWallPermissionRepository;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallPermissionService {

    private final SpaceWallPermissionRepository spaceWallPermissionRepository;

    @Transactional
    public SpaceWallPermissionResponseDto updatePermission(Long id, SpaceWallPermissionRequestDto requestDto) {
        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        spaceWallPermission.setAuths(requestDto.getAuths());
        spaceWallPermission.setType(requestDto.getType());

        return new SpaceWallPermissionResponseDto(spaceWallPermission);
    }

    @Transactional
    public SpaceWallPermissionResponseDto moveSpaceWallPermission(Long id, Long parentId) {
        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        // 권한 검사
        if (parentId != null) {
            SpaceWallPermission parentPermission = spaceWallPermissionRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상위 공유페이스 권한 ID입니다."));

            if (parentPermission.getType() == Type.BLACK) {
                throw new IllegalArgumentException("목록 페이지로 이동할 수 없습니다..");
            }
        }

        spaceWallPermission.setParentId(parentId);

        return new SpaceWallPermissionResponseDto(spaceWallPermission);
    }

    @Transactional
    public void assignPermissionToSpaceWall(Auths auth, SpaceWall spaceWall) {
        SpaceWallPermission permission = new SpaceWallPermission();
        permission.setSpaceWall(spaceWall);
        permission.setAuths(auth);
        spaceWallPermissionRepository.save(permission);
    }

    public SpaceWallPermission getPermissionForSpaceWall(SpaceWall spaceWall) {
        return spaceWallPermissionRepository.findBySpaceWall(spaceWall)
                .orElseThrow(() -> new IllegalArgumentException("해당 공유 스페이스에 대한 권한 정보를 찾을 수 없습니다."));
    }
}
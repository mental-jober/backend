package com.fastcampus.jober.domain.spacewallpermission.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionRequest;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionResponse;
import com.fastcampus.jober.domain.spacewallpermission.repository.SpaceWallPermissionRepository;
import com.fastcampus.jober.global.constant.Auths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallPermissionService {

    private final SpaceWallPermissionRepository spaceWallPermissionRepository;

    @Transactional
    public SpaceWallPermissionResponse updatePermission(Long id, SpaceWallPermissionRequest requestDto) {
        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        spaceWallPermission.setAuths(requestDto.getAuths());

        SpaceWallPermission updatedPermission = spaceWallPermissionRepository.save(spaceWallPermission);
        return new SpaceWallPermissionResponse(updatedPermission);
    }

    @Transactional
    public SpaceWallPermissionResponse moveSpaceWallPermission(Long id, Long parentId) {
        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        // 상위 ID가 있는 경우 권한이 상속되는지 확인하세요.
        if (parentId != null) {
            SpaceWallPermission parentPermission = spaceWallPermissionRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상위 공유페이스 권한 ID입니다."));

            // 권한은 부모로부터 상속됩니다.
            spaceWallPermission.setAuths(parentPermission.getAuths());
        }

        spaceWallPermission.setParentId(parentId);
        SpaceWallPermission updatedPermission = spaceWallPermissionRepository.save(spaceWallPermission);
        return new SpaceWallPermissionResponse(updatedPermission);
    }

    @Transactional
    public void assignPermissionToSpaceWall(Auths auth, SpaceWall spaceWall) {
        SpaceWallPermission permission = new SpaceWallPermission();
        permission.setAuths(auth);
        spaceWallPermissionRepository.save(permission);
    }

}

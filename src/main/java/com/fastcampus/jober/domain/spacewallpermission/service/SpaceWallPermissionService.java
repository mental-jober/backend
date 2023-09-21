package com.fastcampus.jober.domain.spacewallpermission.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionRequest;
import com.fastcampus.jober.domain.spacewallpermission.dto.SpaceWallPermissionResponse;
import com.fastcampus.jober.domain.spacewallpermission.repository.SpaceWallPermissionRepository;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.error.exception.InvalidTargetSequenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceWallPermissionService {

    private final SpaceWallPermissionRepository spaceWallPermissionRepository;
    private final SpaceWallRepository spaceWallRepository;

    @Transactional
    public SpaceWallPermissionResponse updatePermission(Long id, SpaceWallPermissionRequest requestDto) {
        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        spaceWallPermission.setAuths(requestDto.getAuths());

        SpaceWallPermission updatedPermission = spaceWallPermissionRepository.save(spaceWallPermission);
        return new SpaceWallPermissionResponse(updatedPermission);
    }

    @Transactional
    public SpaceWallPermissionResponse moveSpaceWallPermission(Long id, Long targetSequence) {
        SpaceWall currentSpaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 ID입니다."));

        if (targetSequence == null || targetSequence < 1 || targetSequence > spaceWallRepository.count()) {
            throw new InvalidTargetSequenceException("잘못된 타겟 순서입니다. 입력 값: " + targetSequence);
        }

        int currentSequence = currentSpaceWall.getSequence();
        int targetSeqInt = targetSequence.intValue();

        if (targetSeqInt > currentSequence) {
            List<SpaceWall> affectedSpaceWalls = spaceWallRepository.findBySequenceBetween(currentSequence + 1, targetSeqInt);
            for (SpaceWall spaceWall : affectedSpaceWalls) {
                spaceWall.setSequence(spaceWall.getSequence() - 1);
                spaceWallRepository.save(spaceWall);
            }
        } else if (targetSeqInt < currentSequence) {
            List<SpaceWall> affectedSpaceWalls = spaceWallRepository.findBySequenceBetween(targetSeqInt, currentSequence - 1);
            for (SpaceWall spaceWall : affectedSpaceWalls) {
                spaceWall.setSequence(spaceWall.getSequence() + 1);
                spaceWallRepository.save(spaceWall);
            }
        } else {
            // Same position, no movement
            throw new IllegalArgumentException("이동할 필요가 없습니다.");
        }

        currentSpaceWall.setSequence(targetSeqInt);
        spaceWallRepository.save(currentSpaceWall);

        SpaceWallPermission spaceWallPermission = spaceWallPermissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 공유페이스 권한 ID입니다."));

        return new SpaceWallPermissionResponse(spaceWallPermission);
    }


    @Transactional
    public void assignPermissionToSpaceWall(Auths auth, SpaceWall spaceWall) {
        SpaceWallPermission permission = new SpaceWallPermission();
        permission.setAuths(auth);
        spaceWallPermissionRepository.save(permission);
    }

}

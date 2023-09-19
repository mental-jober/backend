package com.fastcampus.jober.domain.spacewallpermission.repository;


import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceWallPermissionRepository extends JpaRepository<SpaceWallPermission, Long> {
    Optional<SpaceWallPermission> findBySpaceWall(SpaceWall spaceWall);
    // TODO: 만약 Member를 기반으로 권한을 조회하는 기능
    // Optional<SpaceWallPermission> findByMember(Member member);
}

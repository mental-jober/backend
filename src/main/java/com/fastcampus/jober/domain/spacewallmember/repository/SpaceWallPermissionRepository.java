package com.fastcampus.jober.domain.spacewallmember.repository;

import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallPermission;
import com.fastcampus.jober.global.constant.Auths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpaceWallPermissionRepository extends JpaRepository<SpaceWallPermission, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE SpaceWallPermission swp SET swp.auths = :auths WHERE swp.spaceWallMember.id = :spaceWallMemberId")
    void updatePermission(@Param("spaceWallMemberId") Long spaceWallMemberId, @Param("auths") Auths auths);

    @Modifying
    @Query(
            value = "INSERT INTO space_wall_permission (space_wall_member_id, auths, type, created_at) " +
                    "VALUES (:spaceWallMemberId, :#{#auths.name()}, 'WHITE', now())",
            nativeQuery = true
    )
    void insertPermission(@Param("spaceWallMemberId") Long spaceWallMemberId, @Param("auths") Auths auths);
}

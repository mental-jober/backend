package com.fastcampus.jober.domain.spacewallmember.repository;

import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpaceWallMemberRepository extends JpaRepository<SpaceWallMember, Long>
{
    @Query("SELECT swm FROM SpaceWallMember swm WHERE swm.spaceWall.id = :spaceWallId AND swm.member.id = :memberId")
    SpaceWallMember findSpaceWallMemberByEmail(@Param("spaceWallId") Long spaceWallId, @Param("memberId") Long memberId);

    @Modifying
    @Query(
            value = "INSERT INTO space_wall_member (member_id, space_wall_id, created_at) " +
                    "VALUES (:memberId, :spaceWallId, now())",
            nativeQuery = true
    )
    void insertMember(@Param("memberId") Long memberId, @Param("spaceWallId") Long spaceWallId);
}

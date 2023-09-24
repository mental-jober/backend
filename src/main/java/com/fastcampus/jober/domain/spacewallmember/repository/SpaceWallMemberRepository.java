package com.fastcampus.jober.domain.spacewallmember.repository;

import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceWallMemberRepository extends JpaRepository<SpaceWallMember, Long> {
    @Query("SELECT swm FROM SpaceWallMember swm WHERE swm.spaceWall.id = :spaceWallId AND swm.member.id = :memberId")
    SpaceWallMember selectSpaceWallMember(@Param("spaceWallId") Long spaceWallId, @Param("memberId") Long memberId);

    @Modifying
    @Query(
            value = "INSERT INTO space_wall_member (member_id, space_wall_id, created_at) " +
                    "VALUES (:memberId, :spaceWallId, now())",
            nativeQuery = true
    )
    void insertMember(@Param("memberId") Long memberId, @Param("spaceWallId") Long spaceWallId);

    @Query("SELECT swm FROM SpaceWallMember swm WHERE swm.spaceWall.id = :spaceWallId")
    List<SpaceWallMember> selectAllSpaceWallMembers(@Param("spaceWallId") Long spaceWallId);

    @Query("SELECT COUNT(*) FROM SpaceWallMember swm WHERE swm.spaceWall.id = :spaceWallId")
    int selectSizeOfSpaceWallMember(@Param("spaceWallId") Long spaceWallId);

    @Modifying
//    @Query("DELETE FROM SpaceWallMember swm WHERE swm.spaceWall.id = :spaceWallId AND swm.member.email = :email")
    @Query(value = "DELETE FROM space_wall_member WHERE id = " +
            "(SELECT swm.id FROM member m " +
            "INNER JOIN space_wall_member swm ON m.id = swm.member_id " +
            "WHERE swm.space_wall_id = :spaceWallId AND m.email = :email);", nativeQuery = true)
    void deleteAllSpaceWallMemberByEmail(@Param("spaceWallId") Long spaceWallId, @Param("email") String email);

}

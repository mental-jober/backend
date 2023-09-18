package com.fastcampus.jober.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT u FROM Member u WHERE u.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);
}

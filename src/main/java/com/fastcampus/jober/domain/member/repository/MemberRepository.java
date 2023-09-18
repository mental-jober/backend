package com.fastcampus.jober.domain.member.repository;

import com.fastcampus.jober.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT u FROM Member u WHERE u.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);
}

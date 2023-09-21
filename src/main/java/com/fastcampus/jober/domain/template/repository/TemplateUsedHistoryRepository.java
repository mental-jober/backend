package com.fastcampus.jober.domain.template.repository;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.domain.TemplateUsedHistory;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemplateUsedHistoryRepository extends JpaRepository<TemplateUsedHistory, Long> {

    Optional<TemplateUsedHistory> findByMemberAndTemplate(Member member, Template template);

    @Query("SELECT t.template FROM TemplateUsedHistory t WHERE t.member = :member ORDER BY t.usedAt DESC")
    Page<Template> findAllByMember(Member member, Pageable pageable);
}

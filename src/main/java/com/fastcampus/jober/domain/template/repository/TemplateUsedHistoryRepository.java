package com.fastcampus.jober.domain.template.repository;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.domain.TemplateUsedHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateUsedHistoryRepository extends JpaRepository<TemplateUsedHistory, Long> {

    Optional<TemplateUsedHistory> findByMemberAndTemplate(Member member, Template template);
}

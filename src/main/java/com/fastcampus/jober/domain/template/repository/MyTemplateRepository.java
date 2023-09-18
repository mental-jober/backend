package com.fastcampus.jober.domain.template.repository;

import com.fastcampus.jober.domain.member.Member;
import com.fastcampus.jober.domain.template.domain.MyTemplate;
import com.fastcampus.jober.domain.template.domain.Template;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyTemplateRepository extends JpaRepository<MyTemplate, Long> {

    @Query("SELECT m.templates FROM MyTemplate m WHERE m.member = :member")
    Page<Template> findTemplatesByMember(Member member, Pageable pageable);


    Optional<MyTemplate> findByMemberAndTemplatesIn(Member member, List<Template> templates);
}

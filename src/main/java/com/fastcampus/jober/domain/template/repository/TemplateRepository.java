package com.fastcampus.jober.domain.template.repository;

import com.fastcampus.jober.domain.template.domain.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    @Query("SELECT t FROM Template t WHERE t.title LIKE %:keyword% OR t.hashtag LIKE %:keyword%")
    Page<Template> findAllByKeywordContains(String keyword, Pageable pageable);
}

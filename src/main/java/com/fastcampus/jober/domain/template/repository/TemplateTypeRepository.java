package com.fastcampus.jober.domain.template.repository;

import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.domain.template.domain.TemplateType;
import com.fastcampus.jober.global.constant.TemplateCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemplateTypeRepository extends JpaRepository<TemplateType, Long> {

    @Query("SELECT t2 FROM TemplateType t JOIN t.template t2 WHERE t.templateCategory = :templateCategory")
    Page<Template> findByTemplateCategory(TemplateCategory templateCategory, Pageable pageable);

    @Query("SELECT t2 FROM TemplateType t JOIN t.template t2 WHERE (t2.title LIKE %:keyword% OR t2.hashtag LIKE %:keyword%) AND t.templateCategory = :templateCategory")
    Page<Template> findAllByKeywordAndType(String keyword,
        TemplateCategory templateCategory, Pageable pageable);
}

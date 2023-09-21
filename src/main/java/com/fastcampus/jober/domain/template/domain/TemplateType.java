package com.fastcampus.jober.domain.template.domain;

<<<<<<< HEAD
import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.global.constant.TemplateCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TemplateType extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    // TODO: 2023/09/21 이부분 다시 체크 
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private TemplateCategory templateCategory;
    
}

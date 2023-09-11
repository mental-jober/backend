package com.fastcampus.jober.domain.template.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "template")
    private List<TemplateType> templateType;

    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(length = 20)
    private String hashtag;

    @Column(length = 200)
    private String thumbnailImageUrl;

    @CreatedDate
    private LocalDateTime createdAt;
}

package com.fastcampus.jober.domain.template.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "template", cascade = CascadeType.REMOVE)
    private List<TemplateType> templateType;

    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(length = 20)
    private String hashtag;

    @Column(length = 200)
    private String thumbnailImageUrl;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public List<String> getHashtags() {
        return Arrays.stream(this.hashtag.split(",")).toList();
    }
}

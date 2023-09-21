package com.fastcampus.jober.domain.spacewalllayout.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space_wall_layout")
public class SpaceWallLayout extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(name = "thumnail_image_url", length = 200)
    private String thumbnailImageUrl;

    @Column(length = 10, nullable = false)
    private String type;

}


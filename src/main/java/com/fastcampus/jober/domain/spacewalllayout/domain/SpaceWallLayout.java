package com.fastcampus.jober.domain.spacewalllayout.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SpaceWallLayout extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 200)
    private String thumnailImageUrl;

    @Column(length = 10, nullable = false)
    private String type;

}

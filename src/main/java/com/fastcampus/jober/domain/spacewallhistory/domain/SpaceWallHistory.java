package com.fastcampus.jober.domain.spacewallhistory.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SpaceWallHistory extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "space_wall_id")
    private SpaceWall spaceWall;

    @Column(length = 100)
    private String url;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(length = 200)
    private String profileImageUrl;

    @Column(length = 200)
    private String backgroundImageUrl;

    @Column(length = 100)
    private String pathIds;

    @Column(length = 100)
    private String shareUrl;

    private LocalDateTime sharedExpiredAt;

    @Column(nullable = false)
    private int sequence;

    private Long layoutId;

    private Long createMemberId;

    private Long workspaceId;

}

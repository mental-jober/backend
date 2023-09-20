package com.fastcampus.jober.domain.spacewall.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewalllayout.domain.SpaceWallLayout;
import com.fastcampus.jober.domain.workspace.domain.Workspace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SpaceWall extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "layout_id", nullable = false)
    private SpaceWallLayout layout;


    @ManyToOne
    @JoinColumn(name = "create_member_id", nullable = false)
    private Member createMember;

    @ManyToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    @Column(nullable = false, length = 100, unique = true)
    private String url;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 200)
    private String profileImageUrl;

    @Column(length = 200)
    private String backgroundImageUrl;

    @Column(length = 100)
    private String pathIds;

    @Column(length = 100)
    private String shareUrl;

    private LocalDateTime shareExpiredAt;

    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;

}

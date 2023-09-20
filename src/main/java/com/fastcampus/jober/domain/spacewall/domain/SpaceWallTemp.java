package com.fastcampus.jober.domain.spacewall.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewalllayout.SpaceWallLayout;
import com.fastcampus.jober.domain.workspace.Workspace;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "space_wall_temp")
public class SpaceWallTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 임시 저장에서는 nullable 처리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layout_id", nullable = true)
    private SpaceWallLayout layout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_member_id", nullable = true)
    private Member createMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = true)

    private Workspace workspace;

    private String url;
    private String title;
    private String description;
    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;
    @Column(name = "background_image_url", nullable = true)
    private String backgroundImageUrl;
    @Column(name = "path_ids", nullable = true)
    private String pathIds;
    @Column(name = "share_url", nullable = true)
    private String shareUrl;
    @Column(name = "share_expired_at", nullable = true)
    private LocalDateTime shareExpiredAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = true)
    private LocalDateTime updatedAt;
    @Column(nullable = true)
    private LocalDateTime deletedAt;

    private int sequence;

    // 임시 저장된 시간
    @Column(nullable = false)
    private LocalDateTime savedAt = LocalDateTime.now();

    protected SpaceWallTemp() {}

    @Builder
    public SpaceWallTemp(SpaceWallLayout layout, Member createMember, Workspace workspace, String url, String title, String description,
                         String profileImageUrl, String backgroundImageUrl, String pathIds, String shareUrl,
                         LocalDateTime shareExpiredAt, int sequence) {
        validateRequiredFields(layout, createMember, workspace, url, title, description);
        this.layout = layout;
        this.createMember = createMember;
        this.workspace = workspace;
        this.url = url;
        this.title = title;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.pathIds = pathIds;
        this.shareUrl = shareUrl;
        this.shareExpiredAt = shareExpiredAt;
        this.sequence = sequence;
    }

    private void validateRequiredFields(SpaceWallLayout layout, Member createMember, Workspace workspace, String url, String title, String description) {
        if (layout == null || createMember == null || workspace == null || url == null || title == null || description == null) {
            throw new IllegalArgumentException("");
        }
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

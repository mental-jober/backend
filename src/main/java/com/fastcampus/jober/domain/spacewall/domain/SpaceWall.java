package com.fastcampus.jober.domain.spacewall.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewalllayout.SpaceWallLayout;
import com.fastcampus.jober.domain.workspace.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(name = "space_wall")
public class SpaceWall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "layout_id", nullable = false)
//    private SpaceWallLayout layout;
//
//    @ManyToOne
//    @JoinColumn(name = "create_member_id", nullable = false)
//    private Member createMember;
//
//    @ManyToOne
//    @JoinColumn(name = "workspace_id", nullable = false)
//    private Workspace workspace;

    @Column(nullable = true, length = 100)
    private String url;

    @Column(nullable = true, length = 100)
    private String title;

    @Column(nullable = true, length = 100)
    private String description;

    @Column(name = "profile_image_url", nullable = true, length = 200)
    private String profileImageUrl;

    @Column(name = "background_image_url", nullable = true, length = 200)
    private String backgroundImageUrl;

    @Column(name = "path_ids", nullable = true, length = 100)
    private String pathIds;

    @Column(name = "share_url", nullable = true, length = 100)
    private String shareUrl;

    @Column(name = "share_expired_at", nullable = true)
    private LocalDateTime shareExpiredAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private int sequence;

    protected SpaceWall() {

    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
    public void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void update(SpaceWall updatedSpaceWall) {
//        if (updatedSpaceWall.getLayout() != null) this.layout = updatedSpaceWall.getLayout();
        if (updatedSpaceWall.getUrl() != null) this.url = updatedSpaceWall.getUrl();
        if (updatedSpaceWall.getTitle() != null) this.title = updatedSpaceWall.getTitle();
        if (updatedSpaceWall.getDescription() != null) this.description = updatedSpaceWall.getDescription();
        if (updatedSpaceWall.getProfileImageUrl() != null) this.profileImageUrl = updatedSpaceWall.getProfileImageUrl();
        if (updatedSpaceWall.getBackgroundImageUrl() != null) this.backgroundImageUrl = updatedSpaceWall.getBackgroundImageUrl();
        if (updatedSpaceWall.getPathIds() != null) this.pathIds = updatedSpaceWall.getPathIds();
        if (updatedSpaceWall.getShareUrl() != null) this.shareUrl = updatedSpaceWall.getShareUrl();
        if (updatedSpaceWall.getShareExpiredAt() != null) this.shareExpiredAt = updatedSpaceWall.getShareExpiredAt();
    }
}
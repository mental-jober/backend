package com.fastcampus.jober.domain.spacewall.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallDto;
import com.fastcampus.jober.domain.spacewalllayout.SpaceWallLayout;
import com.fastcampus.jober.domain.workspace.Workspace;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "space_wall")
public class SpaceWall {

    @Id
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
    public void update(SpaceWallDto.UpdateDto updateDto) {
        if (updateDto.getUrl() != null) {
            this.url = updateDto.getUrl();
        }
        if (updateDto.getTitle() != null) {
            this.title = updateDto.getTitle();
        }
        if (updateDto.getDescription() != null) {
            this.description = updateDto.getDescription();
        }
    }

    @Builder
    public SpaceWall(Long id, SpaceWallLayout layout, Member createMember, Workspace workspace, String url, String title, String description, String profileImageUrl, String backgroundImageUrl, String pathIds, String shareUrl, LocalDateTime shareExpiredAt, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, int sequence) {
        this.id = id;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.sequence = sequence;
    }
}
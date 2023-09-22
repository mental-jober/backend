package com.fastcampus.jober.domain.spacewall.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(name = "space_wall_temp")
public class SpaceWallTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "space_wall_id")
    private SpaceWall spaceWall;

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

    protected SpaceWallTemp() {

    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

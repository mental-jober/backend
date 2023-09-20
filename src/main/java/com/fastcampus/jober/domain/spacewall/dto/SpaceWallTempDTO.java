package com.fastcampus.jober.domain.spacewall.dto;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWallTemp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class SpaceWallTempDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveDTO {
        private Long layoutId;
        private Long createMemberId;
        private Long workspaceId;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private String shareUrl;
        private LocalDateTime shareExpiredAt;
        private int sequence;

        public SpaceWallTemp toEntity() {
            return SpaceWallTemp.builder()
                    .layout(new SpaceWallLayout(layoutId))
                    .createMember(new Member(createMemberId))
                    .workspace(new Workspace(workspaceId))
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .shareUrl(shareUrl)
                    .shareExpiredAt(shareExpiredAt)
                    .sequence(sequence)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoadDTO {
        private Long id;
        private Long layoutId;
        private Long createMemberId;
        private Long workspaceId;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private String shareUrl;
        private LocalDateTime shareExpiredAt;
        private int sequence;

        public LoadDTO(SpaceWallTemp spaceWallTemp) {
            this.id = spaceWallTemp.getId();
            this.layoutId = spaceWallTemp.getLayout().getId();
            this.createMemberId = spaceWallTemp.getCreateMember().getId();
            this.workspaceId = spaceWallTemp.getWorkspace().getId();
            this.url = spaceWallTemp.getUrl();
            this.title = spaceWallTemp.getTitle();
            this.description = spaceWallTemp.getDescription();
            this.profileImageUrl = spaceWallTemp.getProfileImageUrl();
            this.backgroundImageUrl = spaceWallTemp.getBackgroundImageUrl();
            this.pathIds = spaceWallTemp.getPathIds();
            this.shareUrl = spaceWallTemp.getShareUrl();
            this.shareExpiredAt = spaceWallTemp.getShareExpiredAt();
            this.sequence = spaceWallTemp.getSequence();
        }
    }
}

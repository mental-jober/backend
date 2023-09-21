package com.fastcampus.jober.domain.spacewall.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SpaceWallRequest {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        private Long createMemberId;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private String shareUrl;
        private Date shareExpiredAt;
        private Integer sequence;

        public SpaceWall toEntity() {
//            SpaceWallLayout layout = SpaceWallLayout.builder().id(layoutId).build();
            Member member = Member.builder().id(createMemberId).build();
//            Workspace workspace = Workspace.builder().id(workspaceId).build();

            LocalDateTime shareExpiration = (shareExpiredAt != null) ?
                    LocalDateTime.ofInstant(shareExpiredAt.toInstant(), ZoneId.systemDefault()) : null;

            return SpaceWall.builder()
//                    .layout(layout)
//                    .createMember(member)
//                    .workspace(workspace)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .shareUrl(shareUrl)
                    .shareExpiredAt(shareExpiration)
                    .sequence(sequence != null ? sequence : 0)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdateDto {
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private String shareUrl;
        private Date shareExpiredAt;

        public SpaceWall toEntity() {
//            SpaceWallLayout layout = SpaceWallLayout.builder().id(layoutId).build();

            LocalDateTime shareExpiration = (shareExpiredAt != null) ?
                    LocalDateTime.ofInstant(shareExpiredAt.toInstant(), ZoneId.systemDefault()) : null;

            return SpaceWall.builder()
//                    .layout(layout)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .shareUrl(shareUrl)
                    .shareExpiredAt(shareExpiration)
                    .build();
        }
    }
}

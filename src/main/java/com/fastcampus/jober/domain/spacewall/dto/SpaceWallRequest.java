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
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private String shareUrl;
        private Date shareExpiredAt;
        private Integer sequence;

        public SpaceWall toEntityWithMember(Member member) {
            LocalDateTime shareExpiration = convertDateToLocalDateTime(shareExpiredAt);

            return SpaceWall.builder()
                    .createMember(member)
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

        private LocalDateTime convertDateToLocalDateTime(Date date) {
            return (date != null) ? LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : null;
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
            LocalDateTime shareExpiration = convertDateToLocalDateTime(shareExpiredAt);

            return SpaceWall.builder()
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

        private LocalDateTime convertDateToLocalDateTime(Date date) {
            return (date != null) ? LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : null;
        }
    }
}

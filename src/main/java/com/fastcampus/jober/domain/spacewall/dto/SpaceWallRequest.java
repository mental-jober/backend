package com.fastcampus.jober.domain.spacewall.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import lombok.*;

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
        private boolean authorized;
        private Integer sequence;

        public SpaceWall toEntityWithMember(Member member) {
            return SpaceWall.builder()
                    .createMember(member)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .authorized(authorized)
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
        private boolean authorized;

        public SpaceWall toEntity() {
            return SpaceWall.builder()
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .authorized(authorized)
                    .build();
        }
    }
}
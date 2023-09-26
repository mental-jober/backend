package com.fastcampus.jober.domain.spacewall.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import lombok.*;

import java.time.LocalDateTime;

public class SpaceWallResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto {
        private Long id;
        private Long create_member_id;
        private String url;
        private String title;
        private String description;
        private String profile_image_url;
        private String background_image_url;
        private String path_ids;
        private boolean authorized;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private int sequence;

        public ResponseDto(SpaceWall spaceWall) {
            this.id = spaceWall.getId();
            this.create_member_id = spaceWall.getCreateMember().getId();
            this.url = spaceWall.getUrl();
            this.title = spaceWall.getTitle();
            this.description = spaceWall.getDescription();
            this.profile_image_url = spaceWall.getProfileImageUrl();
            this.background_image_url = spaceWall.getBackgroundImageUrl();
            this.path_ids = spaceWall.getPathIds();
            this.authorized = spaceWall.isAuthorized();
            this.created_at = spaceWall.getCreatedAt();
            this.updated_at = spaceWall.getUpdatedAt();
            this.sequence = spaceWall.getSequence();
        }

        public SpaceWall toEntity() {
            Member member = Member.builder().id(create_member_id).build();

            return SpaceWall.builder()
                    .id(id)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profile_image_url)
                    .backgroundImageUrl(background_image_url)
                    .pathIds(path_ids)
                    .authorized(authorized)
                    .sequence(sequence)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionDTO {
        private boolean accessable;
    }
}
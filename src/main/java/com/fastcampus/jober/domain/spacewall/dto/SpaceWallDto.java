package com.fastcampus.jober.domain.spacewall.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewalllayout.SpaceWallLayout;
import com.fastcampus.jober.domain.workspace.Workspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SpaceWallDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        private Long layout_id;
        private Long create_member_id;
        private Long workspace_id;
        private String url;
        private String title;
        private String description;
        private String profile_image_url;
        private String background_image_url;
        private String path_ids;
        private String share_url;
        private Date share_expired_at;
        private Integer sequence;


        public SpaceWall toEntity() {
            SpaceWallLayout layout = SpaceWallLayout.builder().id(layout_id).build();
            Member member = Member.builder().id(create_member_id).build();
            Workspace workspace = Workspace.builder().id(workspace_id).build();

            return SpaceWall.builder()
                    .layout(layout)
                    .createMember(member)
                    .workspace(workspace)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profile_image_url)
                    .backgroundImageUrl(background_image_url)
                    .pathIds(path_ids)
                    .shareUrl(share_url)
                    .shareExpiredAt(LocalDateTime.ofInstant(share_expired_at.toInstant(), ZoneId.systemDefault()))
                    .sequence(sequence)
                    .build();
            }
        }


        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ResponseDto {
            private Long id;
            private Long layout_id;
            private Long create_member_id;
            private Long workspace_id;
            private String url;
            private String title;
            private String description;
            private String profile_image_url;
            private String background_image_url;
            private String path_ids;
            private String share_url;
            private LocalDateTime share_expired_at;
            private LocalDateTime created_at;
            private LocalDateTime updated_at;
            private int sequence;

            public ResponseDto(SpaceWall spaceWall) {
                this.id = spaceWall.getId();
                this.layout_id = spaceWall.getLayout() != null ? spaceWall.getLayout().getId() : null;
                this.create_member_id = spaceWall.getCreateMember() != null ? spaceWall.getCreateMember().getId() : null;
                this.workspace_id = spaceWall.getWorkspace() != null ? spaceWall.getWorkspace().getId() : null;
                this.url = spaceWall.getUrl();
                this.title = spaceWall.getTitle();
                this.description = spaceWall.getDescription();
                this.profile_image_url = spaceWall.getProfileImageUrl();
                this.background_image_url = spaceWall.getBackgroundImageUrl();
                this.path_ids = spaceWall.getPathIds();
                this.share_url = spaceWall.getShareUrl();
                this.share_expired_at = spaceWall.getShareExpiredAt();
                this.created_at = spaceWall.getCreatedAt();
                this.updated_at = spaceWall.getUpdatedAt();
                this.sequence = spaceWall.getSequence();
            }

            public SpaceWall toEntity() {
                SpaceWallLayout layout = SpaceWallLayout.builder().id(layout_id).build();
                Member member = Member.builder().id(create_member_id).build();
                Workspace workspace = Workspace.builder().id(workspace_id).build();

                return SpaceWall.builder()
                        .id(id) // ID도 추가했습니다.
                        .layout(layout)
                        .createMember(member)
                        .workspace(workspace)
                        .url(url)
                        .title(title)
                        .description(description)
                        .profileImageUrl(profile_image_url)
                        .backgroundImageUrl(background_image_url)
                        .pathIds(path_ids)
                        .shareUrl(share_url)
                        .shareExpiredAt(share_expired_at)
                        .createdAt(created_at) // createdAt도 추가했습니다.
                        .updatedAt(updated_at) // updatedAt도 추가했습니다.
                        .sequence(sequence)
                        .build();
            }
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UpdateDto {
            private Long layout_id;
            private String url;
            private String title;
            private String description;
            private String profile_image_url;
            private String background_image_url;
            private String path_ids;
            private String share_url;
            private Date share_expired_at;
        }
}
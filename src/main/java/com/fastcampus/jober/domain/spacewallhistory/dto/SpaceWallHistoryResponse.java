package com.fastcampus.jober.domain.spacewallhistory.dto;

import com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryResponse;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class SpaceWallHistoryResponse {

    @Getter
    @Setter
    public static class SpaceWallHistoryResponseDTO {

        private Long id;
        private Long spaceWallId;
        private Long createMemberId;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private boolean authorized;
        private int sequence;

        public SpaceWallHistoryResponseDTO(SpaceWallHistory spaceWallHistory) {
            this.id = spaceWallHistory.getId();
            this.spaceWallId = spaceWallHistory.getSpaceWallId();
            this.createMemberId = spaceWallHistory.getCreateMemberId();
            this.url = spaceWallHistory.getUrl();
            this.title = spaceWallHistory.getTitle();
            this.description = spaceWallHistory.getDescription();
            this.profileImageUrl = spaceWallHistory.getProfileImageUrl();
            this.backgroundImageUrl = spaceWallHistory.getBackgroundImageUrl();
            this.pathIds = spaceWallHistory.getPathIds();
            this.authorized = spaceWallHistory.isAuthorized();
            this.sequence = spaceWallHistory.getSequence();
        }
    }


}
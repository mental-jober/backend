package com.fastcampus.jober.domain.spacewallhistory.dto;

import lombok.Getter;
import lombok.Setter;

public class SpaceWallHistoryRequest {

    @Getter
    @Setter
    public static class SpaceWallHistoryRequestDTO {

        private Long spaceWallId;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private boolean authorized;
        private int sequence;
        private Long createMemberId;
    }
}
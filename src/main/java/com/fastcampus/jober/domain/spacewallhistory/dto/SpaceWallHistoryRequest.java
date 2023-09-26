package com.fastcampus.jober.domain.spacewallhistory.dto;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;

import java.time.LocalDateTime;

public class SpaceWallHistoryRequest {

    public static class SpaceWallHistorySaveDTO {

        private SpaceWall spaceWall;
        private String url;
        private String title;
        private String description;
        private String profileImageUrl;
        private String backgroundImageUrl;
        private String pathIds;
        private boolean authorized;
        private int sequence;
        private Long createMemberId;

        public SpaceWallHistory toEntity() {
            return SpaceWallHistory.builder()
                    .spaceWall(spaceWall)
                    .url(url)
                    .title(title)
                    .description(description)
                    .profileImageUrl(profileImageUrl)
                    .backgroundImageUrl(backgroundImageUrl)
                    .pathIds(pathIds)
                    .authorized(authorized)
                    .sequence(sequence)
                    .createMemberId(createMemberId)
                    .build();
        }
    }
}
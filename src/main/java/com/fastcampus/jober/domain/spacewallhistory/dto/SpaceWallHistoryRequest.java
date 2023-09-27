package com.fastcampus.jober.domain.spacewallhistory.dto;

import com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryRequest;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryRequest.*;

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
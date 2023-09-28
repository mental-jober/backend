package com.fastcampus.jober.domain.componentTemp.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ComponentTempResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComponentTempResponseDTO {

        private Long id;
        private Long spaceWallTempId;
        private Long templateId;
        private Long childSpaceWallTempId;
        private Long componentId;
        private String type;
        private boolean visible;
        private String title;
        private String content;
        private int sequence;
        private boolean deleted;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }




}

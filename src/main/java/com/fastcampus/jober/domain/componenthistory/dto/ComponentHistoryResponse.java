package com.fastcampus.jober.domain.componenthistory.dto;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import com.fastcampus.jober.global.constant.Type;
import lombok.Getter;
import lombok.Setter;

public class ComponentHistoryResponse {

    @Getter
    @Setter
    public static class ComponentHistoryResponseDTO {

        private Long id;
        private Long templateId;
        private Long spaceWallHistoryId;
        private Type type;
        private Long spaceWallId;
        private boolean visible;
        private String content;
        private int sequence;

        public ComponentHistoryResponseDTO(ComponentHistory componentHistory) {
            this.id = componentHistory.getId();
            this.templateId = componentHistory.getTemplate().getId();
            this.spaceWallHistoryId = componentHistory.getSpaceWallHistory().getId();
            this.type = componentHistory.getType();
            this.spaceWallId = componentHistory.getSpaceWallId();
            this.visible = componentHistory.isVisible();
            this.content = componentHistory.getContent();
            this.sequence = componentHistory.getSequence();
        }
    }
}

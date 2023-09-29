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
        private Long childSpaceWallId;
        private Type type;
        private Long spaceWallId;
        private boolean visible;
        private String title;
        private String content;
        private int sequence;

        public ComponentHistoryResponseDTO(ComponentHistory componentHistory) {
            this.id = componentHistory.getId();
            this.templateId = componentHistory.getTemplateId();
            this.spaceWallHistoryId = componentHistory.getSpaceWallHistoryId();
            this.childSpaceWallId = componentHistory.getChildSpaceWallId();
            this.type = componentHistory.getType();
            this.spaceWallId = componentHistory.getSpaceWallId();
            this.visible = componentHistory.isVisible();
            this.title = componentHistory.getTitle();
            this.content = componentHistory.getContent();
            this.sequence = componentHistory.getSequence();
        }
    }
}

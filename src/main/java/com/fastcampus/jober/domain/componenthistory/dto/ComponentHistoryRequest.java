package com.fastcampus.jober.domain.componenthistory.dto;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.global.constant.Type;

public class ComponentHistoryRequest {

    public static class ComponentHistorySaveDTO {

        private Template template;
        private SpaceWallHistory spaceWallHistory;
        private Type type;
        private boolean visible;
        private String content;
        private int sequence;
        private Long spaceWallId;

        public ComponentHistory toEntity() {
            return ComponentHistory.builder()
                    .template(template)
                    .spaceWallHistory(spaceWallHistory)
                    .type(type)
                    .visible(visible)
                    .content(content)
                    .sequence(sequence)
                    .spaceWallId(spaceWallId)
                    .build();
        }
    }
}

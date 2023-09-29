package com.fastcampus.jober.domain.componenthistory.dto;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.global.constant.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

public class ComponentHistoryRequest {

    @Getter
    @Setter
    public static class ComponentHistoryRequestDTO {

        private Long templateId;
        private Long spaceWallHistoryId;
        private Long childSpaceWallId;
        private Type type;
        private boolean visible;
        private String title;
        private String content;
        private int sequence;
        private Long spaceWallId;
    }
}

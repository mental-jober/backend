package com.fastcampus.jober.domain.component.dto;

import com.fastcampus.jober.domain.component.domain.Component;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ComponentResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComponentResponseDTO{
        private Long id;
        private Long space_wall_id;
        private Long template_id;
        private Long child_space_wall_id;
        private String type;
        private boolean visible;
        private String title;
        private String content;
        private int sequence;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;

    }

}

package com.fastcampus.jober.domain.template.dto;

import com.fastcampus.jober.domain.template.domain.Template;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TemplateResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListDto {

        private Long id;
        private String title;
        private String description;
        private List<String> hashtags;
        private String thumbnailUrl;
        private boolean favorite;

        public ListDto(Template template) {
            this.id = template.getId();
            this.title = template.getTitle();
            this.description = template.getDescription();
            this.hashtags = template.getHashtags();
            this.thumbnailUrl = template.getThumbnailImageUrl();
            this.favorite = false;
        }
    }
}

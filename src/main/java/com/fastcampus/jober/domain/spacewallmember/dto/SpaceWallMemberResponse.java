package com.fastcampus.jober.domain.spacewallmember.dto;

import com.fastcampus.jober.global.constant.Auths;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SpaceWallMemberResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpaceWallMemberDTO {

        private Long id;
        private String email;
        private Auths auths;
    }
}

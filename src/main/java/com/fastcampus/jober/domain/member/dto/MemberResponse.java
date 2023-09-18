package com.fastcampus.jober.domain.member.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDTO {

        private Long id;
        private String email;
        private String username;

        public MemberDTO(Member member) {
            this.id = member.getId();
            this.email = member.getEmail();
            this.username = member.getUsername();
        }
    }

    @Getter
    @Setter
    public static class JoinDTO {

        private Long id;
        private String email;
        private String username;

        public JoinDTO(Member member) {
            this.id = member.getId();
            this.email = member.getEmail();
            this.username = member.getUsername();
        }
    }
}

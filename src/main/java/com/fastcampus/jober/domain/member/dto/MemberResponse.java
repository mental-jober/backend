package com.fastcampus.jober.domain.member.dto;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MemberResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDTO {

        private Long id;
        private String email;
        private String username;
        private Map<Long, Auths> spaceWallMemberIdAndAuths;

        public MemberDTO(Member member) {
            Map<Long, Auths> spaceWallMemberIdAndAuths = new HashMap<>();
            this.id = member.getId();
            this.email = member.getEmail();
            this.username = member.getUsername();
            for (SpaceWallMember spaceWallMember : member.getSpaceWallMember()) {
                spaceWallMemberIdAndAuths.put(
                        spaceWallMember.getId(),
                        spaceWallMember.getSpaceWallPermission().getAuths()
                );
            }
            this.spaceWallMemberIdAndAuths = spaceWallMemberIdAndAuths;
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

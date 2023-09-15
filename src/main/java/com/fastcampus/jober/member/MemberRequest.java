package com.fastcampus.jober.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

public class MemberRequest {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {

        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(
                regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
                message = "이메일 형식으로 작성해주세요"
        )
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JoinDTO {

        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(
                regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
                message = "이메일 형식으로 작성해주세요"
        )
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 8, max = 20, message = "8~20자 이내로 입력해주세요")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$",
                message = "영문, 숫자, 특수문자 조합 8~20자 이내로 입력해주세요"
        )
        private String password;

        @Pattern(
                regexp = "^[a-zA-Z가-힣]{1,20}$",
                message = "한글/영문 1~20자 이내로 작성해주세요"
        )
        private String username;

        public void setPassword(String password) { // password 인코딩용
            this.password = password;
        }

        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .password(password)
                    .username(username)
                    .build();
        }
    }
}

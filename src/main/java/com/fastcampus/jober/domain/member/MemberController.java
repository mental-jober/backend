package com.fastcampus.jober.domain.member;

import com.fastcampus.jober.core.auth.jwt.JwtTokenProvider;
import com.fastcampus.jober.core.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 합니다.
     * @param joinRequestDTO : 회원가입 요청 정보
     * @return : 사용자 정보 반환
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(
            @Valid @RequestBody MemberRequest.JoinDTO joinRequestDTO
    ) {

        return ResponseEntity.ok(new ResponseDTO<>(memberService.join(joinRequestDTO), "회원가입에 성공했습니다."));
    }

    /**
     * 로그인 합니다.
     * @param loginRequestDTO : 로그인 요청 정보
     * @return : Header(토큰), Body(사용자 정보) 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid MemberRequest.LoginDTO loginRequestDTO
    ) {
        Map<String, Object> response = memberService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return ResponseEntity.ok()
                .header(JwtTokenProvider.HEADER, (String) response.get("token"))
                .body(new ResponseDTO<>(response.get("memberInfo"), "로그인에 성공했습니다."));
    }

    /**
     * 로그아웃 합니다.
     * @param authorization : 토큰 정보
     * @return : 로그아웃 메세지 반환
     */
    @PostMapping("logout")
    public ResponseEntity<?> logout(
            @RequestHeader(JwtTokenProvider.HEADER) String authorization
    ) {
        memberService.logout(authorization.split(" ")[1]); // Bearer 떼냄.
        return ResponseEntity.ok(new ResponseDTO<>("로그아웃 하였습니다."));
    }
}
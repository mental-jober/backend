package com.fastcampus.jober.domain.member.controller;

import com.fastcampus.jober.domain.member.dto.MemberRequest;
import com.fastcampus.jober.domain.member.service.MemberService;
import com.fastcampus.jober.global.auth.jwt.JwtTokenProvider;
import com.fastcampus.jober.global.error.exception.MemberException;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.fastcampus.jober.global.constant.ErrorCode.DUPLICATED_EMAIL;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 합니다.
     * @param joinRequestDTO 회원가입 요청 정보
     * @return 사용자 정보 반환
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(
        @Valid @RequestBody MemberRequest.JoinDTO joinRequestDTO
    ) {
        if (memberService.checkEmailDuplication(joinRequestDTO.getEmail()))
            throw new MemberException(DUPLICATED_EMAIL);

        return ResponseEntity.ok(
            new ResponseDTO<>(memberService.join(joinRequestDTO), "회원가입에 성공했습니다."));
    }

    /**
     * 로그인 합니다.
     * @param loginRequestDTO 로그인 요청 정보
     * @return Header(토큰), Body(사용자 정보) 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody @Valid MemberRequest.LoginDTO loginRequestDTO
    ) {
        Map<String, Object> response = memberService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        return ResponseEntity.ok()
            .header(JwtTokenProvider.HEADER, (String) response.get("token"))
            .body(new ResponseDTO<>(response.get("memberInfo"), "로그인에 성공했습니다."));
    }

    /**
     * 토큰 기한을 0으로 축소시켜 로그아웃 합니다.
     * @param authorization 토큰
     * @return 메세지 반환
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        @RequestHeader(JwtTokenProvider.HEADER) String authorization
    ) {
        memberService.logout(authorization.split(" ")[1]); // Bearer 떼냄.
        return ResponseEntity.ok(new ResponseDTO<>("로그아웃 하였습니다."));
    }

    /**
     * Email이 Member에 존재하는지 확인하기 위한 API 입니다.
     * @param request String email
     * @return boolean
     */
    @GetMapping("/checkEmail/{email}")
    public boolean isExistMemberByEmail(@PathVariable(name = "email") @Email String request) {
        return memberService.checkEmailDuplication(request);
    }
}
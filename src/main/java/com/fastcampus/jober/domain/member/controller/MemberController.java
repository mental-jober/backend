package com.fastcampus.jober.domain.member.controller;

import com.fastcampus.jober.domain.member.dto.MemberRequest;
import com.fastcampus.jober.domain.member.service.MemberService;
import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.global.auth.jwt.JwtTokenProvider;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 로그아웃 합니다.
     * @param authorization 토큰 정보
     * @return 로그아웃 메세지 반환
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        @RequestHeader(JwtTokenProvider.HEADER) String authorization
    ) {
        memberService.logout(authorization.split(" ")[1]); // Bearer 떼냄.
        return ResponseEntity.ok(new ResponseDTO<>("로그아웃 하였습니다."));
    }

    /**
     * 공동 작업자 '초대' 버튼을 누를 때 <br>
     * Email이 Member에 존재하는지 확인하기 위한 API 입니다.
     * @param request email, auths
     * @return boolean
     */
    @GetMapping("/checkEmail")
    public boolean isExistMemberByEmail(@RequestBody @Valid SpaceWallMemberRequest.AssignDTO request) {
        return memberService.findEmail(request.getEmail());
    }
}
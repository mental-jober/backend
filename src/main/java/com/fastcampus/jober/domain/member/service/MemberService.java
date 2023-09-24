package com.fastcampus.jober.domain.member.service;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.member.dto.MemberRequest;
import com.fastcampus.jober.domain.member.dto.MemberResponse.JoinDTO;
import com.fastcampus.jober.domain.member.dto.MemberResponse.MemberDTO;
import com.fastcampus.jober.domain.member.repository.MemberRepository;
import com.fastcampus.jober.global.auth.jwt.JwtTokenProvider;
import com.fastcampus.jober.global.auth.session.MemberDetails;
import com.fastcampus.jober.global.error.exception.Exception401;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.fastcampus.jober.global.constant.ErrorCode.CHECK_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final AuthenticationManager authenticationManager;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public Map<String, Object> login(String email, String password) {
        Member member = memberRepository.findByEmail(email).
            orElseThrow(() -> new Exception401(CHECK_ID.getMessage()));

//        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
//            throw new Exception401(CHECK_PASSWORD.getMessage());
//        }

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Member loginUser = memberDetails.getMember();

        // 리턴 정보
        MemberDTO responseMemberInfo = new MemberDTO(loginUser);
        responseMemberInfo.setId(loginUser.getId());
        responseMemberInfo.setUsername(loginUser.getUsername());
        responseMemberInfo.setEmail(loginUser.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", JwtTokenProvider.create(loginUser));
        response.put("memberInfo", responseMemberInfo);

        return response;
    }

    @Transactional
    public void logout(String token) {

        JwtTokenProvider.delete(token);
    }

    @Transactional
    public JoinDTO join(MemberRequest.JoinDTO joinRequestDTO) {

//        joinRequestDTO.setPassword(bCryptPasswordEncoder.encode(joinRequestDTO.getPassword()));

        Member userPS = memberRepository.save(joinRequestDTO.toEntity());

        JoinDTO response = new JoinDTO(userPS);
        response.setUsername(response.getUsername());
        response.setEmail(response.getEmail());

        return response;

    }

    public boolean findEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}

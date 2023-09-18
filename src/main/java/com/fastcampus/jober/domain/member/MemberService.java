package com.fastcampus.jober.domain.member;

import com.fastcampus.jober.core.auth.jwt.JwtTokenProvider;
import com.fastcampus.jober.core.auth.session.MemberDetails;
import com.fastcampus.jober.core.exception.Exception401;
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

import static com.fastcampus.jober.core.exception.ErrorCode.CHECK_ID;
import static com.fastcampus.jober.core.exception.ErrorCode.CHECK_PASSWORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public Map<String, Object> login(String email, String password) {

        Member member = memberRepository.findByEmail(email).
                orElseThrow(() -> new Exception401(CHECK_ID.getMessage()));

        if(!bCryptPasswordEncoder.matches(password, member.getPassword()))
            throw new Exception401(CHECK_PASSWORD.getMessage());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Member loginUser = memberDetails.getMember();

        MemberResponse.MemberDTO responseMemberInfo = new MemberResponse.MemberDTO(loginUser);
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
    public MemberResponse.JoinDTO join(MemberRequest.JoinDTO joinRequestDTO) {

        joinRequestDTO.setPassword(bCryptPasswordEncoder.encode(joinRequestDTO.getPassword()));

        Member userPS = memberRepository.save(joinRequestDTO.toEntity());

        MemberResponse.JoinDTO response = new MemberResponse.JoinDTO(userPS);
        response.setUsername(response.getUsername());
        response.setEmail(response.getEmail());

        return response;

    }
}

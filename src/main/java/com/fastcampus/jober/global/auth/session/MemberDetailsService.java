package com.fastcampus.jober.global.auth.session;

import static com.fastcampus.jober.global.constant.ErrorCode.INVALID_AUTHENTICATION;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.member.repository.MemberRepository;
import com.fastcampus.jober.global.error.exception.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElseThrow(
            () -> new Exception401(INVALID_AUTHENTICATION.getMessage())
        );

        return new MemberDetails(member);
    }
}

package com.fastcampus.jober.core.auth.session;

import com.fastcampus.jober.core.exception.Exception401;
import com.fastcampus.jober.domain.member.Member;
import com.fastcampus.jober.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.fastcampus.jober.core.exception.ErrorCode.INVALID_AUTHENTICATION;

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

package com.fastcampus.jober.global.auth.jwt;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.global.auth.session.MemberDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        if (jwtTokenProvider == null) {
            this.jwtTokenProvider = new JwtTokenProvider();
        }
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        String prefixJwt = request.getHeader(JwtTokenProvider.HEADER);

        if (prefixJwt == null) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = prefixJwt.replace(JwtTokenProvider.TOKEN_PREFIX, "");

        if (JwtTokenProvider.isExpired(jwt)) {
            log.error("토큰이 만료되었습니다.");
            chain.doFilter(request, response);
        }

        Member member = Member.builder()
                .id(JwtTokenProvider.getMemberIdFromToken(jwt))
                .build();

        Long parsedId = null;
        if (request.getRequestURI().split("/")[1].equals("spaces"))
            // localhost:8080[0]/spaces[1]/{space_wall_id}[2] parsing
            parsedId = Long.parseLong(request.getRequestURI().split("/")[2]);

        MemberDetails memberDetails = new MemberDetails(
                member,
                parsedId,
                JwtTokenProvider.getAssignedSpaceWallMemberIdsFromToken(jwt)
        );

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        memberDetails,
                        memberDetails.getPassword(),
                        memberDetails.getAuthorities()
                );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }
}

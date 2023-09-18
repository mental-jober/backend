package com.fastcampus.jober.global.auth.jwt;

import com.fastcampus.jober.domain.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public static final Long EXP = 1000L * 60 * 60 * 24; // 24시간
    public static final String TOKEN_PREFIX = "Bearer "; // 스페이스 필요함
    public static final String HEADER = "Authorization";
    public static String SECRET;

    @Value("${my-env.jwt.secret}")
    public void setSECRET(String secret) {
        SECRET = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public static Long getMemberIdFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
            .getBody().get("id", Long.class);
    }

    public static String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
            .getBody().get("email", String.class);
    }

    public static boolean isExpired(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
            .getBody().getExpiration().before(new Date());
    }

    public static String create(Member member) {
        Claims claims = Jwts.claims();
        claims.put("id", member.getId());
        claims.put("email", member.getEmail());

        String jwt = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXP))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();

        return TOKEN_PREFIX + jwt;
    }

    public static void delete(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        claims.setExpiration(new Date(0));
        Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }
}

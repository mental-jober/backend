package com.fastcampus.jober.global.auth.jwt;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.*;

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

    public static Map<Long, Auths> getAssignedSpaceWallMemberIdsFromToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String jsonList = (String) claims.get("assignedSpaceMemberInfos");

        return convertJsonToObjectList(jsonList);
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
        List<SpaceWallMember> spaceWallMemberList = member.getSpaceWallMember();
        Map<Long, Auths> spaceWallMemberInfos = new HashMap<>();
        for (SpaceWallMember spaceWallMember : spaceWallMemberList) {
            spaceWallMemberInfos.put(spaceWallMember.getId(), spaceWallMember.getSpaceWallPermission().getAuths());
        }
        Claims claims = Jwts.claims();
        claims.put("id", member.getId());
        claims.put("email", member.getEmail());
        claims.put("assignedSpaceMemberInfos", convertObjectListToJson(spaceWallMemberInfos));

        String jwt = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXP))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();

        return TOKEN_PREFIX + jwt;
    }

    private static String convertObjectListToJson(Map<Long, Auths> objectList) {
        // ObjectMapper를 사용하여 객체 리스트를 JSON으로 직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(objectList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<Long, Auths> convertJsonToObjectList(String jsonList) {
        // ObjectMapper를 사용하여 JSON 문자열을 객체 리스트로 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonList, new TypeReference<Map<Long, Auths>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
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

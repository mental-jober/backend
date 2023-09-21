package com.fastcampus.jober.global.auth.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class SpaceWallInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (request.getAttribute("spaceWallId") != null)
            SpaceWallContextHolder.setSpaceWallId(Long.parseLong(request.getAttribute("spaceWallId").toString()));
        return true;
    }
}

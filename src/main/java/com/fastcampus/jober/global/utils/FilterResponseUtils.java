package com.fastcampus.jober.global.utils;

import com.fastcampus.jober.global.error.exception.Exception401;
import com.fastcampus.jober.global.error.exception.Exception403;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;

public class FilterResponseUtils {

    public static void unAuthorized(
        HttpServletResponse httpServletResponse,
        Exception401 e
    ) throws IOException {

        httpServletResponse.setStatus(e.status().value());
        httpServletResponse.setContentType("application/json; charset=utf-8");
        ResponseDTO<?> responseDto = new ResponseDTO<>(
            HttpStatus.UNAUTHORIZED,
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            e.getMessage()
        );
        ObjectMapper om = new ObjectMapper();
//        String responseBody = om.writeValueAsString(e.body());
        String responseBody = om.writeValueAsString(responseDto);
        httpServletResponse.getOutputStream().write(responseBody.getBytes());
    }

    public static void forbidden(
        HttpServletResponse httpServletResponse,
        Exception403 e
    ) throws IOException {

        httpServletResponse.setStatus(e.status().value());
        httpServletResponse.setContentType("application/json; charset=utf-8");
        ResponseDTO<?> responseDto = new ResponseDTO<>(
            HttpStatus.FORBIDDEN,
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            e.getMessage()
        );
        ObjectMapper om = new ObjectMapper();
//        String responseBody = om.writeValueAsString(e.body());
        String responseBody = om.writeValueAsString(responseDto);
//        httpServletResponse.getWriter().println(responseBody);
        httpServletResponse.getOutputStream().write(responseBody.getBytes());

    }
}

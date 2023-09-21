package com.fastcampus.jober.global.config;

import com.fastcampus.jober.global.auth.session.SpaceWallInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spaceWallId를 MemberDetails에 전달하기 위한 SpaceWallContextHolder가
 * 필요한 API가 실행되기 직전에 전달되기 위한 설정입니다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SpaceWallInterceptor spaceWallInterceptor() {
        return new SpaceWallInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(spaceWallInterceptor())
                .addPathPatterns("/spaces/**");
    }
}

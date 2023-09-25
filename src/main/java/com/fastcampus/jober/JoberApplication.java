package com.fastcampus.jober;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@EnableJpaAuditing
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class JoberApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoberApplication.class, args);
    }

}

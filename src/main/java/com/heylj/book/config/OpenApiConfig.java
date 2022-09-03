package com.heylj.book.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("swagger")
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("스프링부트로 웹 서비스 구축하기")
                .version(springdocVersion)
                .description("이 API는 스프링부트로 웹 서비스 구축하기 책으로 보고 따라한 예제입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

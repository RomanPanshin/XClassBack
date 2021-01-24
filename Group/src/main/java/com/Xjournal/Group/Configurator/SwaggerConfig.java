package com.Xjournal.Group.Configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.foo.samples.swaggersample"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "XClass REST API",
                "API for  Android app",
                "version-1",
                "",
                "roma.super@icloud.com",
                "BSD 2-Clause License",
                "https://github.com/RomanPanshin/XClassBack/blob/master/LICENSE"
        );
        return apiInfo;
    }
}
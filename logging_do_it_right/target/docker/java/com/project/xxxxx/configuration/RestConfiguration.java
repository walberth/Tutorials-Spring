package com.project.xxxxx.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Aspect
@EnableSwagger2
@Configuration
public class RestConfiguration  implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Before("execution(* com.project.xxxxx.service.implementation.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.setProperty("methodName", joinPoint.getSignature().getName());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "APIKey", "header");
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Management REST API")
                .contact(new Contact("Walberth Gutierrez", "codingwithnotrycatch.com", "w.felipe.gutierrez@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}

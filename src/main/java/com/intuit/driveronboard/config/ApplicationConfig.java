package com.intuit.driveronboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Class containing all common configuration and beans
 */
@Configuration
public class ApplicationConfig {
    /**
     * Bean to configure swagger docket
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intuit.driveronboard.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

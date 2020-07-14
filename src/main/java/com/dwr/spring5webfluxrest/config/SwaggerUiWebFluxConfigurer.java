package com.dwr.spring5webfluxrest.config;




import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;

import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
public class SwaggerUiWebFluxConfigurer implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
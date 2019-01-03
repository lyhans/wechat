package com.example.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebAppConfig  extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TestFirstInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new TestSecondInterceptor()).addPathPatterns("/**");
    }
}
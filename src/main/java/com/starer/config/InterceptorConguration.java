package com.starer.config;

import com.starer.controller.interceptor.UserAuthenticationIdentifyInterceptor;
import com.starer.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 13:30:20
 * @Version: V1.0
 * @Description:
 **/
@Configuration
public class InterceptorConguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 允许指定源
                .allowedMethods("*") // 允许所有方法（GET, POST, PUT, DELETE, OPTIONS等）
                .allowedHeaders("*") // 允许所有头
                .allowCredentials(true); // 允许携带cookie
    }

    private IAuthenticationService authenticationService;

    public InterceptorConguration(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public UserAuthenticationIdentifyInterceptor loadLoginInterceptor() {
        return new UserAuthenticationIdentifyInterceptor(authenticationService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadLoginInterceptor())
                .excludePathPatterns("/login/*");
    }
}

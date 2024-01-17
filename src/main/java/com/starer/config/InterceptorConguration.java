package com.starer.config;

import com.starer.controller.interceptor.UserAuthenticationIdentifyInterceptor;
import com.starer.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 13:30:20
 * @Version: V1.0
 * @Description:
 **/
public class InterceptorConguration implements WebMvcConfigurer {

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

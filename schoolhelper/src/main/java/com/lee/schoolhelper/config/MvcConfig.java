package com.lee.schoolhelper.config;

import com.lee.schoolhelper.utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/user/login",
                        "/category/**",
                        "/user/logout",
                        "/notice/**",
                        "/order/get",
                        "/admin/login"

        );
    }
}

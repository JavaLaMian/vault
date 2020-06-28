package com.vault.demo.config;

import com.vault.demo.filter.ActInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    ActInterceptor actInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器实例注册到拦截器注册中心
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(actInterceptor);
        //添加拦截路径
        interceptorRegistration.addPathPatterns("/user/*");
        //剔除要拦截的路径
        interceptorRegistration.excludePathPatterns("/user/tologin");
        interceptorRegistration.excludePathPatterns("/user/tozhao");
        interceptorRegistration.excludePathPatterns("/user/login");
        interceptorRegistration.excludePathPatterns("/user/add");
        interceptorRegistration.excludePathPatterns("/user/logout");
        interceptorRegistration.excludePathPatterns("/user/padEmail",//判断注册时该邮箱是否存在
                "/user/getMa",//登陆时选择验证码登陆的获取验证码操作
                "/user/updpwd",
                "/user/padAct");
    }
}

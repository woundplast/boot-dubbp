package com.boot.dubbo.consumer.config;

import com.boot.dubbo.consumer.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
//变成 配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 上面这三行 等同于 下面 一行
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
//        interceptorRegistration.addPathPatterns(); //拦截
//        interceptorRegistration.excludePathPatterns(); //不拦截
        // 拦截路径
        String [] addPathPatterns = {
                "/student/**"
        };
        // 不拦截路径
        String [] excludePathPatterns = {
                "/student/sayHi/**"
        };

        // 注册登录拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);

        //注册权限拦截器
//        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);

    }
}

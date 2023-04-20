package com.doj.server.infrastructure.config.web;

import com.doj.server.infrastructure.interceptor.RequestInterceptor;
import com.doj.server.infrastructure.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类描述: 请求拦截统一配置
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String API_PATTERN = "/**/api/**";


    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public UserInterceptor getUserInterceptor() {
        return new UserInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 前后端接口调用路径匹配
        registry.addInterceptor(getRequestInterceptor()).addPathPatterns(API_PATTERN).order(1);
        registry.addInterceptor(getUserInterceptor()).addPathPatterns(API_PATTERN).order(2);
    }
}

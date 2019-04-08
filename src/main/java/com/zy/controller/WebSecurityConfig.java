package com.zy.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        //js,css
//        addInterceptor.excludePathPatterns("/bootstrap/**");
//        addInterceptor.excludePathPatterns("/jquery/**");
//        addInterceptor.excludePathPatterns("/css/**");
//        addInterceptor.excludePathPatterns("/js/**");
//        addInterceptor.excludePathPatterns("/imgs/**");
//        addInterceptor.excludePathPatterns("/vendor/**");
        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/loginPost");
        addInterceptor.excludePathPatterns("/configuration/ui");
        addInterceptor.excludePathPatterns("/swagger-resources");
        addInterceptor.excludePathPatterns("/v2/api-docs");
        addInterceptor.excludePathPatterns("/configuration/security");
        addInterceptor.excludePathPatterns("/api/**");
        addInterceptor.excludePathPatterns("/pubapi/**");
//        addInterceptor.excludePathPatterns("/admin/**");
//        addInterceptor.excludePathPatterns("/zhuYi/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURL().toString());
            System.out.println(request.getServletPath());
            if (session.getAttribute(SESSION_KEY) != null)
                return true;

            // 跳转登录
            String url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }
}
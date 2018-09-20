package com.casic.oarp.datavisual.interceptor;

import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Configuration
public class LoginInterceptor extends WebMvcConfigurerAdapter {

    public final static String SESSION_KEY = "token";

    @Bean
    public LoginInterceptorHandler getLoginInterceptorHanlder() {
        return new LoginInterceptorHandler();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getLoginInterceptorHanlder());
        //排除的路径
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/v2/api-docs**");
        addInterceptor.excludePathPatterns("/swagger*/**");
        addInterceptor.excludePathPatterns("/common/test");
        //拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class LoginInterceptorHandler extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            String token = request.getParameter(SESSION_KEY);

            // 登录失败，则提示登录败的信息
//            if (StringUtils.isEmpty(token)) {
//                throw new ZXFKException(ResultCode.INVALID_TOKEN);
//            } else {
//                return super.preHandle(request, response, handler);
//            }

            // 测试环境
            if ("9385d137-a82f-439c-bbc6-fbb5979edf4e".equalsIgnoreCase(token)) {
                return super.preHandle(request, response, handler);
            } else {
                throw new ZXFKException(ResultCode.INVALID_TOKEN);
            }
        }
    }
    public  static void  main(String[] a) {
        System.out.println(UUID.randomUUID().toString());
    }

}
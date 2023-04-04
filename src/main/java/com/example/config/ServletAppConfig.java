package com.example.config;

import com.example.beans.UserBean;
import com.example.interceptor.CheckLoginInterceptor;
import com.example.interceptor.CheckWriterInterceptor;
import com.example.interceptor.TopMenuInterceptor;
import com.example.service.BoardService;
import com.example.service.TopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class ServletAppConfig implements WebMvcConfigurer {

    @Autowired
    private TopMenuService topMenuService;

    @Autowired
    private BoardService boardService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
        registry.addInterceptor(topMenuInterceptor).addPathPatterns("/**");

        CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
        registry.addInterceptor(checkLoginInterceptor)
                .addPathPatterns("/user/modify", "/user/logout", "/board/*")
                .excludePathPatterns("/board/main");

        CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService);
        registry.addInterceptor(checkWriterInterceptor)
                .addPathPatterns("/board/modify", "/board/delete");
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}

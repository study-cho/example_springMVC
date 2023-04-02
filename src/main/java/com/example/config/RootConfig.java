package com.example.config;

import com.example.beans.UserBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class RootConfig {

    @Bean("loginUserBean")
    @SessionScope
    public UserBean loginUserBean() {
        return new UserBean();
    }
}

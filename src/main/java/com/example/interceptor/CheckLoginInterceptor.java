package com.example.interceptor;

import com.example.beans.UserBean;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CheckLoginInterceptor implements HandlerInterceptor {

    private final UserBean loginUserBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(loginUserBean.isUserLogin() == false) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/user/not_login");
            return false;
        }
        return true;
    }
}

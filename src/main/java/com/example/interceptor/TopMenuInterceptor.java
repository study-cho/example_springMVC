package com.example.interceptor;

import com.example.beans.BoardInfoBean;
import com.example.beans.UserBean;
import com.example.service.TopMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
public class TopMenuInterceptor implements HandlerInterceptor {

    private final TopMenuService topMenuService;
    private final UserBean loginUserBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);
        request.setAttribute("loginUserBean", loginUserBean);

        return true;
    }
}

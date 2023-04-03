package com.example.interceptor;

import com.example.beans.ContentBean;
import com.example.beans.UserBean;
import com.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CheckWriterInterceptor implements HandlerInterceptor {

    private final UserBean loginUserBean;
    private final BoardService boardService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        int content_idx = Integer.parseInt(request.getParameter("content_idx"));

        ContentBean currentContentBean = boardService.getContentInfo(content_idx);
        if(currentContentBean.getContent_writer_idx() != loginUserBean.getUser_idx()) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/board/not_writer");
            return false;
        }

        return true;
    }
}

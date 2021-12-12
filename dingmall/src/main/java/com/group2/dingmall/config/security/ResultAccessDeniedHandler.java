package com.group2.dingmall.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.dingmall.utils.Result;
import com.group2.dingmall.utils.ResultGenerator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lv
 * @Date 2021/12/11 16:35
 * @Description 当访问接口没有权限时，自定义返回姐过
 **/
@Component
public class ResultAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Result result = ResultGenerator.genFailResult("权限不足，请联系管理员");
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}

package com.group2.dingmall.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.dingmall.utils.Result;
import com.group2.dingmall.utils.ResultGenerator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lv
 * @Date 2021/12/11 16:30
 * @Description 当未登录活着token失效访问接口是，返回的结果
 **/


@Component
public class ResultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Result result = ResultGenerator.genFailResult("未登录，请登录");
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}

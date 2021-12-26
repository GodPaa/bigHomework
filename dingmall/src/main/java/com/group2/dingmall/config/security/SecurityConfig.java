package com.group2.dingmall.config.security;

import com.group2.dingmall.controller.user.vo.UserInfoVO;
import com.group2.dingmall.po.User;
import com.group2.dingmall.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author lv
 * @Date 2021/12/11 16:04
 * @Description  Security 配置类
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserService userService;

    @Resource
    private ResultAccessDeniedHandler resultAccessDeniedHandler;

    @Resource
    private ResultAuthenticationEntryPoint resultAuthenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    /**
     * 放行资源，不在这里设置默认拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/user/login",
                "/user/logout",
                "/user/register",
                "/index/**",
                "/book/**",
                "/css/**",
                "/js/**",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha",
                "/comment/**",
                "/adminUser",
                "/adminCategory",
                "/adminBook",
                "/adminUser/{userId}",
                "/adminBook/{bookId}",
                "/adminAddress",
                "/adminAddress/{user_Id}"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用JWT 不需要csrf
        http.csrf()
                .disable()
                // 基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 拦截
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                // 禁用缓存
                .headers()
                .cacheControl();

        // 添加jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权 和 未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(resultAccessDeniedHandler)
                .authenticationEntryPoint(resultAuthenticationEntryPoint);

        // 允许跨域访问
        http.cors();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->{
            User user = userService.getUserInfo(username);
            if (user != null){
                return (UserDetails) user;
            }
            return null;
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return  new JwtAuthenticationTokenFilter();
    }
}

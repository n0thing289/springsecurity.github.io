package com.weikai.config;

import com.weikai.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.spring6.context.SpringContextUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    /**
     * 加载用户信息
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /**
     * 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份验证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 处理身份验证
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /**
     * @Description: 配置SecurityFilterChain过滤器链
     * @Author: 翰戈.summer
     * @Date: 2023/11/17
     * @Param: HttpSecurity
     * @Return: SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //放行哪些页面， 其他页面都需要验证 -> authenticated() ->验证失败跳回登录页面,验证成功返回首页
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(HttpMethod.GET,"/").permitAll()//首页放行
                .requestMatchers(HttpMethod.GET,"/login").permitAll()//登录页面放行
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll() //登录放行
                .requestMatchers(HttpMethod.GET, "/js/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/css/**").permitAll()
//                .anyRequest().authenticated()
        );

//        httpSecurity.authenticationProvider(authenticationProvider());

        //禁用登录页面
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        //禁用登出页面
        httpSecurity.logout(AbstractHttpConfigurer::disable);
        //禁用session
        httpSecurity.sessionManagement(AbstractHttpConfigurer::disable);
        //禁用httpBasic
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        //禁用csrf保护
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        //只要验证了有token就可以放行
//        SpringContextUtils.getApplicationContext().getBean("authenticationManager");
//        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(authentication));
        return httpSecurity.build();
    }
//————————————————
//    版权声明：本文为CSDN博主「翰戈.summer」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_74312711/article/details/134558633
}

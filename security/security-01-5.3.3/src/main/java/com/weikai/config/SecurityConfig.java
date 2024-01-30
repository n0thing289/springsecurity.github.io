package com.weikai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //给role设置可以访问那些页面
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/guest/**").hasRole("guest")
                .antMatchers("/vip/**").hasRole("vip");
        //开启没有登录，就跳转到，程序员写的登录页
        http.formLogin().loginPage("/login").successForwardUrl("/");

        //开启注销功能
        http.logout().logoutUrl("/logout");
    }

    //认证
    /**
     *  There is no PasswordEncoder mapped for the id "null"
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("guest","vip")
                .and()
                .withUser("kuangshen").password(new BCryptPasswordEncoder().encode("123456")).roles("vip");

    }
}

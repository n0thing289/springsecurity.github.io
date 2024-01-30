package com.weikai.config;

import com.weikai.pojo.MyUser;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

@Configuration
public class SecurityConfig {

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Resource
    private UserDetailsService MyUserServiceImpl;

    public AuthenticationManager authenticationManager(){

    }
    //自己写认证逻辑,
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                // 从前端待认证auth对象中获取用户名,密码
                String name = authentication.getName();
                String password = authentication.getCredentials().toString();
                System.out.println("待认证对象name= "+ name + "password=" + password);

                //调UserDetailsService业务的loadUserDetail()方法拿到数据库中的user对象
                UserDetails user = MyUserServiceImpl.loadUserByUsername(name);
                if(user == null || !passwordEncoder().matches(password, user.getPassword())){
                    System.out.println("authenticate 密码不一样");
                    throw new RuntimeException("authenticate 密码不一样");
                }

                return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                System.out.println("authentication.equals(UsernamePasswordAuthenticationToken.class) 一样, 由认证bean authenticate");
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        httpSecurity.formLogin()
//                //.loginPage这个是定义跳转登录页面的url
//                //.loginProcessingUrl代表的是定义的form表单提交的url
//                .loginPage("/login")
//                .loginProcessingUrl("/api/login");
////                .successForwardUrl("/")
////                .failureForwardUrl("/fail");
        http.authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/api/login").permitAll()
                .and()
                .csrf().disable();
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.build();
    }
}

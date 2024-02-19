package com.weikai.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.weikai.pojo.TUser;
import com.weikai.utils.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            JWTUtil.verify(token);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        DecodedJWT decode = JWT.decode(token);
        Long userid = Long.parseLong(decode.getClaim("userid").toString());
        List<String> permissions = decode.getClaim("perms").asList(String.class);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userid, null, new TUser().setPermissions(permissions).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}

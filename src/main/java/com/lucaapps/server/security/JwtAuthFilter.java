package com.lucaapps.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;

    @Autowired
    public JwtAuthFilter(AuthenticationProvider authenticationProvider,
                                      JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String jwt = jwtUtils.resolveToken(servletRequest);
        if(jwt != null && jwtUtils.validateToken(jwt)){
            String username = jwtUtils.getSub(jwt);
            Authentication authentication = this.authenticationProvider.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}














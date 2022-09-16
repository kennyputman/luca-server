package com.lucaapps.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Authentication getAuthentication(String username){
        UserDetails appUser = this.userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());
    }
}

package com.lucaapps.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${luca.auth.token.key}")
    String signKey;

    private Key setKey(){
        return Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String sub) {

        Key key = setKey();
        if (sub == null || sub.equals("")){
            return null;
        }
        Instant exp = Instant.now();
        return Jwts.builder()
                .setSubject(sub)
                .setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(new Date(exp.toEpochMilli() + 3600000))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt){
        Key key = setKey();
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException e){
            return false;
        }
    }

    public String getSub(String jwt) {
        Key key = setKey();
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e){
            return null;
        }
    }

    public String resolveToken(ServletRequest servletRequest) {
        String authHeader = ((HttpServletRequest) servletRequest).getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring("Bearer ".length());
    }
}

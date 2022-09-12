package com.lucaapps.server.domain.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getPrincipal() + "!";
    }


    @PostMapping("register")
    public ResponseEntity<?> registerUser(){

        return ResponseEntity.ok("registered!");
    }
}

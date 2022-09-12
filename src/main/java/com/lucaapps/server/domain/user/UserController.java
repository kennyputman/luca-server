package com.lucaapps.server.domain.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {




    @PostMapping("register")
    public ResponseEntity<?> registerUser(){

        return ResponseEntity.ok("registered!");
    }
}

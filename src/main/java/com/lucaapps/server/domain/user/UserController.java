package com.lucaapps.server.domain.user;

import com.lucaapps.server.domain.user.dtos.AppUserRegisterDto;
import com.lucaapps.server.domain.user.dtos.AppUserResponseDto;
import com.lucaapps.server.domain.user.dtos.UserLoginDto;
import com.lucaapps.server.domain.user.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final AppUserServiceImpl userService;


    @Autowired
    public UserController(AppUserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("register")
    public ResponseEntity<AppUserResponseDto> registerUser(@Valid @RequestBody AppUserRegisterDto userDto){

        AppUserResponseDto newUser = this.userService.register(userDto);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("login")
    public ResponseEntity<AppUserResponseDto> registerUser(@Valid @RequestBody UserLoginDto loginDto){

        AppUserResponseDto loggedInUser = this.userService.login(loginDto);

        return ResponseEntity.ok(loggedInUser);
    }
}









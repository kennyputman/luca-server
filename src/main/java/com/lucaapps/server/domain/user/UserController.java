package com.lucaapps.server.domain.user;

import com.lucaapps.server.domain.user.Dto.AppUserRegisterDto;
import com.lucaapps.server.domain.user.Dto.AppUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final AppUserServiceImpl userService;


    @Autowired
    public UserController(AppUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello(Principal principal) {

        return "Hello, " + principal.getName() + "!";
    }


    @PostMapping("register")
    public ResponseEntity<AppUserResponseDto> registerUser(@Valid @RequestBody AppUserRegisterDto userDto){

        AppUserResponseDto newUser = this.userService.register(userDto);

        return ResponseEntity.ok(newUser);
    }
}









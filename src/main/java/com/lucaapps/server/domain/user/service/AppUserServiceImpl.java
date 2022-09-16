package com.lucaapps.server.domain.user.service;

import com.lucaapps.server.domain.user.dtos.UserLoginDto;
import com.lucaapps.server.domain.user.entities.AppUser;
import com.lucaapps.server.domain.user.AppUserRepository;
import com.lucaapps.server.domain.user.dtos.AppUserRegisterDto;
import com.lucaapps.server.domain.user.dtos.AppUserResponseDto;
import com.lucaapps.server.exception.AppException;
import com.lucaapps.server.exception.Error;
import com.lucaapps.server.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @Transactional
    public AppUserResponseDto register(AppUserRegisterDto userDto) {
        this.appUserRepository.findByEmail(userDto.getEmail()).ifPresent(entity -> {
            throw new AppException(Error.DUPLICATE_USER);
        });
        AppUser appUser = AppUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        AppUser newUser = this.appUserRepository.save(appUser);

        String token = jwtUtils.generateToken(newUser.getUsername());
        return userMapper(newUser, token);

    }

    @Override
    public AppUserResponseDto login(UserLoginDto loginDto) {
        AppUser loggedInUser = this.appUserRepository.findByEmail(loginDto.getEmail()).filter(user ->
                passwordEncoder.matches(loginDto.getPassword(), user.getPassword())
        ).orElseThrow(() -> new AppException(Error.INVALID_LOGIN_INFO));

        String token = jwtUtils.generateToken(loggedInUser.getUsername());
        return userMapper(loggedInUser, token);

    }


    private AppUserResponseDto userMapper(AppUser user, String token) {
        return AppUserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}

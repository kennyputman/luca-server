package com.lucaapps.server.domain.user.service;

import com.lucaapps.server.domain.user.dtos.AppUserRegisterDto;
import com.lucaapps.server.domain.user.dtos.AppUserResponseDto;
import com.lucaapps.server.domain.user.dtos.UserLoginDto;

public interface AppUserService {
    AppUserResponseDto register(AppUserRegisterDto user);

    AppUserResponseDto login(UserLoginDto loginDto);
}

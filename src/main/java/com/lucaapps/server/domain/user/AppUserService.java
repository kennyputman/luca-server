package com.lucaapps.server.domain.user;

import com.lucaapps.server.domain.user.Dto.AppUserRegisterDto;
import com.lucaapps.server.domain.user.Dto.AppUserResponseDto;

public interface AppUserService {
    AppUserResponseDto register(AppUserRegisterDto user);
}

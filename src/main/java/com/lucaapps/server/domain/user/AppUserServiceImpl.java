package com.lucaapps.server.domain.user;

import com.lucaapps.server.domain.user.Dto.AppUserRegisterDto;
import com.lucaapps.server.domain.user.Dto.AppUserResponseDto;
import com.lucaapps.server.exception.AppException;
import com.lucaapps.server.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public AppUserResponseDto register(AppUserRegisterDto userDto) {
        this.appUserRepository.findByEmail(userDto.getEmail()).ifPresent(entity -> {throw new AppException(Error.DUPLICATE_USER);});
        AppUser appUser = AppUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        AppUser newUser = this.appUserRepository.save(appUser);

        return AppUserResponseDto.builder()
                .id(newUser.getId())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .build();
    }
}

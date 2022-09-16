package com.lucaapps.server.domain.user.service;

import com.lucaapps.server.domain.user.AppUserRepository;
import com.lucaapps.server.domain.user.entities.AppUser;
import com.lucaapps.server.exception.AppException;
import com.lucaapps.server.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->
                new AppException(Error.USERNAME_NOT_FOUND));
    }

}

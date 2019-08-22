package com.step.salehome.service;

import com.step.salehome.exceptions.DuplicateEmailException;
import com.step.salehome.exceptions.InvalidTokenException;
import com.step.salehome.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws DuplicateEmailException;

    void updateUserStatusByToken(String token, String newToken) throws InvalidTokenException;

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
package com.step.salehome.service;

import com.step.salehome.exceptions.DuplicateEmailException;
import com.step.salehome.exceptions.InvalidTokenException;
import com.step.salehome.model.User;
import com.step.salehome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) throws DuplicateEmailException {
        userRepository.registerUser(user);
    }

    @Override
    public void updateUserStatusByToken(String token, String newToken) throws InvalidTokenException {
        userRepository.updateUserStatusByToken(token, newToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.loginUser(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return user;
    }
}

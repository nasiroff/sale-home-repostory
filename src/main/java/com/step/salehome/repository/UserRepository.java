package com.step.salehome.repository;

import com.step.salehome.exceptions.DuplicateEmailException;
import com.step.salehome.exceptions.InvalidTokenException;
import com.step.salehome.model.User;

public interface UserRepository {
    void registerUser(User user) throws DuplicateEmailException;

    User loginUser(String email);

    void updateUserStatusByToken(String token, String newToken) throws InvalidTokenException;
}

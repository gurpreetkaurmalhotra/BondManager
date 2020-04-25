package com.gurpreet.service;

import com.gurpreet.model.User;
import com.gurpreet.request.CreateUserRequest;

public interface UserService {
    User createUser(CreateUserRequest request) throws com.gurpreet.exception.BondManagerException;
}

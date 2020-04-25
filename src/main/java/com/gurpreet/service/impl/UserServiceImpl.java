package com.gurpreet.service.impl;

import com.gurpreet.model.User;
import com.gurpreet.repository.UserRepository;
import com.gurpreet.request.CreateUserRequest;
import com.gurpreet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser (CreateUserRequest request) throws com.gurpreet.exception.BondManagerException {
        User user = User.builder().userType(request.getUserType()).name(request.getName()).build();
        return userRepository.createUser(user);
    }
}

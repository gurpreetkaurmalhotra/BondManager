package com.gurpreet.repository;

import com.gurpreet.model.User;

public interface UserRepository {

    User createUser(User sellerDetails);

    User getUserByUserId(Integer userId);


}

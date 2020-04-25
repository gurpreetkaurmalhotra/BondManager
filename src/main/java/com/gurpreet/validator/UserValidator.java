package com.gurpreet.validator;

import com.gurpreet.request.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class UserValidator {

    public static boolean validUser(CreateUserRequest request) throws com.gurpreet.exception.BondManagerException {
        return StringUtils.isNotEmpty(request.getName()) && request.getUserType() != null;
    }

}

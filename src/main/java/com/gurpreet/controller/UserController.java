package com.gurpreet.controller;

import com.gurpreet.dto.BondManagerResponseEntity;
import com.gurpreet.enums.ErrorCode;
import com.gurpreet.request.CreateUserRequest;
import com.gurpreet.service.UserService;
import com.gurpreet.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public BondManagerResponseEntity createUser(@NotNull @RequestBody CreateUserRequest request) {
        try {
            if(!UserValidator.validUser(request)) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            return BondManagerResponseEntity.buildSuccessResponse(userService.createUser(request));
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in createUser due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }

}

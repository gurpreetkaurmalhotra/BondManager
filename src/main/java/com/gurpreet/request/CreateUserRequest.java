package com.gurpreet.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gurpreet.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {

    private String name;
    private UserType userType;

}

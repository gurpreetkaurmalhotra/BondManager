package com.gurpreet.response;

import lombok.Data;

@Data
public class ResponseWrapper {

    private Status status;

    private int httpCode;

    private String message;

    private Object payload;

    public enum Status {
        OK, ERROR, PARTIAL_SUCCESS
    }
}

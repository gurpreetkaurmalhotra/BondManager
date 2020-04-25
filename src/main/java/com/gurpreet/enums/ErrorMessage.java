package com.gurpreet.enums;

public enum ErrorMessage {

    COMMON_EXCEPTION("Common Exception");

    private String message;

    ErrorMessage (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

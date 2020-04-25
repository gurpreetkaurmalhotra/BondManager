package com.gurpreet.enums;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    DATABASE_ERROR (500, "Database Error"),
    BAD_REQUEST(400, "Bad Request"),
    AUTHORIZATION_FAIL(401, "User not Authorized"),
    DATA_NOT_FOUND(404, "Data Not Found");

    private int code;
    private String message;

    ErrorCode (int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

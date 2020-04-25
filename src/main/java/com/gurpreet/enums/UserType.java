package com.gurpreet.enums;

public enum UserType {

    ADMIN(1),
    CUSTOMER(2),
    SALESPERSON(3)
    ;

    private int userType;

    UserType (int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

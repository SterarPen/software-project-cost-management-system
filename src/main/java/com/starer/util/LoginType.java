package com.starer.util;

import java.util.Arrays;
import java.util.List;

public enum LoginType {

    LOGIN_ID("id", 1),
    LOGIN_PHONE("phone", 2),
    LOGIN_EMAIL("email", 3);

    public static final List<String> strings  = Arrays.asList("id", "phone", "email");
    public static final List<Integer> ints = Arrays.asList(1, 2, 3);

    private String loginTypeString;
    private int loginTypeInt;

    LoginType(String loginTypeString, int loginTypeInt) {
        this.loginTypeString = loginTypeString;
        this.loginTypeInt = loginTypeInt;
    }

    public static LoginType getLoginTypeByString(String login) {
        switch (login) {
            case "id":
                return LOGIN_ID;
            case "phone":
                return LOGIN_PHONE;
            case "email":
                return LOGIN_EMAIL;
            default:
                return null;
        }
    }

    public static LoginType getLoginTypeByInt(int login) {
        switch (login) {
            case 1:
                return LOGIN_ID;
            case 2:
                return LOGIN_PHONE;
            case 3:
                return LOGIN_EMAIL;
            default:
                return null;
        }
    }

    public String getLoginTypeString() {
        return loginTypeString;
    }

    public int getLoginTypeInt() {
        return loginTypeInt;
    }
}

package com.starer.util;

import java.util.Arrays;
import java.util.List;

public enum RoleType {
    ADMIN("manager", 0),
    BUYER("buyer", 1),
    MANAGER("projectMgr", 2);

    public static final List<String> strings = Arrays.asList("manager", "buyer", "projectMgr");
    public static final List<Integer> ints = Arrays.asList(0, 1, 2);

    public final String roleTypeString;
    public final int roleTypeInt;

    RoleType(String roleTypeString, int roleTypeInt) {
        this.roleTypeString = roleTypeString;
        this.roleTypeInt = roleTypeInt;
    }

    public static RoleType getRoleTypeByInt(int role) {

        switch (role) {
            case 0:
                return ADMIN;
            case 1:
                return BUYER;
            case 2:
                return MANAGER;
            default:
                return null;
        }
    }

    public static RoleType getLoginTypeByString(String role) {
        switch (role) {
            case "manager":
                return ADMIN;
            case "buyer":
                return BUYER;
            case "projectMgr":
                return MANAGER;
            default:
                return null;
        }
    }

    public String getRoleTypeString() {
        return roleTypeString;
    }

    public int getRoleTypeInt() {
        return roleTypeInt;
    }
}

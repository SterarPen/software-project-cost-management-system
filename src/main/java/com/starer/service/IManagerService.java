package com.starer.service;

import com.starer.pojo.entity.user.Manager;
import com.starer.util.LoginType;

public interface IManagerService {

    Manager register(String managerName, String password, String email, String phone, String createBy,
                     String projectId);

    Manager login(String managerId, String password, LoginType loginType);
    boolean updateUserName(String managerId, String newName);
    boolean updatePassword(String managerId, String oldPassword, String newPassword);
    boolean updatePassword(String managerId, String newPassword);

    int updateManager(Manager manager);
    Manager getInformation(String managerId, String phone, String email);
    Manager[] getAllManager(String managerId, String phone, String email);

    int deleteManager(String managerId);
}

package com.starer.pojo.entity.user;

import com.starer.pojo.entity.super_class.CommonUser;

/**
 * @Author: pengxiong
 * @Date: 2023/11/26 14:21:13
 * @Version: V1.0
 * @Description: 系统管理员
 **/
public class Admin extends CommonUser {

    private String adminId;
    private String adminName;
    private String password;
    private String updateTime;
    private String oldPassword;

    public Admin() {
    }

    public Admin(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='" + adminId + '\'' +
                ", managerName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}

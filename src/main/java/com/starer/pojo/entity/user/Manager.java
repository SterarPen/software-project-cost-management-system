package com.starer.pojo.entity.user;

import com.starer.pojo.entity.super_class.CommonUser;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/11/26 14:21:47
 * @Version: V1.0
 * @Description: 
 **/
public class Manager extends CommonUser {

    private String managerId;
    private String managerName;
    private String password;
    private String phone;
    private String email;
    private Timestamp createTime;
    private String createBy;
    private String handlingProject;

    public Manager() {
    }

    public Manager(String managerName, String password, String phone, String email, String createBy) {
        this.managerName = managerName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createBy = createBy;
    }

    public Manager(String managerId, String managerName, String password, String phone, String email,
                   Timestamp createTime, String createBy, String handlingProject) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
        this.createBy = createBy;
        this.handlingProject = handlingProject;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "DeveloperManager{" +
                "id='" + managerId + '\'' +
                ", name='" + managerName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", handlingProject='" + handlingProject + '\'' +
                '}';
    }

    public String getHandlingProject() {
        return handlingProject;
    }

    public void setHandlingProject(String handlingProject) {
        this.handlingProject = handlingProject;
    }

}

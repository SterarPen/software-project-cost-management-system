package com.starer.pojo.entity.user;

import com.starer.pojo.entity.super_class.CommonUser;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/11/26 14:22:08
 * @Version: V1.0
 * @Description: 甲方
 **/
public class Buyer extends CommonUser {

    private String id;
    private String name;
    private String password;
    private Timestamp createTime;
    private Timestamp destroyTime;
    private String phone;
    private String email;
    private String createBy;
    private String handlingProject;

    public Buyer() {
    }

    /**
     * use this constructor to create a object for inserting a new Buyer in database.
     * @param name
     * @param password
     * @param phone
     * @param email
     * @param createBy
     */
    public Buyer(String name, String password, String phone, String email, String createBy, String handlingProject) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createBy = createBy;
        this.handlingProject = handlingProject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(Timestamp destroyTime) {
        this.destroyTime = destroyTime;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getHandlingProject() {
        return handlingProject;
    }

    public void setHandlingProject(String handlingProject) {
        this.handlingProject = handlingProject;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", destroyTime=" + destroyTime +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createBy='" + createBy + '\'' +
                ", handlingProject='" + handlingProject + '\'' +
                '}';
    }
}

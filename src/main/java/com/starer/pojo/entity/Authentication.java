package com.starer.pojo.entity;

import java.sql.Timestamp;

public class Authentication {

    private Integer id;
    private String userId;
    private String token;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private int expire;
    private int role;

    public Authentication(String userId, String token, int role) {
        this.userId = userId;
        this.token = token;
        this.role = role;
    }

    public Authentication(Integer id, String userId, String token, Timestamp createTime, Timestamp deleteTime,
                          int expire, int role) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.expire = expire;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", expire=" + expire +
                ", role=" + role +
                '}';
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}

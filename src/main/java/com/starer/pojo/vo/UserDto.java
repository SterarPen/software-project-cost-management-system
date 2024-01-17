package com.starer.pojo.vo;

public class UserDto {

    private String userId;

    private String userName;
    private String phone;
    private String email;

    private String createTime;

    private String handlingProject;

    private int role;

    public UserDto() {
    }

    public UserDto(String userId, String userName, String phone, String email, int role) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public UserDto(String userId, String userName, String phone, String email, String createTime, int role) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
        this.role = role;
    }

    public UserDto(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserDto(String userId, String userName, String phone, String email,
                   String createTime, String handlingProject, int role) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
        this.handlingProject = handlingProject;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getHandlingProject() {
        return handlingProject;
    }

    public void setHandlingProject(String handlingProject) {
        this.handlingProject = handlingProject;
    }
}

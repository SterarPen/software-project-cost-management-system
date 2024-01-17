package com.starer.pojo.entity;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 17:06:19
 * @Version: V1.0
 * @Description:
 **/
public class Project {
    private String projectId;
    private String projectName;
    private String buyerId;
    private Date createTime;
    private Date finishTime;
    private String developerManagerId;


    public Project() {
    }

    public Project(String projectId, String projectName, String buyerId, Date createTime, Date finishTime, String developerManagerId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.buyerId = buyerId;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.developerManagerId = developerManagerId;
    }

    public Project(String projectName, String buyerId, String developerManagerId) {
        this.projectName = projectName;
        this.buyerId = buyerId;
        this.developerManagerId = developerManagerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getDeveloperManagerId() {
        return developerManagerId;
    }

    public void setDeveloperManagerId(String developerManagerId) {
        this.developerManagerId = developerManagerId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", developerManagerId='" + developerManagerId + '\'' +
                '}';
    }
}

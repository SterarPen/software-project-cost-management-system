package com.starer.pojo.vo;


import com.starer.pojo.entity.Project;

import java.sql.Date;

public class ProjectDto {

    private String projectId;
    private String projectName;
    private Date createTime;
    private Date finishTime;

    public ProjectDto(String projectId, String projectName, Date createTime, Date finishTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.createTime = createTime;
        this.finishTime = finishTime;
    }

    public ProjectDto(Project project) {
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.createTime = project.getCreateTime();
        this.finishTime = project.getFinishTime();
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
}

package com.starer.pojo.entity;

import java.sql.Timestamp;

public class Demand {

    private String demandId;
    private String demandContent;
    private Timestamp createTime;
    private int status;
    private String projectId;


    public Demand(String projectId,String demandContent) {
        this.demandContent = demandContent;
        this.projectId = projectId;
    }

    public Demand(String demandId, String demandContent, Timestamp createTime, int status) {
        this.demandId = demandId;
        this.demandContent = demandContent;
        this.createTime = createTime;
        this.status = status;
    }

    public Demand(String demandId, String demandContent, Timestamp createTime, int status, String projectId) {
        this.demandId = demandId;
        this.demandContent = demandContent;
        this.createTime = createTime;
        this.status = status;
        this.projectId = projectId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getDemandContent() {
        return demandContent;
    }

    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "demandId='" + demandId + '\'' +
                ", demandContent='" + demandContent + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}

package com.starer.pojo.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 16:14:24
 * @Version: V1.0
 * @Description:
 **/
public class Task {

    private String taskId;
    private String taskContent;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private BigDecimal cost;
    private String stageId;

    public Task() {
    }

    public Task(String taskContent, Date startTime, Date endTime, BigDecimal cost, String stageId) {
        this.taskContent = taskContent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.stageId = stageId;
    }

    public Task(String taskId, String taskContent,
                Date startTime, Date endTime,
                Integer status, BigDecimal cost,
                String stageId) {
        this.taskId = taskId;
        this.taskContent = taskContent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.cost = cost;
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", cost=" + cost +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}

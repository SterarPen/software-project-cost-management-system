package com.starer.pojo.vo;

public class TaskDto {

    private String taskId;
    private String taskContent;
    private String startTime;
    private String endTime;
    private int status;
    private String cost;
    private String stageName;

    public TaskDto(String taskId, String taskContent,
                   String startTime, String endTime,
                   int status, String cost,
                   String stageName) {
        this.taskId = taskId;
        this.taskContent = taskContent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.cost = cost;
        this.stageName = stageName;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId='" + taskId + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", cost='" + cost + '\'' +
                ", stageName='" + stageName + '\'' +
                '}';
    }
}

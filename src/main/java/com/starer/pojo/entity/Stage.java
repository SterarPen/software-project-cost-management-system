package com.starer.pojo.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 11:56:36
 * @Version: V1.0
 * @Description:
 **/
public class Stage {

    private String stageId;
    private String stageName;
    private Date startTime;
    private Date costTime;
    private BigDecimal predictCost;

    public Stage() {
    }

    public Stage(String stageId, String stageName, Date startTime, Date costTime, BigDecimal predictCost) {
        this.stageId = stageId;
        this.stageName = stageName;
        this.startTime = startTime;
        this.costTime = costTime;
        this.predictCost = predictCost;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCostTime() {
        return costTime;
    }

    public void setCostTime(Date costTime) {
        this.costTime = costTime;
    }

    public BigDecimal getPredictCost() {
        return predictCost;
    }

    public void setPredictCost(BigDecimal predictCost) {
        this.predictCost = predictCost;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stageId='" + stageId + '\'' +
                ", stageName='" + stageName + '\'' +
                ", startTime=" + startTime +
                ", costTime=" + costTime +
                ", predictCost=" + predictCost +
                '}';
    }
}

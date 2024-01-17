package com.starer.pojo.vo;

import com.alibaba.fastjson2.JSON;

import java.sql.Date;
import java.util.Arrays;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 20:56:07
 * @Version: V1.0
 * @Description:
 **/
public class ProjectInformation {
    String projectName;
    String buyerName;
    String buyerPhone;
    String buyerEmail;
    Date createTime;
    Date finishTime;
    String[] stages;
    String[] cost;
    String managerName;
    int[] finishTaskNum;
    int[] noFinishTaskNum;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getCreateTime() {
        return JSON.toJSONString(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return JSON.toJSONString(finishTime);
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getStages() {
        return JSON.toJSONString(stages);
    }

    public String[] getStages1() {
        return stages;
    }

    public void setStages(String[] stages) {
        this.stages = stages;
    }

    public String getCost() {
        return JSON.toJSONString(cost);
    }

    public String[] getCost1() {
        return cost;
    }

    public void setCost(String[] cost) {
        this.cost = cost;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getFinishTaskNum() {
        return JSON.toJSONString(finishTaskNum);
    }

    public int[] getFinishTaskNum1() {
        return finishTaskNum;
    }

    public void setFinishTaskNum(int[] finishTaskNum) {
        this.finishTaskNum = finishTaskNum;
    }

    public String getNoFinishTaskNum() {
        return JSON.toJSONString(noFinishTaskNum);
    }
    public int[] getNoFinishTaskNum1() {
        return noFinishTaskNum;
    }

    public void setNoFinishTaskNum(int[] noFinishTaskNum) {
        this.noFinishTaskNum = noFinishTaskNum;
    }

    @Override
    public String toString() {
        return "{" +
                "projectName:'" + projectName + '\'' +
                ", buyerName:'" + buyerName + '\'' +
                ", buyerPhone:'" + buyerPhone + '\'' +
                ", buyerEmail:'" + buyerEmail + '\'' +
                ", createTime:'" + createTime + '\'' +
                ", finishTime:'" + finishTime + '\'' +
                ", stages:" + JSON.toJSONString(stages) +
                ", cost:" + Arrays.toString(cost) +
                ", managerName:'" + managerName + '\'' +
                ", finishTaskNum:" + Arrays.toString(finishTaskNum) +
                ", noFinishTaskNum:" + Arrays.toString(noFinishTaskNum) +
                '}';
    }
}



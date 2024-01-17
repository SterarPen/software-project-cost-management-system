package com.starer.pojo.vo;

public class StageTaskNum {

    // 项目所有阶段的名称
    private String[] stages;
    // 每个阶段已完成的任务数量
    private int[] finishTaskNum;
    // 每个阶段未完成的任务数量
    private int[] noFinishTaskNum;

    public StageTaskNum(String[] stages, int[] finishTaskNum, int[] noFinishTaskNum) {
        this.stages = stages;
        this.finishTaskNum = finishTaskNum;
        this.noFinishTaskNum = noFinishTaskNum;
    }

    public String[] getStages() {
        return stages;
    }

    public void setStages(String[] stages) {
        this.stages = stages;
    }

    public int[] getFinishTaskNum() {
        return finishTaskNum;
    }

    public void setFinishTaskNum(int[] finishTaskNum) {
        this.finishTaskNum = finishTaskNum;
    }

    public int[] getNoFinishTaskNum() {
        return noFinishTaskNum;
    }

    public void setNoFinishTaskNum(int[] noFinishTaskNum) {
        this.noFinishTaskNum = noFinishTaskNum;
    }
}

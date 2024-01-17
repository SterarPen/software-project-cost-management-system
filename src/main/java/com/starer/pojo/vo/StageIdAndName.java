package com.starer.pojo.vo;

/**
 * @Author: pengxiong
 * @Date: 2024/01/02 13:08:23
 * @Version: V1.0
 * @Description:
 **/
public class StageIdAndName {

    private String stageId;
    private String stageName;

    public StageIdAndName(String stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
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
}

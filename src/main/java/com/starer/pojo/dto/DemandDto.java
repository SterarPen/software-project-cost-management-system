package com.starer.pojo.dto;

import com.starer.pojo.entity.Demand;
import com.starer.util.DateTimeUtil;


/**
 * @Author: pengxiong
 * @Date: 2023/12/30 18:26:46
 * @Version: V1.0
 * @Description:
 **/
public class DemandDto {

    private String demandId;
    private String demandContent;
    private String createTime;
    private String status;

    public DemandDto(String demandId, String demandContent, String createTime, String status) {
        this.demandId = demandId;
        this.demandContent = demandContent;
        this.createTime = createTime;
        this.status = status;
    }

    public DemandDto(Demand demand) {
        this.demandId = demand.getDemandId();
        this.demandContent = demand.getDemandContent();
        this.createTime = DateTimeUtil.convertTimestampToString(demand.getCreateTime());
        switch (demand.getStatus()) {
            case 0:
                this.status = "待处理";
                break;
            case 1:
                this.status = "未通过";
                break;
            case 2:
                this.status = "已通过";
                break;
            case 3:
                this.status = "待删除";
                break;
            case -1:
                this.status = "已删除";
                break;
        }
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

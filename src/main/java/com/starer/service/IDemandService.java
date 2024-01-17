package com.starer.service;

import com.starer.dao.IDemandDao;
import com.starer.pojo.entity.Demand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface IDemandService {

    Demand queryDemandById(String demandId);
    Demand[] queryDemandByProjectId(String projectId);

    Demand[] queryConditionDemand(String projectId, String demandId,
                                  String day, String status, String type);

    int addDemand(Demand demand);
    int agreeDemand(String demandId, boolean agree);
    int updateDemandContent(String demandId, String demandContent);

    int updateDemand(Demand demand);
}

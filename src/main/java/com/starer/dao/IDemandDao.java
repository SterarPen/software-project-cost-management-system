package com.starer.dao;

import com.starer.pojo.entity.Demand;
import org.apache.ibatis.annotations.Param;

public interface IDemandDao {
    Demand selectDemandById(@Param("demandId") String demandId);
    Demand[] selectDemandByProjectId(@Param("projectId") String projectId);

    Demand[] selectConditionalDemand(@Param("projectId") String projectId, @Param("demandId") String demandId,
                                     @Param("day1") String day, @Param("status1") String status,
                                     @Param("type1") String type);
    int insertDemand(Demand demand);
    int updateDemand(Demand demand);

    int deleteDemand(String demandId);

}

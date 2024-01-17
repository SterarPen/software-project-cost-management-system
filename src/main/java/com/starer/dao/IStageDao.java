package com.starer.dao;

import com.starer.pojo.entity.Stage;
import org.apache.ibatis.annotations.Param;

public interface IStageDao {

    Stage selectStageById(@Param("stageId") String stageId);
    Stage[] selectAllStageByProjectId(@Param("projectId") String projectId);

    boolean insertStage(@Param("stage") Stage stage, @Param("projectId") String projectId);
    boolean deleteStage(@Param("stageId") String stageId);
    boolean updateStage(@Param("stage") Stage stage, @Param("projectId") String projectId);
}

package com.starer.service;

import com.starer.pojo.entity.Stage;

import java.math.BigDecimal;

public interface IStageService {

    BigDecimal queryPredictCostOfProject(String projectId);

    Stage queryStageById(String stageId);

    Stage[] queryAllStagesForAProject(String projectId);

    int deleteStage(String stageId);
    int updateStage(Stage stage, String projectId);

    int addStage(Stage stage, String projectId);
}

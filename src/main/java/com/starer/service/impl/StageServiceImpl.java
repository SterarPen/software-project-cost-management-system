package com.starer.service.impl;

import com.starer.dao.IStageDao;
import com.starer.pojo.entity.Stage;
import com.starer.service.IStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 13:05:52
 * @Version: V1.0
 * @Description:
 **/
@Service
public class StageServiceImpl implements IStageService {

    private IStageDao stageDao;

    @Autowired
    public StageServiceImpl(IStageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Override
    public BigDecimal queryPredictCostOfProject(String projectId) {
        Stage[] stages = stageDao.selectAllStageByProjectId(projectId);
        BigDecimal bigDecimal = new BigDecimal("0");
        for (Stage stage : stages) {
            bigDecimal = bigDecimal.add(stage.getPredictCost());
        }
        return bigDecimal;
    }

    @Override
    public Stage queryStageById(String stageId) {
        return stageDao.selectStageById(stageId);
    }

    @Override
    public Stage[] queryAllStagesForAProject(String projectId) {
        Stage[] stages = stageDao.selectAllStageByProjectId(projectId);
        return stages;
    }

    @Override
    public int deleteStage(String stageId) {
        boolean b = stageDao.deleteStage(stageId);
        return b?1:0;
    }

    @Override
    public int updateStage(Stage stage, String projectId) {
        boolean b = stageDao.updateStage(stage, projectId);
        return b?1:0;
    }

    @Override
    public int addStage(Stage stage, String projectId) {
        return stageDao.insertStage(stage, projectId)?1:0;
    }
}

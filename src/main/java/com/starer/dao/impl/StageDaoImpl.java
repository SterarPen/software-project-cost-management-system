package com.starer.dao.impl;

import com.starer.dao.IStageDao;
import com.starer.pojo.entity.Stage;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 12:15:43
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class StageDaoImpl implements IStageDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public StageDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Stage selectStageById(String stageId) {
        return sqlSessionTemplate.getMapper(IStageDao.class).selectStageById(stageId);
    }

    @Override
    public Stage[] selectAllStageByProjectId(String projectId) {
        return sqlSessionTemplate.getMapper(IStageDao.class).selectAllStageByProjectId(projectId);
    }

    @Override
    public boolean insertStage(Stage stage, String projectId) {
        return sqlSessionTemplate.getMapper(IStageDao.class).insertStage(stage, projectId);
    }

    @Override
    public boolean deleteStage(String stageId) {
        return sqlSessionTemplate.getMapper(IStageDao.class).deleteStage(stageId);
    }

    @Override
    public boolean updateStage(Stage stage, String projectId) {
        return sqlSessionTemplate.getMapper(IStageDao.class).updateStage(stage, projectId);
    }
}

package com.starer.dao.impl;

import com.starer.dao.IDemandDao;
import com.starer.pojo.entity.Demand;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DemandDaoImpl implements IDemandDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    @Override
    public Demand selectDemandById(String demandId) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).selectDemandById(demandId);
    }

    @Override
    public Demand[] selectDemandByProjectId(String projectId) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).selectDemandByProjectId(projectId);
    }

    @Override
    public Demand[] selectConditionalDemand(String projectId, String demandId, String day,
                                            String status, String type) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).selectConditionalDemand(projectId, demandId,
                day, status, type);
    }

    @Override
    public int insertDemand(Demand demand) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).insertDemand(demand);
    }

    @Override
    public int updateDemand(Demand demand) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).updateDemand(demand);
    }

    @Override
    public int deleteDemand(String demandId) {
        return sqlSessionTemplate.getMapper(IDemandDao.class).deleteDemand(demandId);
    }
}

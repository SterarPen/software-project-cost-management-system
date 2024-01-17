package com.starer.service.impl;

import com.starer.dao.IDemandDao;
import com.starer.pojo.entity.Demand;
import com.starer.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandServiceImpl implements IDemandService {

    private IDemandDao demandDao;

    @Autowired
    public DemandServiceImpl(IDemandDao demandDao) {
        this.demandDao = demandDao;
    }

    @Override
    public Demand queryDemandById(String demandId) {
        return demandDao.selectDemandById(demandId);
    }

    @Override
    public Demand[] queryDemandByProjectId(String projectId) {
        return demandDao.selectDemandByProjectId(projectId);
    }

    @Override
    public Demand[] queryConditionDemand(String projectId, String demandId, String day, String status, String type) {
        return demandDao.selectConditionalDemand(projectId, demandId, day, status,type);
    }

    @Override
    public int addDemand(Demand demand) {
        return demandDao.insertDemand(demand);
    }

    @Override
    public int agreeDemand(String demandId, boolean agree) {
        if(agree)
            return demandDao.updateDemand(
                    new Demand(demandId, null, null, 1, null));
        else
            return demandDao.updateDemand(
                    new Demand(demandId, null, null, 0, null));
    }

    @Override
    public int updateDemandContent(String demandId, String demandContent) {
        return demandDao.updateDemand(
                new Demand(demandId,  demandContent, null,0, null));
    }

    @Override
    public int updateDemand(Demand demand) {
        return demandDao.updateDemand(demand);
    }
}

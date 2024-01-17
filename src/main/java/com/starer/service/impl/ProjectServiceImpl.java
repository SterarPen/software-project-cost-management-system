package com.starer.service.impl;

import com.starer.dao.IBuyerDao;
import com.starer.dao.IManagerDao;
import com.starer.dao.IProjectDao;
import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 19:18:26
 * @Version: V1.0
 * @Description:
 **/
@Service
public class ProjectServiceImpl implements IProjectService {

    private IProjectDao projectDao;
    private IBuyerDao buyerDao;
    private IManagerDao managerDao;

    @Autowired
    public ProjectServiceImpl(IProjectDao projectDao, IBuyerDao buyerDao, IManagerDao managerDao) {
        this.projectDao = projectDao;
        this.buyerDao = buyerDao;
        this.managerDao = managerDao;
    }

    @Override
    public Project[] getAllProject() {
        return projectDao.selectProjects(null, null);
    }

    @Override
    public Project getProjectInformationById(String projectId) {
        return projectDao.queryProjectById(projectId);
    }

    @Override
    public String[] getAllProjectIdOfBuyer(String buyerId) {
        return projectDao.queryProjectsIdByBuyerId(buyerId);
    }

    @Override
    public String[] getAllProjectIdOfDeveloperManager(String developerManagerId) {
        return projectDao.queryProjectsIdByDeveloperManagerId(developerManagerId);
    }

    @Override
    public int addProject(String projectName, String buyerId, String developerManagerId) {
        Buyer buyer = buyerDao.queryBuyer(buyerId, null, null)[0];
        if(buyer == null) return -1;
        Manager developer = managerDao.queryManager(developerManagerId, null, null)[0];
        if(developer == null) return -2;
        Project project = new Project(projectName, buyerId, developerManagerId);
        boolean b = projectDao.insertProject(project);
        if(b)
            return 1;
        else
            return 0;
    }

    @Override
    public int deleteProject(String projectId) {
        boolean b = projectDao.deleteProject(projectId);
        if(b)
            return 1;
        else
            return 0;
    }
}

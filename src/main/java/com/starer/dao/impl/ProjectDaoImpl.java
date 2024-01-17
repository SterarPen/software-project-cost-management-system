package com.starer.dao.impl;


import com.starer.dao.IProjectDao;
import com.starer.pojo.entity.Project;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 17:46:52
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class ProjectDaoImpl implements IProjectDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ProjectDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    @Override
    public Project queryProjectById(String id) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).queryProjectById(id);
    }

    @Override
    public Project[] queryProjectsByBuyerId(String buyerId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).queryProjectsByBuyerId(buyerId);
    }

    @Override
    public Project[] queryProjectsByDeveloperManagerId(String developerManagerId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).queryProjectsByDeveloperManagerId(developerManagerId);
    }

    @Override
    public String[] queryProjectsIdByBuyerId(String buyerId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).queryProjectsIdByBuyerId(buyerId);
    }

    @Override
    public String[] queryProjectsIdByDeveloperManagerId(String developerManagerId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).queryProjectsIdByDeveloperManagerId(developerManagerId);
    }

    @Override
    public Project[] selectProjects(String buyerId, String developerManagerId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).selectProjects(buyerId, developerManagerId);
    }

    @Override
    public boolean insertProject(Project project) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).insertProject(project);
    }

    @Override
    public boolean deleteProject(String projectId) {
        return sqlSessionTemplate.getMapper(IProjectDao.class).deleteProject(projectId);
    }
}

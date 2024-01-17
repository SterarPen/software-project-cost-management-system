package com.starer.dao.impl;

import com.starer.dao.IManagerDao;
import com.starer.pojo.entity.user.Manager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/11/26 21:58:53
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class ManagerDaoImpl implements IManagerDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    @Override
    public Manager[] queryManager(String id, String phone, String email) {
        return sqlSessionTemplate.getMapper(IManagerDao.class).queryManager(id, phone, email);
    }

    @Override
    public int updateManager(Manager manager) {
        return sqlSessionTemplate.getMapper(IManagerDao.class).updateManager(manager);
    }

    @Override
    public int addManager(Manager manager) {
        return sqlSessionTemplate.getMapper(IManagerDao.class).addManager(manager);
    }

    @Override
    public int deleteManager(String managerId, Timestamp destroyTime) {
        return sqlSessionTemplate.getMapper(IManagerDao.class).deleteManager(managerId, destroyTime);
    }
}

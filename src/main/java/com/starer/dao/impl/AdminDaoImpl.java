package com.starer.dao.impl;

import com.starer.dao.IAdminDao;
import com.starer.pojo.entity.user.Admin;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @Author: pengxiong
 * @Date: 2023/11/26 22:02:00
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class AdminDaoImpl implements IAdminDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public AdminDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Admin queryAdminById(String adminId) {
        return sqlSessionTemplate.getMapper(IAdminDao.class).queryAdminById(adminId);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return sqlSessionTemplate.getMapper(IAdminDao.class).updateAdmin(admin);
    }

    @Override
    public int addAdmin(Admin admin) {
        return sqlSessionTemplate.getMapper(IAdminDao.class).addAdmin(admin);
    }

    @Override
    public int deleteAdmin(String adminId) {
        return sqlSessionTemplate.getMapper(IAdminDao.class).deleteAdmin(adminId);
    }
}

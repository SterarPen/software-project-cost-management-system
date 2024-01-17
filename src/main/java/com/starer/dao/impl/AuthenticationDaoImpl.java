package com.starer.dao.impl;

import com.starer.dao.IAuthenticationDao;
import com.starer.pojo.entity.Authentication;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDaoImpl implements IAuthenticationDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Authentication selectAuthenticationByUserId(String userId, int role) {
        return sqlSessionTemplate.getMapper(IAuthenticationDao.class).selectAuthenticationByUserId(userId, role);
    }

    @Override
    public int insertAuthentication(Authentication authentication) {
        return sqlSessionTemplate.getMapper(IAuthenticationDao.class).insertAuthentication(authentication);
    }

    @Override
    public int deleteAuthentication(String userId, int role) {
        return sqlSessionTemplate.getMapper(IAuthenticationDao.class).deleteAuthentication(userId, role);
    }
}

package com.starer.dao.impl;

import com.starer.dao.IBuyerDao;
import com.starer.pojo.entity.user.Buyer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/11/26 21:56:05
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class BuyerDaoImpl implements IBuyerDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Buyer[] queryBuyer(String id, String phone, String email) {
        return sqlSessionTemplate.getMapper(IBuyerDao.class).queryBuyer(id, phone, email);
    }

    @Override
    public int updateBuyer(Buyer buyer) {
        return sqlSessionTemplate.getMapper(IBuyerDao.class).updateBuyer(buyer);
    }

    @Override
    public int addBuyer(Buyer buyer) {
        return sqlSessionTemplate.getMapper(IBuyerDao.class).addBuyer(buyer);
    }

    @Override
    public int deleteBuyer(String id, Timestamp destroyTime) {
        return sqlSessionTemplate.getMapper(IBuyerDao.class).deleteBuyer(id, destroyTime);
    }
}

package com.starer.service.impl;

import com.mysql.cj.util.StringUtils;
import com.starer.dao.IBuyerDao;
import com.starer.pojo.entity.user.Buyer;
import com.starer.service.IBuyerService;
import com.starer.util.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BuyerServiceImpl implements IBuyerService {

    private IBuyerDao buyerDao;

    @Autowired
    public BuyerServiceImpl(IBuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

    @Override
    public Buyer register(String name, String password, String email, String phone, String createBy, String projectId) {
        Buyer buyer = new Buyer(name, password, phone, email, createBy, projectId);
        int result = buyerDao.addBuyer(buyer);
        return result==1?buyer:null;
    }

    @Override
    public Buyer login(String account, String password, LoginType loginType) {
        Buyer[] buyers = null;
        switch (loginType) {
            case LOGIN_ID:
                buyers = buyerDao.queryBuyer(account, null, null);
                break;
            case LOGIN_PHONE:
                buyers = buyerDao.queryBuyer(null, account, null);
                break;
            case LOGIN_EMAIL:
                buyers = buyerDao.queryBuyer(null, null, account);
                break;
            default:
                return null;
        }
        return buyers != null && buyers.length != 0 ? buyers[0] : null;
    }

    @Override
    public int updateBuyer(Buyer buyer) {
        int i = buyerDao.updateBuyer(buyer);
        return i;
    }

    @Override
    public boolean updateUserName(String id, String newName) {
        Buyer buyer = new Buyer();
        buyer.setId(id);
        buyer.setName(newName);
        int resultNum = buyerDao.updateBuyer(buyer);
        boolean b = resultNum == 1;
        return b;
    }

    @Override
    public boolean updatePassword(String id,String currentPassword, String newPassword) {
        if(StringUtils.nullSafeEqual(currentPassword, newPassword)) return false;
        Buyer buyer = buyerDao.queryBuyer(id, null, null)[0];
        if(!StringUtils.nullSafeEqual(currentPassword,buyer.getPassword()))
            return false;
        buyer.setPassword(newPassword);
        int resultNum = buyerDao.updateBuyer(buyer);
        return resultNum == 1;
    }

    @Override
    public boolean updatePassword(String id, String newPassword) {
        Buyer buyer = new Buyer();
        buyer.setId(id);
        buyer.setPassword(newPassword);
        int resultNum = buyerDao.updateBuyer(buyer);
        return resultNum == 1;
    }

    @Override
    public Buyer getInformation(String id, String phone, String email) {
        Buyer[] buyers = buyerDao.queryBuyer(id, phone, email);
        return buyers.length == 1 ? buyers[0] : null;
    }

    @Override
    public Buyer[] getBuyers(String id, String phone, String email) {
        return buyerDao.queryBuyer(id, phone, email);
    }

    @Override
    public int deleteBuyer(String buyerId) {
        return buyerDao.deleteBuyer(buyerId, new Timestamp(System.currentTimeMillis()));
    }
}

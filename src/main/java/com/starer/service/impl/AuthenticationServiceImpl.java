package com.starer.service.impl;

import com.starer.dao.IAuthenticationDao;
import com.starer.pojo.entity.Authentication;
import com.starer.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private IAuthenticationDao authenticationDao;

    @Autowired
    public AuthenticationServiceImpl(IAuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    @Override
    public Authentication queryAuthentication(String userId, int role) {
        return authenticationDao.selectAuthenticationByUserId(userId, role);
    }

    @Transactional
    @Override
    public int addAuthentication(Authentication authentication) {
        deleteAuthentication(authentication.getUserId(), authentication.getRole());
        return authenticationDao.insertAuthentication(authentication);
    }

    @Override
    public int deleteAuthentication(String userId, int role) {
        return authenticationDao.deleteAuthentication(userId, role);
    }
}

package com.starer.service;

import com.starer.dao.IAuthenticationDao;
import com.starer.pojo.entity.Authentication;

public interface IAuthenticationService {

    Authentication queryAuthentication(String userId, int role);

    int addAuthentication(Authentication authentication);
    int deleteAuthentication(String userId, int role);


}

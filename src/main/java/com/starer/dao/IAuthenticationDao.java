package com.starer.dao;

import com.starer.pojo.entity.Authentication;
import org.apache.ibatis.annotations.Param;

public interface IAuthenticationDao {

    Authentication selectAuthenticationByUserId(@Param("userId") String userId, @Param("role") int role);
    int insertAuthentication(Authentication authentication);
    int deleteAuthentication(@Param("userId") String userId, @Param("role") int role);
}

package com.starer.dao;


import com.starer.pojo.entity.user.Manager;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface IManagerDao {

    Manager[] queryManager(@Param("managerId") String managerId, @Param("phone") String phone, @Param("email") String email);
    int updateManager(Manager manager);
    int addManager(Manager developer);
    int deleteManager(@Param("managerId") String managerId,@Param("destroyTime") Timestamp destroyTime);

}
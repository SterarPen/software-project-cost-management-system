package com.starer.dao;

import com.starer.pojo.entity.user.Admin;
import org.apache.ibatis.annotations.Param;

public interface IAdminDao {

    Admin queryAdminById(@Param("adminId") String adminId);
    int updateAdmin(Admin admin);
    int addAdmin(Admin admin);
    int deleteAdmin(@Param("adminId") String adminId);
}

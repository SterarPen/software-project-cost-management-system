package com.starer.dao;

import com.starer.pojo.entity.user.Buyer;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface IBuyerDao {

    Buyer[] queryBuyer(@Param("id") String id,@Param("phone") String phone,@Param("email") String email);
    int updateBuyer(Buyer buyer);
    int addBuyer(Buyer buyer);
    int deleteBuyer(@Param("id") String id,@Param("destroyTime") Timestamp destroyTime);
}

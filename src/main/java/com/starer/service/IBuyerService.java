package com.starer.service;

import com.starer.pojo.entity.user.Buyer;
import com.starer.util.LoginType;

public interface IBuyerService {

    Buyer register(String name, String password, String email, String phone, String createBy,
                   String projectId);

    Buyer login(String account, String password, LoginType loginType);

    int updateBuyer(Buyer buyer);

    boolean updateUserName(String id, String newName);
    boolean updatePassword(String id, String oldPassword, String newPassword);
    boolean updatePassword(String id, String newPassword);

    Buyer getInformation(String id, String phone, String email);
    Buyer[] getBuyers(String id, String phone, String email);
    int deleteBuyer(String buyerId);
}

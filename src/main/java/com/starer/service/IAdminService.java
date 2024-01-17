package com.starer.service;

import com.starer.pojo.entity.user.Admin;
import org.springframework.lang.NonNull;

public interface IAdminService {

    /**
     * 管理员登录业务代码（管理员只通过ID登录）
     * @param adminId
     * @param password
     * @return 登录是否成功
     */
    boolean login(@NonNull String adminId,@NonNull String password);

    boolean updateUserName(@NonNull String adminId,@NonNull String newName);
    boolean updatePassword(@NonNull String adminId,@NonNull String currentPassword,@NonNull String newPassword);

    Admin getAdminInformation(@NonNull String adminId);
}

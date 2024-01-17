package com.starer.service.impl;

import com.starer.dao.IAdminDao;
import com.starer.pojo.entity.user.Admin;
import com.starer.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class AdminServiceImpl implements IAdminService {

    private IAdminDao adminDao;

    @Autowired
    public AdminServiceImpl(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public boolean login(String adminId, String password) {
        if(adminId == null || password == null) return false;
        Admin admin = adminDao.queryAdminById(adminId);
        if(admin == null ) return false;
        return password.equals(admin.getPassword());
    }

    @Override
    public boolean updateUserName(String adminId, String newName) {
        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminName(newName);
        int i = adminDao.updateAdmin(admin);
        return i == 1;
    }

    @Override
    public boolean updatePassword(String adminId, String currentPassword, String newPassword) {
        Admin admin = adminDao.queryAdminById(adminId);
        if(!currentPassword.equals(admin.getPassword()))
            return false;
        String[] oldPasswords = admin.getOldPassword().split("-");
        for (String oldPassword : oldPasswords) {
            if(newPassword.equals(oldPassword)) return false;
        }

        String oldPasswords1;
        if(oldPasswords.length == 0 || ("".equals(oldPasswords[0])))
            oldPasswords1 = newPassword;
        else
            oldPasswords1 =  oldPasswords.length > 30 ? "" : admin.getOldPassword() + "-" + newPassword;

        Admin newAdmin = new Admin();
        newAdmin.setAdminId(adminId);
        newAdmin.setPassword(newPassword);
        newAdmin.setOldPassword(oldPasswords1);
        int i = adminDao.updateAdmin(newAdmin);
        return i == 1;
    }

    @Override
    public Admin getAdminInformation(String adminId) {
        return adminDao.queryAdminById(adminId);
    }
}

package com.starer.service.impl;

import com.mysql.cj.util.StringUtils;
import com.starer.dao.IManagerDao;
import com.starer.pojo.entity.user.Manager;
import com.starer.service.IManagerService;
import com.starer.util.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ManagerServiceImpl implements IManagerService {

    private IManagerDao managerDao;

    @Autowired
    public ManagerServiceImpl(IManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public Manager register(String managerName, String password, String email, String phone, String createBy,
                            String projectId) {
        Manager manager = new Manager();
        manager.setManagerName(managerName);
        manager.setPassword(password);
        manager.setPhone(phone);
        manager.setEmail(email);
        manager.setCreateBy(createBy);
        manager.setHandlingProject(projectId);

        int i = managerDao.addManager(manager);
        if(i == 1)
            return manager;
        else
            return null;
    }

    @Override
    public Manager login(String account, String password, LoginType loginType) {
        Manager manager = null;
        switch (loginType) {
            case LOGIN_ID:
                Manager[] managers = managerDao.queryManager(account, null, null);
                manager = managers!=null && managers.length==1?managers[0]:null;
                break;
            case LOGIN_PHONE:
                managers = managerDao.queryManager(null, account, null);
                manager = managers != null &&managers.length==1?managers[0]:null;
                break;
            case LOGIN_EMAIL:
                managers = managerDao.queryManager(null, null, account);
                manager = managers != null &&managers.length==1?managers[0]:null;
                break;
        }
        if(manager == null) return null;
        return manager.getPassword().equals(password)?manager:null;
    }

    @Override
    public boolean updateUserName(String id, String newName) {
        Manager developer = new Manager();
        developer.setManagerId(id);
        developer.setManagerName(newName);
        return managerDao.updateManager(developer)==1;
    }

    @Override
    public boolean updatePassword(String id, String currentPassword, String newPassword) {
        if(StringUtils.nullSafeEqual(currentPassword, newPassword)) return false;
        Manager developer =
                managerDao.queryManager(id, null, null)[0];
        if(!StringUtils.nullSafeEqual(currentPassword,developer.getPassword()))
            return false;
        developer.setPassword(newPassword);
        int resultNum = managerDao.updateManager(developer);
        return resultNum == 1;
    }

    @Override
    public boolean updatePassword(String id, String newPassword) {
        return false;
    }

    @Override
    public int updateManager(Manager manager) {
        return managerDao.updateManager(manager);
    }

    @Override
    public Manager getInformation(String id, String phone, String email) {
        Manager[] developerManagers = managerDao.queryManager(id, phone, email);
        return developerManagers == null || developerManagers.length == 0 ? null : developerManagers[0];
    }

    @Override
    public Manager[] getAllManager(String id, String phone, String email) {
        return managerDao.queryManager(id,phone, email);
    }

    @Override
    public int deleteManager(String managerId) {
        return managerDao.deleteManager(managerId, new Timestamp(System.currentTimeMillis()));
    }
}

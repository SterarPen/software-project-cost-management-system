package com.starer.dao;

import com.starer.pojo.entity.user.Admin;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 18:40:18
 * @Version: V1.0
 * @Description:
 **/
public class TestManagerDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IAdminDao adminDao = applicationContext.getBean("adminDaoImpl", IAdminDao.class);

    public void testAddManager() {
        Admin admin = new Admin("admin5", "123");
        int i = adminDao.addAdmin(admin);
        System.out.println(i);
        System.out.println(admin);
    }

    public void testQueryAdminById() {
        Admin admin = adminDao.queryAdminById("114");
        System.out.println(admin);
    }

    public void testUpdateManager() {
        Admin admin = adminDao.queryAdminById("115");
        Admin newManager = new Admin();
        admin.setAdminId("115");
        admin.setAdminName("addMain");
        admin.setPassword("134");
        admin.setOldPassword(admin.getOldPassword() + "-" + "134");
        int i = adminDao.updateAdmin(admin);
        System.out.println(i);
    }



}

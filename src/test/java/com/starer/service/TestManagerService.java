package com.starer.service;

import com.starer.pojo.entity.user.Manager;
import com.starer.util.LoginType;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 22:16:31
 * @Version: V1.0
 * @Description:
 **/
public class TestManagerService extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IManagerService managerService =
            applicationContext.getBean("managerServiceImpl", IManagerService.class);

    public void testRegister() {
        Manager das = managerService.register("das", "123", "1345",
                "15472145124", "112", "1");
        System.out.println(das);
    }

    public void testLogin() {
        Manager login = managerService.login("2", "123", LoginType.LOGIN_ID);
        System.out.println(login);
    }

    public void testUpdateUserName() {
        boolean b = managerService.updateUserName("2", "小二");
        System.out.println(b);
    }

    public void testUpdatePassword() {
        boolean b = managerService.updatePassword("2", "123", "111");
        System.out.println(b);
    }

    public void testUpdatePasswordTwo() {
        boolean b = managerService.updatePassword("2", "123");
        System.out.println(b);
    }

    public void testGetInformation() {
        Manager information = managerService.getInformation("2", null, null);
        System.out.println(information);
    }

    public void testGetAllDeveloperManager() {
        Manager[] allDeveloperManager = managerService.getAllManager("2", null, null);
        for (int i = 0; i < allDeveloperManager.length; i++) {
            System.out.println(i + ":  " + allDeveloperManager[i]);
        }
    }
}

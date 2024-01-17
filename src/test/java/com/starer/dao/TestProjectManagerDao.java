package com.starer.dao;

import com.starer.pojo.entity.user.Manager;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 12:25:04
 * @Version: V1.0
 * @Description:
 **/
public class TestProjectManagerDao extends TestCase {

    private final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private final IManagerDao managerDao =
            applicationContext.getBean("managerDaoImpl", IManagerDao.class);

    public void testQueryDeveloperManager() {
        Manager[] developerManagers = managerDao.queryManager("3", null, null);
        if(developerManagers == null || developerManagers.length == 0) {
            System.out.println("Not have developer-manager");
            return;
        }
        for (Manager developerManager : developerManagers) {
            System.out.println(developerManager);
        }
    }

    public void testUpdateDeveloperManager() {
        Manager developerManager = new Manager();
        developerManager.setManagerId("1");
        developerManager.setManagerName("test-developer-manager");
        developerManager.setPassword("123");

        int i = managerDao.updateManager(developerManager);
        System.out.println(i);
    }

    public void testAddDeveloperManager() {
        Manager developerManager = new Manager("test1", "12",
                "16854124121", "1234565@qq.com", "111");

        int i = managerDao.addManager(developerManager);
        System.out.println(i);
    }

    public void testDeleteDeveloperManager() {
        int i = managerDao.deleteManager("3", new Timestamp(System.currentTimeMillis()));
        System.out.println(i);
    }
}

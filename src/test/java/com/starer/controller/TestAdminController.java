package com.starer.controller;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ConcurrentModel;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 20:27:22
 * @Version: V1.0
 * @Description:
 **/
public class TestAdminController extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("/spring/spring-dao.xml");
    private ManagerController managerController =
            applicationContext.getBean("managerController", ManagerController.class);

    public void testprojectInformation() {
//        String s = managerController.projectInformation("3", new ConcurrentModel());
//        System.out.println(s);
    }
}

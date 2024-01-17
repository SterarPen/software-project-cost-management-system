package com.starer.dao;

import com.starer.dao.impl.ProjectDaoImpl;
import com.starer.pojo.entity.Project;
import junit.framework.TestCase;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 19:10:28
 * @Version: V1.0
 * @Description:
 **/
public class TestProjectDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private SqlSessionTemplate sqlSessionTemplate =
            applicationContext.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
    private IProjectDao projectDao = new ProjectDaoImpl(sqlSessionTemplate);

    public void testProjectDao() {
        System.out.println(projectDao.queryProjectById("1").toString());
    }
    public void testQueryProjectsByBuyerId() {
        Project[] projects = projectDao.queryProjectsByBuyerId("2");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    public void testQueryProjectsByDeveloperManagerId() {
        Project[] projects = projectDao.queryProjectsByDeveloperManagerId("12");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    public void testQueryProjectsIdByBuyerId() {
        String[] strings = projectDao.queryProjectsIdByBuyerId("2");
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public void testQueryProjectsIdByDeveloperManagerId() {
        String[] strings = projectDao.queryProjectsIdByDeveloperManagerId("12");
        for (String str : strings) {
            System.out.println(str);
        }
    }
}

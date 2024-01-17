package com.starer.service;

import com.starer.pojo.entity.Project;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 19:48:07
 * @Version: V1.0
 * @Description:
 **/
public class ProjectService extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IProjectService projectService =
            applicationContext.getBean("projectServiceImpl", IProjectService.class);

    public void testGetProjectInformationById() {
        Project project = projectService.getProjectInformationById("1");
        System.out.println(project);
    }

    public void testGetAllProjectIdOfBuyer() {
        String[] projectsId = projectService.getAllProjectIdOfBuyer("2");
        for (String s : projectsId) {
            System.out.println(s);
        }
    }

    public void testGetAllProjectIdOfDeveloperManager() {
        String[] projectsId = projectService.getAllProjectIdOfDeveloperManager("12");
        for (String s : projectsId) {
            System.out.println(s);
        }
    }

    public void testInsertProject() {
        int i = projectService.addProject("sda", "11124", "1");
        System.out.println(i);
    }
    
    public void testDeleteProject() {
        int i = projectService.deleteProject("4");
        System.out.println(i);
    }
}

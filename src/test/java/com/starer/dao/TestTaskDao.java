package com.starer.dao;

import com.starer.pojo.entity.Task;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 16:51:59
 * @Version: V1.0
 * @Description:
 **/
public class TestTaskDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("/spring/spring-dao.xml");
    private ITaskDao taskDao = applicationContext.getBean("taskDaoImpl", ITaskDao.class);

    public void testselectTaskById() {
        Task task = taskDao.selectTaskById("3");
        System.out.println(task);
    }

    public void testselectTaskByStageId() {
        Task[] tasks = taskDao.selectTaskByStageId("1");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void testselectTaskByProjectId()   {
        Task[] tasks = taskDao.selectTaskByProjectId("1");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void testinsertTask() {
        Task task = new Task("dasfsdasfsafefedf", new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), new BigDecimal("120.2"),
                "1");
        int i = taskDao.insertTask(task, "1");
        System.out.println(i);
    }

    public void testdeleteTask() {
        int i = taskDao.deleteTask("6");
        System.out.println(i);
    }

    public void testupdateTask() {
        int i = taskDao.updateTask(
                new Task(
                        "6", "1111", null,
                null, 1, new BigDecimal("120510.00"), "2"
                )
        );
        System.out.println(i);
    }

    public void testSelectTaskByCondition() {
        Task[] tasks = taskDao.selectTaskByCondition(null, "1", "11", "2");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}

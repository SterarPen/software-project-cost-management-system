package com.starer.dao;

import com.starer.pojo.entity.Stage;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 12:17:57
 * @Version: V1.0
 * @Description:
 **/
public class TestStageDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
//    private SqlSessionTemplate sqlSessionTemplate =
//            applicationContext.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
//    private IStageDao stageDao = new StageDaoImpl(sqlSessionTemplate);

    private IStageDao stageDao = applicationContext.getBean("stageDaoImpl", IStageDao.class);

    public void testSelectStageById() {
        Stage stage = stageDao.selectStageById("3");
        System.out.println(stage);
    }

    public void testSelectAllStageByProjectId() {
        Stage[] stages = stageDao.selectAllStageByProjectId("1");
        for (Stage stage : stages) {
            System.out.println(stage);
        }
    }

    public void testInsertStage() {
        Stage stage = new Stage("10", "项目结束", new Date(System.currentTimeMillis()),
                Date.valueOf(LocalDate.of(2023, 4, 15)), new BigDecimal("3021.12"));
        boolean b = stageDao.insertStage(stage, "2");
        System.out.println(b);
    }

    public void testUpdateStage() {
        Stage stage = stageDao.selectStageById("10");
        stage.setPredictCost(new BigDecimal("10000.00"));
        boolean b = stageDao.updateStage(stage, null);
        System.out.println(b);
    }

    public void testDeleteStage() {
        boolean b = stageDao.deleteStage("10");
        System.out.println(b);
    }
}

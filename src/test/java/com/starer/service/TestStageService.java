package com.starer.service;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 13:11:15
 * @Version: V1.0
 * @Description:
 **/
public class TestStageService extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IStageService stageService = applicationContext.getBean("stageServiceImpl", IStageService.class);

    public void testQueryPredictCostOfProject() {
        BigDecimal bigDecimal = stageService.queryPredictCostOfProject("1");
        System.out.println(bigDecimal);
    }
}

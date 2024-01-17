package com.starer.dao;

import com.starer.pojo.entity.Demand;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 12:57:48
 * @Version: V1.0
 * @Description:
 **/
public class TestDemandDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IDemandDao demandDao = applicationContext.getBean("demandDaoImpl", IDemandDao.class);

    public void test1() {
        Demand[] demands = demandDao.selectConditionalDemand("1", null,
                "1000", null, null);
        for (Demand demand : demands) {
            System.out.println(demand);
        }
    }
}

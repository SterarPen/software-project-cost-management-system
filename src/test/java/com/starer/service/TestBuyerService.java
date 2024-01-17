package com.starer.service;

import com.starer.pojo.entity.user.Buyer;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 15:24:04
 * @Version: V1.0
 * @Description:
 **/
public class TestBuyerService extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IBuyerService buyerService = applicationContext.getBean("buyerServiceImpl", IBuyerService.class);

    public void testRegister() {
        Buyer buyer = buyerService.register("dared", "123", "352", "16584124135",
                "2", "1");
        System.out.println(buyer);

    }

}

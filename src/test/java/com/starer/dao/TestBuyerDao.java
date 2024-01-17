package com.starer.dao;

import com.starer.pojo.entity.user.Buyer;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 14:39:04
 * @Version: V1.0
 * @Description:
 **/
public class TestBuyerDao extends TestCase {

    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    private IBuyerDao buyerDao = applicationContext.getBean("buyerDaoImpl", IBuyerDao.class);

    public void testQueryBuyer() {
        Buyer buyer = buyerDao.queryBuyer(null, null, "1")[0];
        System.out.println(buyer);
    }

    public void testAddBuyer() {
        Buyer buyer = new Buyer("test", "1", "16584145214", "25632", "3", "1");
        int i = buyerDao.addBuyer(buyer);
        System.out.println(i);
    }
    public void testDeleteBuyer() {
        int i = buyerDao.deleteBuyer("11127", new Timestamp(System.currentTimeMillis()));
        System.out.println(i);
    }

    public void testUpdateBuyer() {
        Buyer buyer = new Buyer();
        buyer.setId("11123");
        buyer.setPassword("1");

        int i = buyerDao.updateBuyer(buyer);
        System.out.println(i);
    }
}

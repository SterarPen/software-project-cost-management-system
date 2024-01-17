import com.starer.pojo.vo.TaskDto;
import com.starer.util.DateTimeUtil;
import junit.framework.TestCase;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2023/12/03 16:27:06
 * @Version: V1.0
 * @Description:
 **/
public class myTest extends TestCase {

    public static void main(String[] args) {
//        IUserService buyerService = new BuyerServiceImpl();
//        buyerService.getInformation("11123", null, null);
//        Buyer buyer = new Buyer();
//        System.out.println(buyer.getClass());
//        System.out.println(buyer instanceof Buyer);
//        System.out.println(Buyer.class.equals(buyer.getClass()));

//        Integer[] a =new Integer[3];
//        System.out.println(a.getClass().toString());
//        System.out.println(Integer.class);

//        System.out.println(DateTimeUtil.convertTimestampToString(new Timestamp(System.currentTimeMillis())));


    }

    public void test1() {
        System.out.println("134658412412".matches("1[0-9]{10}"));
        System.out.println("dfsafsdfsd@qqcom".matches("[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-zA-Z]+"));
    }

    public void test2() {
        TaskDto taskDto = new TaskDto("1", "asdasd","2023-1-1",
                "2023-1-2",1,"1321","阶段1");
        System.out.println(taskDto);
    }



}

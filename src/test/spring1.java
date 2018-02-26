package test;

import com.ztt.bean.User;
import com.ztt.services.Regist;
import com.ztt.services.RegistImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import util.GenerateID;

/**
 * @author zhaotian
 * @date 2018/2/26 13:59
 */
public class spring1 {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Regist testSpring=(RegistImpl) applicationContext.getBean("regist");
        User user=new User();
        user.setId(GenerateID.generate ());
        user.setName("ztt");
        user.setPassword("123322");
        user.setType(1);
        user.setEmail("1231@qq.com");
        testSpring.add(user);
        System.out.println(user.getId().length());
    }
}

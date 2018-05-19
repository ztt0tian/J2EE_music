package logSystem;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhaotian
 * @date 2018/3/15 19:48
 */
@Aspect
@Component
public class LogInterceptor {
    private final Logger logger=LoggerFactory.getLogger(LogInterceptor.class);
    @Pointcut(value = "execution(* com.ztt.action.UserAction.*())")
    public void controlAspect(){

    }
    @Before("controlAspect()")
    public void doBefor(JoinPoint joinPoint){
        System.out.println("执行前");
    }
    @Before("execution(* com.ztt..*(..))")
    public void before(){
        System.out.println("method start");
    }
}

package javatest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/14 16:02
 */
@Component
@Aspect
public class ParamValidateAspect {

    @Pointcut("execution(public * qf.javatest.aspect.TestAspetController.mm(..)))")
    public void ddd() {

    }
  /*  @Pointcut("execution(public * qf.javatest.JavatestApplication.dsd(..)))")
    public void sdfsdf() {

    }*/

    @Pointcut("execution(public * qf.javatest.aspect.TestAspetController.dsd(..)))")
    public void BrokerAspect() {

    }



    @Before("BrokerAspect()")
    public void mm(JoinPoint jp){
        System.out.println("before");
    }
    @After("BrokerAspect()")
    public void mm1(JoinPoint jp){
        System.out.println("after");
    }

    @AfterReturning(pointcut = "BrokerAspect()", returning = "args")
    public void mm2(  List args){
        args.remove(0);
        System.out.println("afterreturn");
    }


    @Around("BrokerAspect()")
    public Object mm3(ProceedingJoinPoint jp) throws Throwable {
        Object re = null;
        System.out.println("around before");
        re = jp.proceed();
        System.out.println("around end");
        return re;
    }

    @After("ddd()")
    public void sada(JoinPoint jp){
        System.out.println("after");
    }

/*    @After("BrokerAspect()")
    public void afterer(JoinPoint jp){
        System.out.println("after");
    }*/

  /*  @AfterReturning(pointcut = "BrokerAspect()",returning = "fsdf")
    public void aftererRE(JoinPoint jp, Object fsdf ){
        System.out.println("after");
    }*/

/*    @Before("BrokerAspect()")
    public void dfdf(JoinPoint jp){
        System.out.println( ((TestAspetController)jp.getTarget()).dsd());
        System.out.println("sdsds");
    }*/
    /**
     *  可以选择是不是需要执行方法
     * @param proceedingJoinPoint
     * @return
     */
/*    @Around("BrokerAspect()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();


        Object obj = null;
        try {//obj之前可以写目标方法执行前的逻辑
            //调用执行目标方法
            obj =  proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        return "sfsdf";

    }*/



}

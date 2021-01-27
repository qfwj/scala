package javatest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/11/12 14:56
 */
@Aspect
@Component
public class TestAopAdvice {

    @Pointcut("execution( * cn.people.audit.web.Test.getMm())")
    public void testPoint2() {
    }

    @Pointcut("execution( * cn.people.audit.web.TestAop.testAop())")
    public void testPoint() {
    }

    @Around("testPoint()")
    public void Around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around1");
        joinPoint.proceed();
        System.out.println("Around2");

    }


    @Before("testPoint()")
    public void Before(JoinPoint joinPoint){
        System.out.println("Before");
    }
    @After("testPoint()")
    public void After(JoinPoint joinPoint){
        System.out.println("After");
    }
    @AfterReturning("testPoint()")
    public void AfterReturning(JoinPoint joinPoint){
        System.out.println("AfterReturning");
    }

    @AfterThrowing("testPoint()")
    public void AfterThrowing(JoinPoint joinPoint){
        System.out.println("AfterThrowing");
    }
}

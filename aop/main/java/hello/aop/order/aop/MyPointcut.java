package hello.aop.order.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyPointcut {

    @Pointcut("execution(* hello.aop.order..*.*(..))")
    public void allOrder(){}

    @Pointcut("execution(* hello.aop.order.*Service*.*(..))")
    public void allService() {}

    @Pointcut("allService() && allOrder()")
    public void allOrderAndService(){}

}

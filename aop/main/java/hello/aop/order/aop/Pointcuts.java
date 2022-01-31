package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* *..*Service*.*(..))")
    public void allService(){}

    @Pointcut("execution(* hello.aop..*.*(..))")
    public void allOrder(){}

    @Pointcut("allService() && allOrder()")
    public void allOrderAndService(){}


}

package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV2 {


    @Pointcut("execution(* hello.aop.order..*.*(..))")
    public void allOrder(){}




    @Around("allOrder()")
    public Object doLog1(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[doLog1{}]", joinPoint.getSignature().toShortString());
        return joinPoint.proceed();
    }

    @Around("allOrder()")
    public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[doLog2{}]", joinPoint.getSignature().toShortString());
        return joinPoint.proceed();
    }


}

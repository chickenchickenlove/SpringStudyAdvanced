package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Slf4j
public class AspectV6 {


    // 트랜잭션 구현

    @Around("hello.aop.order.aop.MyPointcut.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature().toShortString());
        return joinPoint.proceed();
    }


    @Before("hello.aop.order.aop.MyPointcut.allService()")
    public void beforeTx(JoinPoint joinPoint) throws Throwable {
        log.info("[트랜잭션 시작] {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(value = "hello.aop.order.aop.MyPointcut.allOrderAndService()", returning = "result")
    public void afterReturnTx(JoinPoint joinPoint, Object result) throws Throwable {
        log.info("[After Returning] result : {}", result);
        log.info("[트랜잭션 종료] {}", joinPoint.getSignature().toShortString());
    }



    @AfterThrowing(value = "hello.aop.order.aop.MyPointcut.allOrderAndService()", throwing = "ex")
    public void afterThrowingTx(JoinPoint joinPoint, Exception ex) throws Throwable {
        log.info("[After Throw] Catch : {}", ex.getMessage());
        log.info("[트랜잭션 롤백] {}", joinPoint.getSignature().toShortString());
    }


    @After("hello.aop.order.aop.MyPointcut.allService()")
    public void doTx(JoinPoint joinPoint) throws Throwable {
        log.info("[리소스 릴리즈] {}", joinPoint.getSignature().toShortString());
    }

}

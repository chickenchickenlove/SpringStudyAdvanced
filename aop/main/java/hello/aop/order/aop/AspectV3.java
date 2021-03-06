package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV3 {


    // 트랜잭션 구현

    @Pointcut("execution(* hello.aop.order..*.*(..))")
    public void allOrder(){}


    @Pointcut("execution(* hello.aop.order.*Service*.*(..))")
    public void allService() {}




    @Around("allOrder()")
    public Object doLog1(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature().toShortString());
        return joinPoint.proceed();
    }


    @Around("allService()")
    public Object doTx(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature().toShortString());

            Object result = joinPoint.proceed();
            log.info("[트랜잭션 종료] {}", joinPoint.getSignature().toShortString());

            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature().toShortString());
            throw e;
        }finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature().toShortString());
        }

    }

}

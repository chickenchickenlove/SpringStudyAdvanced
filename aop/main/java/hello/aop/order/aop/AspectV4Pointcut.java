package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class AspectV4Pointcut {


    @Around("hello.aop.order.aop.MyPointcut.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[Log] {}", joinPoint.getSignature().toShortString());
        return joinPoint.proceed();
    }



    @Around("(hello.aop.order.aop.MyPointcut.allService())")
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

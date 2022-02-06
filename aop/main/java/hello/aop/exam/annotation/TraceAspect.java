package hello.aop.exam.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TraceAspect {

    @Pointcut("@annotation(hello.aop.exam.annotation.Trace)")
    public void tracePointcut(){}


    @Before("tracePointcut()")
    public void doLogBefore(JoinPoint joinPoint) {
        log.info("[Trace {} Before]", joinPoint.getSignature().toShortString());
    }

    @After("tracePointcut()")
    public void doLogAfter(JoinPoint joinPoint) {
        log.info("[Trace {} After]", joinPoint.getSignature().toShortString());
    }

}

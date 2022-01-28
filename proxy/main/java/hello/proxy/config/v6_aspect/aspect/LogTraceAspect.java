package hello.proxy.config.v6_aspect.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;

import java.lang.reflect.Method;

@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;


    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    @Around("execution(* hello.proxy.app..*(..))") // 포인트 컷
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        TraceStatus status = null;
        try {

            String message = joinPoint.getSignature().toShortString();

            // 메세지 작성.

            status = logTrace.begin(message);
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status,e);
            throw e;
        }
    }
}

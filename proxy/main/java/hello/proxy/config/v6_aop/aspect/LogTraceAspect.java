package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

@Aspect
public class LogTraceAspect {

    // 어드바이저를 만드는 것임
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    // * 패턴을 의미한다. string 뭐 등등
    // .. 은 여러개 있다는 것을 의미한다.


    @Around("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))")
    public Object logTrace(ProceedingJoinPoint joinPoint) throws Throwable {


        TraceStatus status = null;
        try {

            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 핵심 기능
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}

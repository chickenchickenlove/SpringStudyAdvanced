package hello.aop.exam.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class RetryAspect {

    Exception exceptionHolder;

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[doRetry] {}, retry = {}", joinPoint.getSignature().toShortString(), retry);

        int maxLimit = retry.maxRetryCount();

        for (int i = 0; i < maxLimit; i++) {
            try {
                log.info("[doRetry] {}, retry = {}/{}", joinPoint.getSignature().toShortString(), i, maxLimit);
                return joinPoint.proceed();
            } catch (Exception e) {
                //실패시 Exception을 잡아둔다.
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}

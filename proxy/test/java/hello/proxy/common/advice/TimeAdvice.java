package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


@Slf4j
public class TimeAdvice implements MethodInterceptor {


    // 만들 때, Target으로 넘겨준다. ==> 그래서 Target이 뭔지 알고 있고, 인자도 뭔지 알고 있다.
    // 그리고 실행 시점에 메서드 정보가 넘어온다.
    // 추상화 되어있으니 그냥 proceed()로 실행만 하면 된다. 다음 Target으로 넘어가려면.

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 실행");

        long s = System.currentTimeMillis();

        Object result = invocation.proceed();

        long e = System.currentTimeMillis();
        long r = e - s;

        log.info("TimeAdvice 실행 종료, resultTime = {}", r);

        return result;
    }
}

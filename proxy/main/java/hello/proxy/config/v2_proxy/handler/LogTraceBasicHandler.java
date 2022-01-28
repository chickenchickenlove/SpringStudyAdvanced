package hello.proxy.config.v2_proxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {


    private final Object target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }


    // 메서드와 프록시가 넘어온다.
    // 그리고 args도 넘어온다.
    // 객체가 안 넘어온다.
    // JDK 동적 프록시는 핸들러러 + CLASS LOADER 같은 것들을 통해서 동적으로 프록시를 만들 수 있다.
    // 동적 프록시가 호출되면 자동으로 동적으로 메서드 정보가 넘어오고, 인스턴스는 이미 만들 때 넣어져있으니..
    // INVOKE를 통해서 바로 실행이 된다.

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        TraceStatus status = null;
        try {
            // 메세지 작성.
            String message = method.getDeclaringClass().getSimpleName() + "." +
                    method.getName() + "()";
            status = logTrace.begin(message);
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status,e);
            throw e;
        }

    }
}

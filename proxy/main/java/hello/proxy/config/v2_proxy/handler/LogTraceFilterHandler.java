package hello.proxy.config.v2_proxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {


    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 매칭되지 않으면 걍 넘기자.
        String name = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, name)) {
            Object result = method.invoke(target, args);
            return result;
        }


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

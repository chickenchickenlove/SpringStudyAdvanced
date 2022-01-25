package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

// 반환 타입이 제각각이기 때문에 Generic을 넣어준다.
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    // 로그 찍기 위해서 생성해서 주입 받는다.
    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    // 반환 타입이 제각각이기 때문에 Generic을 넣어준다.
    // 클래스에서 타입을 지정하고, 그것을 반환 타입으로 받는다.
    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 로직 호출
            T result = call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();


}

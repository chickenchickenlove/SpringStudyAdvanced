package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceTemplate {

    private final LogTrace trace;

    // Log Trace를 생성자로 주입받는다.
    public TraceTemplate(LogTrace logTrace) {
        this.trace = logTrace;
    }


    // 제네릭을 이렇게 받도록 한다.
    // 추상 템플릿에 했던 것과 똑같다.
    // 제네릭은 반환 타입만 신경쓰면 된다.
    public <T> T  execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 로직 호출
            // 기존에는 자식을 호출했는데, 지금은 callBack을 통해서 한다
            T result = callback.call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }


}

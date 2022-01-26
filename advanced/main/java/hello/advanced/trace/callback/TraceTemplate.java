package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TraceTemplate<T> {

    private final LogTrace trace;


    public T execute(String message, TraceCallBack<T> callBack) {


        TraceStatus status= null;

        try {
             status = trace.begin(message);
            T result = callBack.call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


    }


}

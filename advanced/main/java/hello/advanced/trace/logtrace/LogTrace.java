package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

// 인터페이스로 유연한 설계를 도모한다.
// 로그 추적기를 위한 최소한의 기능만 정의했다.

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}

package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        // 로그 남는 게 확인됨.
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }


    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        // 로그 남는 게 확인됨.
        TraceStatus status = trace.begin("hello");
        trace.excpetion(status, new IllegalStateException());
    }

}
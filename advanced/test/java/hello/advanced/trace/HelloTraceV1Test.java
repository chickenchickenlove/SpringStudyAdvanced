package hello.advanced.trace;

import hello.advanced.trace.hellotrace.HelloTraceV1;
import org.junit.jupiter.api.Test;

public class HelloTraceV1Test {


    @Test
    public void begin_end() {

        HelloTraceV1 trace = new HelloTraceV1();

        TraceStatus hello = trace.begin("hello1");
        trace.end(hello);
    }

    @Test
    public void begin_ex() {

        HelloTraceV1 trace = new HelloTraceV1();

        TraceStatus hello = trace.begin("hello1");
        trace.exception(hello, new IllegalStateException());
    }



}

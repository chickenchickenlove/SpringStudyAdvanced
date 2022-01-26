package hello.advanced.app.v5;


import hello.advanced.trace.callback.TraceCallBack;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate<Void> traceTemplate;


    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate<>(trace);
    }

    public void save(String itemId) {


        traceTemplate.execute("orderRepository", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생 ");
            }
            sleep(1000);
            return null;
        });


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

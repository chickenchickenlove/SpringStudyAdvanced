package hello.advanced.app.v1;

import hello.advanced.app.v0.OrderRepositoryV0;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final HelloTraceV1 trace;
    private final OrderRepositoryV1 orderRepository;

    public void orderItem(String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderService");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }


}

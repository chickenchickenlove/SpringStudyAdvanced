package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final HelloTraceV2 trace;
    private final OrderRepositoryV2 orderRepository;

    // item을 주문해라.
    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderController.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.excpetion(status,e);
            throw e;
        }
    }


}

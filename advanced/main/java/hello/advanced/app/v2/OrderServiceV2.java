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

    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin_sync(traceId,"OrderService");
            orderRepository.save(status.getTraceId() ,itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }


}

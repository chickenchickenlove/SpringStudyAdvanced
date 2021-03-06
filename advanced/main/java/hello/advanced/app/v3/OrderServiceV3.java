package hello.advanced.app.v3;

import hello.advanced.app.v2.OrderRepositoryV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final LogTrace trace;
    private final OrderRepositoryV3 orderRepository;

    public void orderItem(String itemId) {

        TraceStatus status = null;

        try {

            // 부가 기능

            status = trace.begin("OrderService");

            // 핵심 기능

            orderRepository.save(itemId);


            // 부가 기능
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }


}

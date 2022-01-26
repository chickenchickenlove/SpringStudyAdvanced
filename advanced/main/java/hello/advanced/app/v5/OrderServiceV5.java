package hello.advanced.app.v5;

import hello.advanced.app.v4.OrderRepositoryV4;
import hello.advanced.trace.callback.TraceCallBack;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate<Void> traceTemplate;


    public OrderServiceV5(LogTrace trace, OrderRepositoryV5 orderRepository) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate<>(trace);
    }

    public void orderItem(String itemId) {
        traceTemplate.execute("orderService", (TraceCallBack<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }

}

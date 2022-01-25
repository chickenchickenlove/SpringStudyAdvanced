package hello.advanced.app.v4;

import hello.advanced.app.v3.OrderRepositoryV3;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final LogTrace trace;
    private final OrderRepositoryV4 orderRepository;

    // item을 주문해라.
    public void orderItem(String itemId) {

        // 객체 타입의 Void를 넣어준다.
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null; // 반환 타입이 Void일 때, 사용하는 함수다. 언어 차원에서
            }
        };

        template.execute("OrderController.orderItem()");
    }


}

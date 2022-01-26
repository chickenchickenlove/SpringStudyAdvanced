package hello.proxy.app.v3;

import hello.proxy.app.v2.OrderRepositoryV2;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV3(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }


}

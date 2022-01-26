package hello.advanced.app.v5;

import hello.advanced.app.v4.OrderServiceV4;
import hello.advanced.trace.callback.TraceCallBack;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate<String> traceTemplate;


    public OrderControllerV5(OrderServiceV5 orderService,LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate<String>(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {

        return traceTemplate.execute("orderController", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });

    }
}

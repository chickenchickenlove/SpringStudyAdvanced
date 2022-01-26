package hello.advanced.app.v3;

import hello.advanced.app.v2.OrderServiceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;


    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {

            // 부가 기능
            status = trace.begin("orderController");


            // 핵심 기능
            orderService.orderItem(itemId);


            // 부가 기능
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status,e);
            throw e;
        }

    }
}

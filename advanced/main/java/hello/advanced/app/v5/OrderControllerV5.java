package hello.advanced.app.v5;


import hello.advanced.app.v4.OrderServiceV4;
import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class OrderControllerV5 {

//    private final LogTrace trace;
    private final OrderServiceV4 orderService;

    // 조립해서 쓰는 것이 아니라, 실행 시점에 콜백 함수를 받아서 한다.
    // 따라서 매번 새로운 것을 만들 필요가 없고, 기존에 있는 것을 사용하면 된다.
    private final TraceTemplate traceTemplate;

    // 생성자가 하나나니까 @AutoWired를 생략해도 값이 잘 들어간다.
    // 여기서 logTrace 의존관계 주입을 받는다.
    public OrderControllerV5(OrderServiceV4 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    // 파라메터를 전달할 필요가 이제 없다.
    @GetMapping("/v5/request")
    public String request(String itemId) {

        return traceTemplate.execute("OrderController.request()", new TraceCallback<String>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });

    }

}

package hello.advanced.app.v4;


import hello.advanced.app.v3.OrderServiceV3;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final LogTrace trace;
    private final OrderServiceV4 orderService;

    // 파라메터를 전달할 필요가 이제 없다.
    @GetMapping("/v4/request")
    public String request(String itemId) {

        // 추상 템플릿을 익명 내부 클래스로 만든다.
        // 생성할 때 Trace를 넣어줘야한다. 처음부터 그렇게 정했기 때문이다.
        // 템플릿 메서드 패턴으로 해결했다.
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

        // 메세지만 넘겨주면 된다.
        return template.execute("OrderController.request()");
    }

}

package hello.advanced.app.v1;


import hello.advanced.app.v1.OrderServiceV1;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final HelloTraceV1 trace;
    private final OrderServiceV1 orderService;

    @GetMapping("/v1/request")
    public String request(String itemId) {


        TraceStatus status = null;

        // 예외가 터져도 예외가 들어가야하기 때문에 Try + catch 해줘야한다.
        try {
            // 정상 로직
            // 여기서 만든 변수는 여기에서만 유효하다. 스코프를 넓게 쓰기 위해서 빼줘야 한다.
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            // 로그 분석기는 전체 비즈니스 로직의 흐름을 바꾸면 안된다.
            // 이렇게만 해두면 예외를 먹어버린다 여기서.
            trace.excpetion(status,e);

            // 따라서 예외를 꼭 던져야한다. 예외를 먹어버리면 안되니까.
            throw e;
        }




    }
}

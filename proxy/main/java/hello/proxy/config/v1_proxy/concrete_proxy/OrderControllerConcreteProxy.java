package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerConcreteProxy extends OrderControllerV2 {


    private final LogTrace logTrace;
    private final OrderControllerV2 target;


    public OrderControllerConcreteProxy(LogTrace logTrace, OrderControllerV2 target) {
        super(null);
        this.logTrace = logTrace;
        this.target = target;
    }

    @Override
    public String request(String itemId) {
        log.info("구현체 프록시로 구현했음.");

        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderService.Request()");
            target.request(itemId);
            logTrace.end(status);
            return "ok";
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}

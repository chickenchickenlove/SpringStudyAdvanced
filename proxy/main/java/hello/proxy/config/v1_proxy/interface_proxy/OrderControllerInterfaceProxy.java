package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerInterfaceProxy implements OrderControllerV1 {


    private final OrderControllerV1Impl target;
    private final LogTrace logTrace;


    public OrderControllerInterfaceProxy(OrderControllerV1Impl target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        log.info("인터페이스 프록시로 구현했음.");

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

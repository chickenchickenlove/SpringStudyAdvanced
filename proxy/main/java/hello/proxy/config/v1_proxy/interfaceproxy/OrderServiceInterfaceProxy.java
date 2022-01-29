package hello.proxy.config.v1_proxy.interfaceproxy;

import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceInterfaceProxy implements OrderServiceV1{

    private final OrderServiceV1 target;
    private final LogTrace logTrace;


    public OrderServiceInterfaceProxy(OrderServiceV1 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("orderService.orderItem()");

            //로직 호출
            target.orderItem(itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;

        }
    }

}

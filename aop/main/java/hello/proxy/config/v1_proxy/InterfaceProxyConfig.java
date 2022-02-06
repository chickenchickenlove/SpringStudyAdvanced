package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Autowired
    private LogTrace logTrace;

    @Bean
    public OrderControllerV1 orderControllerV1() {
        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1());
        OrderControllerV1 proxy = new OrderControllerInterfaceProxy(target, logTrace);
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1());
        OrderServiceV1 proxy = new OrderServiceInterfaceProxy(target, logTrace);
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = new OrderRepositoryInterfaceProxy(target, logTrace);
        return proxy;
    }
}

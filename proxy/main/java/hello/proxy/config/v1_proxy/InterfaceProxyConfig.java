package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerInterfaceProxy proxy = new OrderControllerInterfaceProxy(orderControllerV1, logTrace);
        return proxy;
    }


    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        OrderServiceInterfaceProxy proxy = new OrderServiceInterfaceProxy(orderServiceV1, logTrace);
        return proxy;
    }



    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {

        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        OrderRepositoryInterfaceProxy proxy = new OrderRepositoryInterfaceProxy(orderRepositoryV1, logTrace);
        return proxy;
    }
}
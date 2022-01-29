package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concreteproxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concreteproxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concreteproxy.OrderServiceConcreteProxy;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interfaceproxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {



    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(logTrace));
        OrderControllerV2 proxy = new OrderControllerConcreteProxy(orderControllerV2, logTrace);
        return proxy;
    }


    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(logTrace));
        OrderServiceV2 proxy = new OrderServiceConcreteProxy(orderServiceV2, logTrace);
        return proxy;
    }


    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
        OrderRepositoryV2 proxy = new OrderRepositoryConcreteProxy(orderRepositoryV2, logTrace);
        return proxy;
    }


}

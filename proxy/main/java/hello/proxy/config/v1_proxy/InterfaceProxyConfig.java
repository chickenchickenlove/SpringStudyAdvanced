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

        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerInterfaceProxy proxy = new OrderControllerInterfaceProxy(target, logTrace);
        return proxy;
    }



    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        OrderServiceInterfaceProxy proxy = new OrderServiceInterfaceProxy(target,logTrace);
        return proxy;
    }



    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        OrderRepositoryInterfaceProxy proxy = new OrderRepositoryInterfaceProxy(target, logTrace);
        return proxy;
    }

}

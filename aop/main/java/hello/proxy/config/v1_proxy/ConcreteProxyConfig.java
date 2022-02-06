package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concreteproxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concreteproxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concreteproxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Autowired
    private LogTrace logTrace;

    @Bean
    public OrderControllerV2 orderControllerV2() {
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2());
        OrderControllerV2 proxy = new OrderControllerConcreteProxy(target, logTrace);
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2());
        OrderServiceV2 proxy = new OrderServiceConcreteProxy(target, logTrace);
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {
        OrderRepositoryV2 target = new OrderRepositoryV2();
        OrderRepositoryV2 proxy = new OrderRepositoryConcreteProxy(target, logTrace);
        return proxy;
    }
}

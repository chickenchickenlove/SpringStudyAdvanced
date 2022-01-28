package hello.proxy.config.v2_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_proxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {

        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(logTrace));
        LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class}, handler);

        return proxy;
    }




    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {

        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class}, handler);
        return proxy;
    }


    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {

        // 진짜 타겟 만든다.
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                handler);
        return proxy;
    }



}

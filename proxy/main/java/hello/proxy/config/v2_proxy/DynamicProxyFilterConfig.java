package hello.proxy.config.v2_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_proxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_proxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
@Slf4j
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};




    @Bean
    OrderControllerV1 orderControllerV1(LogTrace logTrace) {

        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        LogTraceFilterHandler logTraceFilterHandler = new LogTraceFilterHandler(orderControllerV1, logTrace, PATTERNS);

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                logTraceFilterHandler);

        return proxy;
    }




    @Bean
    OrderServiceV1 orderServiceV1(LogTrace logTrace) {

        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        LogTraceFilterHandler logTraceFilterHandler = new LogTraceFilterHandler(orderServiceV1, logTrace, PATTERNS);

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                logTraceFilterHandler);

        return proxy;
    }


    @Bean
    OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {

        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        LogTraceFilterHandler logTraceFilterHandler = new LogTraceFilterHandler(orderRepositoryV1, logTrace, PATTERNS);

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader()
                , new Class[]{OrderRepositoryV1.class}
                , logTraceFilterHandler);

        return proxy;
    }



}

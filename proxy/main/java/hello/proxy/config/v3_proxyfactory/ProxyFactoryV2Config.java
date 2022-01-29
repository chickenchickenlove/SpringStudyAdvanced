package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class ProxyFactoryV2Config {


    private final LogTrace logTrace;

    public ProxyFactoryV2Config(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    @Bean
    public OrderControllerV2 orderControllerV2() {
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2());
        OrderControllerV2 proxy = (OrderControllerV2)getProxy(target);
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2());
        OrderServiceV2 proxy = (OrderServiceV2) getProxy(target);
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {

        OrderRepositoryV2 target = new OrderRepositoryV2();
        OrderRepositoryV2 proxy = (OrderRepositoryV2) getProxy(target);
        return proxy;
    }


    //== 편의 메서드==//

    private Object getProxy(Object target) {
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor());
        return proxyFactory.getProxy();
    }


    private Advisor getAdvisor() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "save*", "orderItem*");
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }


}

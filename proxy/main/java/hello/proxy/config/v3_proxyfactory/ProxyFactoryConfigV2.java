package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryConfigV2 {



    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {

        OrderControllerV2 target = new OrderControllerV2(orderServiceV2(logTrace));

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        return proxy;
    }




    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {

        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2(logTrace));

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        return proxy;
    }


    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {

        OrderRepositoryV2 target = new OrderRepositoryV2();

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        return proxy;
    }




    public Advisor getAdvisor(LogTrace logTrace) {
        // 포인트컷
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "request*", "orderItem*");

        // 어드바이스
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        // 어드바이저
        return new DefaultPointcutAdvisor(pointcut, advice);
    }



}

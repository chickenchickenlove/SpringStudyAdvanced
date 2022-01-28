package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryConfigV1 {



    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {

        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(logTrace));

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();
        return proxy;
    }




    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {

        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();
        return proxy;
    }


    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {

        OrderRepositoryV1Impl target = new OrderRepositoryV1Impl();

        // 프록시 팩토리 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
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

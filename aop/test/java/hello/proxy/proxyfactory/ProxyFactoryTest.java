package hello.proxy.proxyfactory;

import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class ProxyFactoryTest {

    @Test
    void multipleProxy1() {

        ServiceInterface target = new ServiceImpl();
        // 프록시1 생성
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        proxyFactory1.addAdvice(new advice1());
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // 프록시2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        proxyFactory2.addAdvice(new advice2());
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    void multipleProxy2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new advice2());

        proxyFactory.addAdvisors(advisor1, advisor2);

        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        proxy.save();

    }


    @Test
    void multipleProxy3() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new advice2());

        proxyFactory.addAdvisors(advisor1, advisor2);

        ConcreteService proxy = (ConcreteService)proxyFactory.getProxy();
        proxy.call();

    }




    static class advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("Advice1 실행");
            Object result = invocation.proceed();
            return result;
        }
    }

    static class advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("Advice2 실행");
            Object result = invocation.proceed();
            return result;
        }
    }


}


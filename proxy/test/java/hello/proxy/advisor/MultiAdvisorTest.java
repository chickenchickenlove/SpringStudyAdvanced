package hello.proxy.advisor;

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
public class MultiAdvisorTest {


    @Test
    void multiAdvisorV0() {



        // 프록시 1 생성
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        proxyFactory1.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1()));
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // 이 프록시는 어드바이스는 어드바이스1, target은 ServiceImpl이다.


        // 프록시 2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        proxyFactory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2()));
        ServiceInterface proxy = (ServiceInterface) proxyFactory2.getProxy();

        proxy.find();
    }


    @Test
    void multiAdvisorV1() {


        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);


        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        proxyFactory.addAdvisors(advisor2, advisor1);

        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        proxy.save();


    }





    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice 1 호출");
            return invocation.proceed();
        }
    }

    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice 2 호출");
            return invocation.proceed();
        }
    }


}


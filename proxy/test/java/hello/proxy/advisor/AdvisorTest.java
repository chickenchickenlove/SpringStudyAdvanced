package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.awt.*;
import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1() {

        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice()));

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }


    @Test
    void advisorTest2() {

        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice()));

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();


    }



    @Test
    void advisorTest3() {

        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
        pointCut.setMappedName("save");

        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointCut, new TimeAdvice()));

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();


    }




    static class MyPointCut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }


    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            log.info("포인트컷 호출 method = {}, targetClass = {}", method.getName(), targetClass);
            return method.getName() == matchName ? true : false;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }





}

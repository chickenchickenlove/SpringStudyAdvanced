package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;


@Slf4j
public class CglibTest {


    @Test
    void cglib() {

        ConcreteService concreteService = new ConcreteService();

        Enhancer enhancer = new Enhancer();


        // CGLIB는 부모 클래스를 상속받아서 만든다.
        enhancer.setSuperclass(ConcreteService.class);

        enhancer.setCallback(new TimeMethodInterceptor(concreteService));


        ConcreteService proxy = (ConcreteService)enhancer.create();

        log.info("targetClass = {}",concreteService.getClass());
        log.info("proxyClass = {}",proxy.getClass());

        proxy.call();


    }



}

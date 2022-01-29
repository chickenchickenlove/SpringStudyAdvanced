package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglibTest() {

        // 타겟 생성
        ConcreteService target = new ConcreteService();

        // 몸뚱아리 생성
        TimeMethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor(target);

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(ConcreteService.class);

        enhancer.setCallback(timeMethodInterceptor);

        ConcreteService proxy = (ConcreteService)enhancer.create();
        proxy.call();

        log.info("target = {}", target.getClass());
        log.info("proxy = {}", proxy.getClass());





    }



}

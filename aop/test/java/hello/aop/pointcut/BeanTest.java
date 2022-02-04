package hello.aop.pointcut;

import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(BeanTest.BeanAspect.class)
public class BeanTest {


    @Autowired
    OrderService orderService;

    @Test
    void success() {
        orderService.orderItem("itemA");
    }



    @Aspect
    static class BeanAspect{


        @Around("bean(orderService) || bean(orderRepository)")
        public Object beanAspectLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[beanAspectLog] : {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }


    }




}

package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.AspectClass.class)
//@SpringBootTest
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시
public class ThisTargetTest {

    @Autowired
    MemberService memberService;


    @Test
    void test1() {
        log.info("memberService Proxy = {}", this.memberService.getClass());
        this.memberService.hello("helloA");
    }


    @Aspect
    static class AspectClass{

        @Around("this(hello.aop.member.MemberService)")
        public Object this1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("this-interface : {}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberService)")
        public Object target1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("target-interface : {}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }

        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object this2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("this-concrete : {}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object target2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("target-Concrete : {}", joinPoint.getSignature() );
            return joinPoint.proceed();
        }
    }







}

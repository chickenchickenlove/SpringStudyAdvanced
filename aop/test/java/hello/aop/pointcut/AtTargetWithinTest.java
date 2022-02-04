package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Method;

@Slf4j
@SpringBootTest
@Import(AtTargetWithinTest.Config.class)
public class AtTargetWithinTest {


    @Autowired
    Child child;

    /**
     * args, @args, @target
     * 실행 시점에 어드바이스 적용 여부를 확인한다.
     * 따라서 저걸로 만들면, 기본적으로 모든 스프링 빈에 포인트컷을 적용해서 프록시로 만든다.
     * 실행 시점에 어드바이스 적용 여부를 확인하려면, 기본적으로 프록시 객체가 되고 그 프록시 객체가 어드바이저를 가지고 있어야 하기 때문이다.
     * 이 때, AOP는 보통 CGLIB로 상속받아 만드는데.. 이런 문제는 대부분 final 키워드가 있는 애들은 프록시로 만들 수 없기 때문에 오류가 발생할 수 있음.
     */



    /**
     * @target : 타겟은 프록시 객체의 타겟으로 이해하자. 타겟을 상속 받아 프록시가 만들어졌다. 따라서 전체에 적용된다.
     * 즉, 특정 인스턴스 + 부모 객체가 가지고 있는 모든 메서드를 포인트컷 대상으로 한다.
     * @within : 타겟이 아니기 때문에 withIn 적용된 놈만 지정됨.
     *즉, 특정 인스턴스가 명시적으로 가지고 있는 모든 메서드를 포인트컷 대상으로 한다.
     */

    @Test
    void success() {
        // 포인트컷을 하나라도 만족하는 것이 있기 때문에 Child는 프록시 객체로 만들어진다.
        // 포인트컷 만족은 hello.aop.pointcut & @AOP를 가지고 있기 대문임.
        // 그런데 @target은 부모것까지 다 걸리기 때문에, 부모 메서드를 상속 받아도 다 된다.
        // 그런데 @within은 인스턴스것에만 걸리기 때문에 모른다.
        // 부모 메서드를 오버라이딩 하게 되면, 자식의 메서드가 되기 때문에 포인트컷 대상이 된다.

        log.info("child Proxy = {}", child.getClass());
        child.childMethod();
        child.parentMethod();
    }




    @Aspect
    static class AtTargetWithinAspect{

        @Pointcut("execution(* hello.aop..*(..))")
        public void allOrder(){}

        @Around("allOrder() && @within(hello.aop.member.annotation.ClassAop)")
        public Object atTargetAspect1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("allOrder() && @target(hello.aop.member.annotation.ClassAop)")
        public Object atTargetAspect2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }



    static class Config {

        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetWithinAspect atTargetWithinAspect() {
            return new AtTargetWithinAspect();
        }

    }


    static class Parent{
        public void parentMethod(){}
    }

    @ClassAop
    static class Child extends Parent{
        public void childMethod(){}
    }





}

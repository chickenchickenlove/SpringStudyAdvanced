package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;


@Import(AtAnnotationTest.AtAnnotationAspect.class)
@SpringBootTest
@Slf4j
public class AtAnnotationTest {


    @Autowired
    MemberServiceImpl memberService;


    @Test
    void success() {

        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");

    }



    @Aspect
    static class AtAnnotationAspect{

        @Pointcut("execution (* hello.aop.member..*(..))")
        public void allMember(){}


        @Around("allMember() && @annotation(hello.aop.member.annotation.MethodAop)")
        public Object atAnnotationAspect1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation Proxy 1] : {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }


        @Around("allMember() && @annotation(annotation)")
        public Object atAnnotationAspect2(ProceedingJoinPoint joinPoint, MethodAop annotation) throws Throwable {
            log.info("[@annotation Proxy 2 ] : {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }







    }




}

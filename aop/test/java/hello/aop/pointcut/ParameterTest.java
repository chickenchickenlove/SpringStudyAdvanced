package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {


    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }


    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void allMember() {}


        @Before("allMember()")
        public void doLog1(JoinPoint joinPoint) {
            Object arg = joinPoint.getArgs()[0];
            log.info("doLog 1, args : {}, joinPoint : {}", arg, joinPoint.getSignature());
        }

        @Before("allMember() && args(arg)")
        public void doLog2(JoinPoint joinPoint, String arg) {
            log.info("doLog 2, args : {}, joinPoint : {}", arg, joinPoint.getSignature());
        }


        @Before("allMember() && args(arg)")
        public void doLog3(JoinPoint joinPoint, Object arg) {
            log.info("doLog 3, args : {}, joinPoint : {}", arg, joinPoint.getSignature());
        }

        @Before("allMember() && @annotation(annotation)")
        public void doLog4(JoinPoint joinPoint, MethodAop annotation) {
            log.info("doLog 4, annotation : {}, joinPoint : {}", annotation, joinPoint.getSignature());
        }


        @Before("allMember() && @within(annotation)")
        public void doLog5(JoinPoint joinPoint, ClassAop annotation) {
            log.info("doLog 5, annotation : {}, joinPoint : {}", annotation, joinPoint.getSignature());
        }


        @Before("allMember() && @target(annotation)")
        public void doLog6(JoinPoint joinPoint, ClassAop annotation) {
            log.info("doLog 5, annotation : {}, joinPoint : {}", annotation, joinPoint.getSignature());
        }


        @Before("allMember() && this(obj)")
        public void doLog7(JoinPoint joinPoint, MemberService obj) {
            log.info("doLog 7, this obj : {}, joinPoint : {}", obj.getClass(), joinPoint.getSignature());
        }


        @Before("allMember() && target(obj)")
        public void doLog6(JoinPoint joinPoint, MemberService obj) {
            log.info("doLog 8, this obj : {}, joinPoint : {}", obj.getClass(), joinPoint.getSignature());
        }


    }




}

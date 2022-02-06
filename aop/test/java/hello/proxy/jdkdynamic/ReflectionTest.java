package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {


    @Test
    void reflection0() {

        Hello hello = new Hello();

        // 공통로직 1 실행
        String result1 = hello.callA();
        log.info("공통로직 1 실행 결과 = {}", result1);


        // 공통로직 2 실행
        String result2 = hello.callB();
        log.info("공통로직 2 실행 결과 = {}", result2);

    }



    @Test
    void reflection1() throws Exception {
        Hello hello = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Method methodCallA = classHello.getMethod("callA");

        Object result1 = methodCallA.invoke(hello);

        log.info("result1 = {}",result1);
    }


    @Test
    void reflection2() throws Exception {
        Hello hello = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(hello);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(hello);

        log.info("result1 = {}",result1);
        log.info("result2 = {}",result2);
    }




    static class Hello{
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }


}




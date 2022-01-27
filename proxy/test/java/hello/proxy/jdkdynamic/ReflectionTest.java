package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {


    /**
     * 프록시를 적용하려고 했더니, 적용하려는 프록시만큼 프록시 클래스를 만들어줘야했다.
     * 그런데 잘 살펴보니 데코레이터나 캐싱 같은 것들은 코드가 비슷비슷하다.
     * 이걸 하나로 또 뭉쳐줄 수 있을 것 같다. 근데 왜 안되는 걸까?
     * 그것은 바로 비즈니스 로직의 메서드명이 하드코딩으로 되어있어서, 동적으로 접근할 수 없기 때문이다.
     * 그렇다면 동적으로 넘겨줄 수 있으면 된다.
     */

    @Test
    void reflection0() {

        Hello target = new Hello();


        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result = {}", result1);
        // 공통 로직1 종료


        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result = {}", result2);
        // 공통 로직2 종료
    }



    @Test
    void reflection1() throws Exception {

        // 클래스 정보를 가져온다.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // 공통 로직1 실행
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1 = {}", result1);

    }

    @Test
    void reflection2() throws Exception {

        // 클래스 정보를 가져온다.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1 = {}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2 = {}", result2);
    }

    @Test
    void reflection3() throws Exception {


        // 클래스 정보를 가져온다.
        // 동적으로 메서드를 뺄 수 있게 되었다.

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(target, methodCallA);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(target, methodCallB);
    }



    private void dynamicCall(Hello target, Method methodCallA) throws IllegalAccessException, InvocationTargetException {
        Object result = methodCallA.invoke(target);
        log.info("result = {}", result);
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




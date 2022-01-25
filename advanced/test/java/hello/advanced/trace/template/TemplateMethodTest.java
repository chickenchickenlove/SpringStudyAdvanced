package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {


    @Test
    void templateMethodV0() {

        // 템플릿 패턴 예제1
        // 변하는 부분 : 비즈니스 로직
        // 변하지 않는 부분 : 시간을 측정하는 부분
        logic1();
        logic2();
    }

    private void logic1() {
        // 부가 기능
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        // 부가 기능
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }


    /**
     * 템플릿 메서드 패턴 적용
     */

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

    }


    /**
     * 익명 내부 클래스 적용
     */

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate(){
            // 이렇게 하면 객체를 생성함과 동시에 구현체를 만들 수 있음.
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        AbstractTemplate template2 = new AbstractTemplate(){
            // 이렇게 하면 객체를 생성함과 동시에 구현체를 만들 수 있음.
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");            }
        };

        // 아래 익명 클래스는 TemplateMethodTest 안에서 선언되었다.
        // 따라서 이름은 TemplateMethodTest$1,2,3,4, 이런 식으로 만들어진다.
        log.info("클래스 이름1 = {}", template1.getClass());
        log.info("클래스 이름2 = {}", template2.getClass());

        template1.execute();
        template2.execute();



    }



}

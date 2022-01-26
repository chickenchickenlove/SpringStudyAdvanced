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
        logic1();
        logic2();
    }

    private void logic1() {

        // 부가 기능이 사용된 시간이라고 해보자.
        long startTimeMs = System.currentTimeMillis();


        // 핵심 기능 실행
        log.info("비즈니스 로직 1 실행");

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTime);
    }


    private void logic2() {

        // 부가 기능이 사용된 시간이라고 해보자.
        long startTimeMs = System.currentTimeMillis();

        // 핵심 기능 실행
        log.info("비즈니스 로직 1 실행");

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTime);
    }


    /**
     * 템플릿 메서드 적용
     */

    @Test
    void templateMethodV1() {

        AbstractTemplate template1 = new SubClassLogic1();
        AbstractTemplate template2 = new SubClassLogic2();

        template1.execute();
        template2.execute();

        log.info("subClassLogic1 = {}", template1.getClass());
        log.info("subClassLogic2 = {}", template2.getClass());

    }


    /**
     * 템플릿 메서드 적용 + 익명 클래스 적용
     */

    @Test
    void templateMethodV2() {

        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        template1.execute();
        template2.execute();

        log.info("subClassLogic1 = {}", template1.getClass());
        log.info("subClassLogic2 = {}", template2.getClass());
    }


}

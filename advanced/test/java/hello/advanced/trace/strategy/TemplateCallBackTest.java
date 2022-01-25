package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.CallBack;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {


    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스로 구현
     */

    @Test
    void callBackV1() {

        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();

        timeLogTemplate.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직 실행1");
            }
        });

        timeLogTemplate.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직 실행2");
            }
        });

    }


    /**
     * 템플릿 콜백 패턴 - 람다
     */

    @Test
    void callBackV2() {

        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직 실행 1"));
        timeLogTemplate.execute(() -> log.info("비즈니스 로직 실행 2"));

    }


}

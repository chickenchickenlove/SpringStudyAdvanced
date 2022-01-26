package hello.advanced.strategy;


import hello.advanced.strategy.code.template.CallBack;
import hello.advanced.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {


    @Test
    void callBackV1() {

        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();


        timeLogTemplate.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비지니스 로직 실행1");
            }
        });


        timeLogTemplate.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직 실행2");
            }
        });
    }



    @Test
    void callBackV2() {

        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비지니스 로직 실행1"));
        timeLogTemplate.execute(() -> log.info("비즈니스 로직 실행2"));
    }



}

package hello.advanced.trace.strategy;


// 전략 패턴

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {



    // 템플릿 메서드 패턴 테스트를 복붙한다.

    @Test
    void strategyV0() {

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
     * 전략 패턴 사용
     */

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();


        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();

    }


    /**
     * 익명 내부 클래스를 활용한 전략패턴 사용
     */

    @Test
    void strategyV2() {

        Strategy strategyLogic1 = new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();


        Strategy strategyLogic2 = new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };


        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();


        // ContextV1 클래스로 나온다. 내부 익명 클래스이기 때문이다.
        log.info("strategy1 = {}", strategyLogic1.getClass());
        log.info("strategy2 = {}", strategyLogic2.getClass());
    }


    /**
     * 익명 내부 클래스를 좀 더 편하게 사용
     * 익명 내부 클래스를 변수를 두지 않고, 생성자를 통해서 바로 주입한다.
     */

    @Test
    void strategyV3() {

        ContextV1 context1 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        ContextV1 context2 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }});

        context1.execute();
        context2.execute();
    }

    /**
     * 람다를 활용해서 깔끔하게 작성
     * V3 코드에서 ALT + ENTER를 치면 인텔리제이가 지원해준다.
     */

    @Test
    void strategyV4() {

        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));

        context1.execute();
        context2.execute();
    }






}

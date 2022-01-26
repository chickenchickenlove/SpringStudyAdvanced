package hello.advanced.strategy;

import hello.advanced.strategy.code.strategy.ContextV1;
import hello.advanced.strategy.code.strategy.Strategy;
import hello.advanced.strategy.code.strategy.StrategyLogic1;
import hello.advanced.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
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
        log.info("비즈니스 로직 2 실행");

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTime);
    }


    @Test
    public void strategyV1() {

        ContextV1 contextV1 = new ContextV1(new StrategyLogic1());
        ContextV1 contextV2 = new ContextV1(new StrategyLogic2());

        contextV1.execute();
        contextV2.execute();
    }


    @Test
    public void strategyV2() {


        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        ContextV1 context1 = new ContextV1(strategyLogic1);

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        ContextV1 context2 = new ContextV1(strategyLogic2);

        context1.execute();
        context2.execute();
    }

    @Test
    public void strategyV3() {


        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }});

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }});

        context1.execute();
        context2.execute();
    }

    @Test
    public void strategyV4() {


        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));

        context1.execute();
        context2.execute();
    }


}

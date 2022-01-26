package hello.advanced.strategy;

import hello.advanced.strategy.code.strategy.ContextV2;
import hello.advanced.strategy.code.strategy.Strategy;
import hello.advanced.strategy.code.strategy.StrategyLogic1;
import hello.advanced.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {


    @Test
    void strategyV1() {


        Strategy strategyLogic1 = new StrategyLogic1();
        Strategy strategyLogic2 = new StrategyLogic2();

        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(strategyLogic1);
        contextV2.execute(strategyLogic2);
    }


    @Test
    void strategyV2() {

        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");

            }
        };

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");

            }
        };


        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(strategy1);
        contextV2.execute(strategy2);
    }



    @Test
    void strategyV3() {

        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(() -> log.info("비즈니스 로직1 실행"));
        contextV2.execute(() -> log.info("비즈니스 로직2 실행"));
    }



}

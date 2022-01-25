package hello.advanced.trace.strategy;


// 전략 패턴

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {


    /**
     * 전략 패턴 적용
     */

    @Test
    void strategyV1() {

        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */

    @Test
    void strategyV2() {

        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 호출 with 익명 클래스");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 호출 with 익명 클래스");
            }
        });
    }


    /**
     * 전략 패턴 익명 클래서 + 람다 실행
     */



    @Test
    void strategyV3() {

        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 호출 with 익명 클래스"));
        context.execute(() -> log.info("비즈니스 로직2 호출 with 익명 클래스"));
    }

}

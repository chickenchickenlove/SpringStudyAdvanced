package hello.advanced.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

// 필드에 전략을 보관하고 나머지는 메서드로 한다.
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {

        long startTimeMs = System.currentTimeMillis();

        strategy.call();

        long lastTimeMs = System.currentTimeMillis();
        long resultTime = lastTimeMs - startTimeMs;

        log.info("resultTime = {} ", resultTime);

    }


}

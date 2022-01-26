package hello.advanced.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {

        long startTimeMs = System.currentTimeMillis();

        strategy.call();

        long lastTimeMs = System.currentTimeMillis();
        long resultTime = lastTimeMs - startTimeMs;

        log.info("resultTime = {} ", resultTime);
    }



}

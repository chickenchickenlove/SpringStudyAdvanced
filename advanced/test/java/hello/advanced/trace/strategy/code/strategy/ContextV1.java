package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 필드에 전략을 보관하는 방식
 */

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    // 필드에서 전략을 가지고 있고, 생성자를 통해 주입 받는다.
    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    // 불변 로직을 작성한다.
    // 테스트에서 코드를 가지고 와서 바꿔준다.
    public void execute() {

        long startTime = System.currentTimeMillis();

        strategy.call(); // 위임

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }



}

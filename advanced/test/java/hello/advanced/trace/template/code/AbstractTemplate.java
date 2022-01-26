package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        // 부가 기능이 사용된 시간이라고 해보자.
        long startTimeMs = System.currentTimeMillis();

        // 핵심 기능 실행
        call();

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTime);
    }

    protected abstract void call();

}


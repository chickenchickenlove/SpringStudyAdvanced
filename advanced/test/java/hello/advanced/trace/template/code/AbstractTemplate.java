package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// 템플릿 메서드 패턴 구현을 위한 추상 템플릿
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
//        log.info("비즈니스 로직1 실행"); // 이 부분만 어떻게 풀도록 하면 된다.

        call(); // 비즈니스 로직을 상속으로 푼다.

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);

    }

    protected abstract void call();


}

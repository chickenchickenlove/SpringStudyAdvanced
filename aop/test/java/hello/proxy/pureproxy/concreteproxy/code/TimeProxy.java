package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{

    private final ConcreteLogic target;

    public TimeProxy(ConcreteLogic target) {
        this.target = target;
    }

    @Override
    public void call() {
        long startTime = System.currentTimeMillis();
        target.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("실행 완료, resultTime = {}",resultTime);
    }
}

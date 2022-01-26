package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component target;

    public TimeDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("Time Decorator 시작 ");
        long startTimeMs = System.currentTimeMillis();

        String result = target.operation();

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;

        log.info("Time Decorator 종료, Result Time = {}", resultTime);

        return result;
    }
}

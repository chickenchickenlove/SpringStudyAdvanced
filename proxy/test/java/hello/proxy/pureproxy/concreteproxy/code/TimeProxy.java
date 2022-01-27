package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{

    private final ConcreteLogic target;

    public TimeProxy(ConcreteLogic target) {
        this.target = target;
    }

    @Override
    public String operation() {
        long s = System.currentTimeMillis();

        String result = target.operation();

        long e = System.currentTimeMillis();
        long r = e - s;
        log.info("실행 시간 = {}", r);

        return result;

    }
}


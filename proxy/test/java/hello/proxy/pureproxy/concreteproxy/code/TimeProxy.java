package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private final ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {

        log.info("프록시 객체 소환 ");

        long startTimeMs = System.currentTimeMillis();

        String result = concreteLogic.operation();


        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;

        log.info("프록시 객체 끝, resultTime = {}", resultTime);

        return result;
    }
}

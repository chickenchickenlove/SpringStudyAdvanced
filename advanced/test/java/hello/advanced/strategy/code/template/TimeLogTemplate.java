package hello.advanced.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {


    public void execute(CallBack callBack) {

        long startTimeMs = System.currentTimeMillis();

        callBack.call();

        long endTimeMs = System.currentTimeMillis();
        long resultTime = endTimeMs - startTimeMs;

        log.info("Result Time = {}", resultTime);




    }

}

package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Realsubject implements Subject{
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        // 단순히 1초 기다리고 돌아가는 것.
        sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{


    private final Component target;

    public TimeDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long s = System.currentTimeMillis();

        String result = target.operation();

        long e = System.currentTimeMillis();
        long r = e - s;

        log.info("TimeDecorator 완료, 소요 시간 = {}", r);
        return result;
    }
}

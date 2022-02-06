package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheSubject implements Subject{

    private final Subject target;
    private String cache;

    public CacheSubject(Subject target) {
        this.target = target;
    }

    @Override
    public String call() {
        log.info("프록시 객체 호출 ");
        if (cache == null) {
            cache = target.call();
        }
        return cache;
    }

}

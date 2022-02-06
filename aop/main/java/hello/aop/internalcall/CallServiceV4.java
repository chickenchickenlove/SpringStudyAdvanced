package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV4 {

    /**
     * 메서드 분리
     * 메서드를 2개로 분리해 내부 호출 하지 않도록 설정 
     */


    public void external() {
        log.info("call external");
    }
    public void internal() {
        log.info("call internal");
    }


}

package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {


    private CallServiceV1 callServiceV1;

    /**
     * 자기 자신 주입
     * 순환 문제 발생 → 스프링 2.6부터 지원 X
     */

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 내부 메서드 호출. this.internal()
    }

    public void internal() {
        log.info("call internal");
    }


}

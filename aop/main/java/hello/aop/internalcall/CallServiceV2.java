package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {


    /**
     * 스프링 컨테이너, Object Provider를 이용해 프록시 객체 불러오기
     */


    /*
    private final ApplicationContext context;
    public CallServiceV2(ApplicationContext context) {
        this.context = context;
    }
     */


    private final ObjectProvider<CallServiceV2> provider;

    public CallServiceV2(ObjectProvider<CallServiceV2> provider) {
        this.provider = provider;
    }

    public void external() {
        log.info("call external");

        /* 스프링 컨테이너 사용
        CallServiceV2 callServiceV2 = (CallServiceV2) context.getBean("callServiceV2");
        callServiceV2.internal();
         */

        CallServiceV2 callServiceV2 = provider.getObject();
        callServiceV2.internal();

    }

    public void internal() {
        log.info("call internal");
    }


}

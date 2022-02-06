package hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallServiceV1Test {

    @Autowired
    CallServiceV1 callService;


    @Test
    void external() {
        callService.external();
    }

    @Test
    void internal() {
        callService.internal();
    }



}

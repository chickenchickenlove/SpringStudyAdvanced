package hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallServiceV0Test {

    @Autowired
    CallServiceV0 callService;


    @Test
    void external() {
        callService.external();
    }

    @Test
    void internal() {
        callService.internal();
    }



}

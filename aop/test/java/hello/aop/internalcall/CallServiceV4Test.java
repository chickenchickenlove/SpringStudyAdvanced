package hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallServiceV4Test {

    @Autowired
    CallServiceV4 callService;


    @Test
    void external() {
        callService.external();
        callService.internal();
    }



}

package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootTest
public class BasicTest {


    @Test
    void noPostProcessor() {

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(BasicConfig.class);

        classA a = context.getBean("A", classA.class);
        a.call();
    }



    @Configuration
    static class BasicConfig{

        @Bean
        public classA A() {
            return new classA();
        }

    }


    static class classA{
        public void call(){
            log.info("callA");
        }
    }


    static class classB{
        public void call(){
            log.info("callB");
        }
    }

}

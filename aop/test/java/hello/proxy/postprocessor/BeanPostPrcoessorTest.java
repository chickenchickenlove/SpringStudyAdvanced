package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootTest
public class BeanPostPrcoessorTest {


    @Test
    void beanPostProcessorTest() {

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(BasicConfig.class);

        classB a = (classB) context.getBean("A");
        a.call();
    }

    @Configuration
    static class BasicConfig{
        @Bean
        public classA A() {
            return new classA();
        }
        @Bean
        public Postprocessor postprocessor() {
            return new Postprocessor();
        }
    }

    static class Postprocessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            return new classB();
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

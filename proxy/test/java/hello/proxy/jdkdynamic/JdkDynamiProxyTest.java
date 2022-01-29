package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamiProxyTest {


    @Test
    void dynamicJdkA() {

        AImpl a = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(a);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);

        proxy.call();

        log.info("target = {}", a.getClass());
        log.info("proxy = {}", proxy.getClass());


    }


}

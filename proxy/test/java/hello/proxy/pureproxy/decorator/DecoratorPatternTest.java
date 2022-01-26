package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {


    @Test
    void noDecoratorTest() {

        RealComponent component = new RealComponent();

        // 아래 코드는 변경이 안된다. (DI만 해준다면)
        DecoratePatternClient client = new DecoratePatternClient(component);

        client.execute();
    }


    @Test
    void decoratorV1() {

        RealComponent component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);

        // 아래 코드는 변경이 안된다. (DI만 해준다면)
        DecoratePatternClient client = new DecoratePatternClient(messageDecorator);

        client.execute();
    }


    @Test
    void decoratorV2() {

        RealComponent component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);

        // 아래 코드는 변경이 안된다. (DI만 해준다면)
        DecoratePatternClient client = new DecoratePatternClient(timeDecorator);

        client.execute();
    }



}

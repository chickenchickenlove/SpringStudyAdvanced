package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecoratorTest() {

        RealComponent component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();

    }

    @Test
    void decoratorTestV1() {

        RealComponent component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();

    }


    @Test
    void decoratorTestV2() {
        RealComponent component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();

    }



}

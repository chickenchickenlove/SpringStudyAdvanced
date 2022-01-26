package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

    private Component target;

    public MessageDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("messageDecorator 실행");

        String operation = target.operation();
        String result = "***** " + operation + " *****";

        log.info("messageDecorator 실행 전 = {}, 실행 결과 = {}", operation, result);

        return result;
    }
}

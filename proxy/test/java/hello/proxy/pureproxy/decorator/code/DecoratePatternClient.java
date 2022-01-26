package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratePatternClient {

    private final Component component;

    public DecoratePatternClient(Component component) {
        this.component = component;
    }

    public void execute() {
        String operation = component.operation();
        log.info("result = {}", operation);
    }



}

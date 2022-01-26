package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    /**
     * name을 nameStore에 저장하는 로직
     * 저장할 때 1초간 쉬고 저장한다.
     */


    private String nameStore;

    public String logic(String name) {

        log.info("저장 name = {} -> nameStore = {}", name, nameStore);
        nameStore = name;

        sleep(1000);

        log.info("조회 nameStore = {}",nameStore);
        return nameStore;

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        // 쓰레드 실행 로직
        // 아래 위가 동일한 로직이다.
        Runnable userA = new Runnable() {
            @Override
            public void run() {
                service.logic("userA");
            }
        };


        Runnable userB = () -> {
            service.logic("userB");
        };


        Thread threadA = new Thread(userA);
        threadA.setName("threadA");
        Thread threadB = new Thread(userB);
        threadB.setName("threadB");

        threadA.start(); // threadA가 들어가있는 Runnable 로직을 실행한다.
        sleep(10);
        // 2초를 쉬고 다음 로직을 실행한다.
        // 동시성 문제발생 X.
        threadB.start();

        sleep(3000);
        log.info("main Thread Exit");





    }

    private void sleep(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

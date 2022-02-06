package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    // 5번에 1번씩 에러 발생
    private static int seq = 0;

    @Trace
    @Retry(maxRetryCount = 10, maxAbc = 111)
    public String save(String itemId) {
        seq ++;
        if (seq % 2 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }

}

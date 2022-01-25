package hello.advanced.app.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {


    public void save(String itemId) {

        // ex가 넘어오면 예외 발생 시키는 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생! ");
        }

        // 상품 저장에 1초 정도 걸린다고 가정하자.
        sleep(1000);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

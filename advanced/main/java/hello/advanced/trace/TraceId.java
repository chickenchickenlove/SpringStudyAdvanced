package hello.advanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    // 트랜잭션 ID
    private String id;
    // 깊이
    private int level;

    public TraceId() {
        this.id = createId();
        // 초기 레벨은 0이다.
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // UUID는 너무 길다. 앞에 8자리만 사용함. 편의를 위해서.
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // 레벨 증가를 표현하기 위한 것임.
    public TraceId createNextId() {
        return new TraceId(id, this.level + 1);
    }

    // 레벨 감소를 표현하기 위한 것임.
    public TraceId createPreviousId() {
        return new TraceId(id, this.level - 1);
    }

    // 첫번째 레벨인지 확인
    public boolean isFirstLevel() {
        return level == 0;
    }


}


package hello.advanced.trace;


import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

// 상태를 가지고 있기만 하자.
@Getter
public class TraceStatus {

    private TraceId traceId;
    private String message; //
    private Long startTimeMs; // 시작 시간을 가져야 한다.

    public TraceStatus(TraceId traceId, String message, Long startTimeMs) {
        this.traceId = traceId;
        this.message = message;
        this.startTimeMs = startTimeMs;
    }
}

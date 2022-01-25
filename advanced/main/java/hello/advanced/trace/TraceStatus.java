package hello.advanced.trace;

import lombok.Getter;

@Getter
// 로그를 시작할 때의 상태 정보를 가지고 있다.
// 이 정보는 로그를 종료할 때 사용됨.
public class TraceStatus {

    private TraceId traceId;
    // 시작 시간만 있으면 종료 시간을 알 수 있다.
    private Long startTimeMs;
    // 시작 시 사용한 메세지이다. 이후 로그 종료시에도 이 메세지를 사용해서 출력한다.
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }


}

package hello.advanced.trace.hellotrace;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloTraceV1 {


    private final static String START_PREFIX = "-->";
    private final static String COMPLETE_PREFIX = "<--";
    private final static String ERROR_PREFIX = "<X-";


    // 시작

    public TraceStatus begin(String message) {

        long startTimeMs = System.currentTimeMillis();
        TraceId traceId = new TraceId();

        log.info("[{}] {}{}", traceId.getId(), addSpacer(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, message, startTimeMs);
    }


    public void complete(TraceStatus status, Exception e) {

        Long lastTimeMs = System.currentTimeMillis();
        Long startTimeMs = status.getStartTimeMs();
        Long resultTime = lastTimeMs - startTimeMs;

        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time = {}", traceId.getId(), addSpacer(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTime);
        }else{
            log.info("[{}] {}{} time = {} ex = {}", traceId.getId(), addSpacer(ERROR_PREFIX, traceId.getLevel()), status.getMessage(), resultTime, e.toString());
        }
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private String addSpacer(String prefix, int level) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1 ? "|" + prefix : "|  "));
        }
        return sb.toString();
    }



}

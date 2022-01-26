package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class FieldLogTrace implements LogTrace{

    private final static String START_PREFIX = "-->";
    private final static String COMPLETE_PREFIX = "<--";
    private final static String ERROR_PREFIX = "<X-";

    private TraceId traceIdHolder;

    @Override
    public TraceStatus begin(String message) {

        long startTimeMs = System.currentTimeMillis();
        // 싱그로나이즈를 맞춰준다.
        syncTraceId();
        TraceId traceId = this.traceIdHolder;

        log.info("[{}] {}{}", traceId.getId(), addSpacer(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, message, startTimeMs);
    }

    private void syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
        }else{
            traceIdHolder = traceIdHolder.createNextId();
        }
    }




    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = this.traceIdHolder;

        if (traceId.isFirstLevel()) {
            traceIdHolder = null;
        } else {
            traceIdHolder = traceId.creatPreviousId();
        }
    }


    private String addSpacer(String prefix, int level) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1 ? "|" + prefix : "|  "));
        }
        return sb.toString();
    }

}

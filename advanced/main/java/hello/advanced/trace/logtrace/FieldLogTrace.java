package hello.advanced.trace.logtrace;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;


@Slf4j
// ComponentScan안 쓰고, 직접 스프링 빈으로 등록할 것이다.
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    // traceId 동기화용으로 들고 있는다. 그런데 동시성 이슈가 생긴다.
    // 기존에는 파라미터로 넘김
    private TraceId traceIdHolder;


    public FieldLogTrace() {
    }

    @Override
    public TraceStatus begin(String message) {

        //추가되는 함수
        syncTraceId();

        // 변경되었음. 만드는게 아니라 가지고 있는 걸 사용한다.
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();

        // 로그 출력
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        // traceIdHolder가 없으면 초기화 시켜준다.
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
        } else {
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

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        Long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(),resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs, e.toString());
        }

        // 추가되는 ID
        releaseTraceId();

    }

    private void releaseTraceId() {
        // 마지막 레벨까지 왔는지 본다.
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; // 파괴하겠다는 뜻.
        } else {
            // 아직 돌아가는 중이면, 이전 ID를 준다.
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }


    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }


}

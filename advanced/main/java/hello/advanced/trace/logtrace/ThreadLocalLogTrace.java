package hello.advanced.trace.logtrace;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    //    private TraceId traceIdHolder;
    // 동시성 문제 해결을 위해 쓰레드 로컬을 쓴다.
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    public ThreadLocalLogTrace() {
    }

    @Override
    public TraceStatus begin(String message) {

        //추가되는 함수
        syncTraceId();

        // 변경되었음. 만드는게 아니라 가지고 있는 걸 사용한다.
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();

        // 로그 출력
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();

        // tracedIdHolder가 이제 쓰레드 로컬 저장소로 바뀌었다
        // 따라서 값을 꺼내서 비교해줘야한다.
        if (traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
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

        TraceId traceId = traceIdHolder.get();

        if (traceId.isFirstLevel()) {
            // 첫번째 레벨이면 이면 null을 하면 안된다. 파괴한다는 뜻이니, 로컬 저장소를 없애줘야한다.
            // 특정 쓰레드 저장소가 제거된다.
            traceIdHolder.remove();
        } else {
            // 아직 돌아가는 중이면, 이전 ID를 준다.
            traceIdHolder.set(traceId.createPreviousId());
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

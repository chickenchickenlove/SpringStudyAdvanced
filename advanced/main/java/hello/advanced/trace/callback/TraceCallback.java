package hello.advanced.trace.callback;

// 반환 값이 다를 수 있어 제네릭으로 해준다.
public interface TraceCallback <T>{
    T call();
}

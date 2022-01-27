package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheSubject;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import hello.proxy.pureproxy.proxy.code.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProxyPatternTest {

    @Test
    @DisplayName("프록시 패턴 없이 한다. ")
    void noProxy() {
        Subject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        // 실제 객체 3번 호출 + 3초 대기
        client.execute();
        client.execute();
        client.execute();
    }



    @Test
    @DisplayName("프록시 패턴 : 캐싱을 통해 접근 제어 + 성능 개선 ")
    void proxyPatternTest() {
        Subject realSubject = new RealSubject();
        CacheSubject cacheSubject = new CacheSubject(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheSubject);

        // 실제 객체 3번 호출 + 3초 대기
        client.execute();
        client.execute();
        client.execute();
    }









}

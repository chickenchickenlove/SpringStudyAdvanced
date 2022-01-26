package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.Realsubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxy() {
        ProxyPatternClient client = new ProxyPatternClient(new Realsubject());
        client.execute();
        client.execute();
        client.execute();
    }


    @Test
    void cacheProxyTest() {

        Realsubject realsubject = new Realsubject();
        CacheProxy cacheProxy = new CacheProxy(realsubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }


}

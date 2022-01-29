package hello.proxy.config.v6_aop;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v6_aop.aspect.LogTraceAspect;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class, AppV2Config.class})
@Configuration
public class AopConfig {


    private final LogTrace logTrace;

    public AopConfig(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Bean
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect(logTrace);
    }

}

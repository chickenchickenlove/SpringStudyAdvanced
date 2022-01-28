package hello.proxy.config.v4_postprocessor;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class, AppV2Config.class})
@Configuration
public class BeanPostProcessConfig {


    @Bean
    public PackageLogTracePostProcessor packageLogTracePostProcessor(LogTrace logTrace) {

        // 포인트 컷
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "request*", "orderItem");

        // 어드바이스.
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        // 어드바이저
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);


        return new PackageLogTracePostProcessor("hello.proxy.app", advisor);
    }




}

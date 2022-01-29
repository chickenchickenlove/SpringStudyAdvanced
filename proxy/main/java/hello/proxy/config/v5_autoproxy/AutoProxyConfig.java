package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {


    private final LogTrace logTrace;

    public AutoProxyConfig(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    // 오토 프록시 팩토리는 Advisor가 있으면 포인트컷을 가능한 곳에만 어드바이스를 적용해준다.
    // 포인트컷으로 어디에 넣을지 적용해준다.
    // 그리고 후처리도 해준다.
    // 프록시 팩토리 + 빈 포스트 프로세서가 동작하는 것이다.
    // 포인트컷이 적용할 수 있는 곳이라면, 빈 포스트 프로세서로 넘어간다
    // 빈 후처리기에서 프록시 팩토리로 빈을 넘기고, 어드바이저를 넘겨서 적용해서 만들어준다.

//    @Bean
    public Advisor advisor1() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "orderItem*", "save*");
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }


//    @Bean
    public Advisor advisor2() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");

        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }

    @Bean
    public Advisor advisor3() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");

        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;


    }





}

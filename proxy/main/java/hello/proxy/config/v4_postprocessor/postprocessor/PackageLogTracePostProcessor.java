package hello.proxy.config.v4_postprocessor.postprocessor;


import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final Advisor advisor;
    private final String basePackage;

    public PackageLogTracePostProcessor(Advisor advisor, String basePackage) {
        this.advisor = advisor;
        this.basePackage = basePackage;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 적용 하지 않을 것
        String packName = bean.getClass().getPackage().getName();
        if(!packName.startsWith(basePackage)){
            return bean;
        }

        // 적용할 것
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();

        log.info("CreateProxy! beanName = {}, proxyClass = {}", beanName, proxy.getClass());

        return proxy;
    }

}

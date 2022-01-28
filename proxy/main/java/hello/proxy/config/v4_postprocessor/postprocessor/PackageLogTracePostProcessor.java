package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {


    private final String basePackages;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackages, Advisor advisor) {
        this.basePackages = basePackages;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        String packageName = bean.getClass().getPackageName();

        // 프록시 안 만드면 그냥 빈을 돌려준다.
        if (!packageName.startsWith(basePackages)) {
            return bean;
        }

        // 프록시를 만들어야 한다.
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);

        Object proxy = proxyFactory.getProxy();
        log.info("Create Proxy : target = {}, proxy = {}, bean name = {}", bean.getClass(), proxy.getClass(), beanName);

        return proxy;
    }
}

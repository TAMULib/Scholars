package edu.tamu.scholars.middleware.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;

@Component
public class PatchingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(HateoasPageableHandlerMethodArgumentResolver.class)) {
            // NOTE: work around for spring data rest issue, https://jira.spring.io/browse/DATAREST-1290
            ((HateoasPageableHandlerMethodArgumentResolver) bean).setOneIndexedParameters(true);
        }
        return bean;
    }

}

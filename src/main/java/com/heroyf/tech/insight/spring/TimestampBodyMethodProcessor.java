package com.heroyf.tech.insight.spring;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @author frankfyang
 * @date 2025/6/9 17:05
 */
public class TimestampBodyMethodProcessor implements HandlerMethodArgumentResolver {

    private ApplicationContext applicationContext;

    private RequestResponseBodyMethodProcessor processor;


    public TimestampBodyMethodProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(TimestampRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        this.setupProcessor();
        Object result = this.processor.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        if (!(result instanceof Map)){
            return result;
        }
        ((Map) result).put("timestamp", System.currentTimeMillis());
        return result;
    }

    private void setupProcessor() {
        if (this.processor != null) {
            return;
        }
        RequestMappingHandlerAdapter adapter = this.applicationContext.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodArgumentResolver> resolvers = adapter.getArgumentResolvers();
        for (HandlerMethodArgumentResolver resolver : resolvers) {
            if (resolver instanceof RequestResponseBodyMethodProcessor) {
                this.processor = (RequestResponseBodyMethodProcessor) resolver;
                break;
            }
        }
    }
}

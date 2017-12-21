package cn.com.nlj.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import cn.com.nlj.sso.service.RemoteService;
import cn.com.nlj.sso.service.RemoteServiceImpl;

@Configuration
public class MyInvoker {
	
	@Bean(name="/remoteService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invoker = new HttpInvokerServiceExporter();
        invoker.setService(new RemoteServiceImpl());
        invoker.setServiceInterface(RemoteService.class);
        invoker.afterPropertiesSet();
        return invoker;
    }
}

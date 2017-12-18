package cn.com.nlj.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import cn.com.nlj.sso.service.RemotService;
import cn.com.nlj.sso.service.RemotServiceImpl;

@Configuration
public class MyInvoker {
	
	@Bean(name="/remotService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invoker = new HttpInvokerServiceExporter();
        invoker.setService(new RemotServiceImpl());
        invoker.setServiceInterface(RemotService.class);
        invoker.afterPropertiesSet();
        return invoker;
    }
}

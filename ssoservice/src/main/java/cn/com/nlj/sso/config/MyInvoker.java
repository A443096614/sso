package cn.com.nlj.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import cn.com.nlj.sso.service.LoginService;
import cn.com.nlj.sso.service.LoginServiceImpl;

@Configuration
public class MyInvoker {
	
	@Bean(name="/loginService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invoker = new HttpInvokerServiceExporter();
        invoker.setService(new LoginServiceImpl());
        invoker.setServiceInterface(LoginService.class);
        invoker.afterPropertiesSet();
        return invoker;
    }
}

package cn.com.nlj.sso.config;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import cn.com.nlj.sso.service.LoginService;

@Configuration
@PropertySource(value = "classpath:service.properties")
public class ServiceInvoker {

	@Value("${service.url}")
	private String url;
	@Value("${service.port}")
	private int port;
	@Value("${service.service}")
	private String service;
	
	@Bean
	public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceInterface(LoginService.class);
		String serviceUrl = url + ":" + port + service + "/loginService";
		invoker.setServiceUrl(serviceUrl);
		invoker.setHttpInvokerRequestExecutor(commonsHttpInvokerRequestExecutor());
		invoker.afterPropertiesSet();
		return invoker;
	}
	
	@Bean
	public CommonsHttpInvokerRequestExecutor commonsHttpInvokerRequestExecutor() {
		CommonsHttpInvokerRequestExecutor chr = new CommonsHttpInvokerRequestExecutor();
		HttpClient httpClient = new HttpClient();
		//httpClient.setParams(params);
		chr.setHttpClient(httpClient);
		return chr;
	}
}

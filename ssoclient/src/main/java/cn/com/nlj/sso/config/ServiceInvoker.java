package cn.com.nlj.sso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import cn.com.nlj.sso.service.RemoteService;

@Configuration
@PropertySource(value = "classpath:service.properties")
public class ServiceInvoker {

	@Value("${service.url}")
	private String url;
	@Value("${service.port}")
	private int port;
	@Value("${service.service}")
	private String service;
	
	@Bean(name = "remoteService")
	public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceInterface(RemoteService.class);
		String serviceUrl = url + ":" + port + service + "/remoteService";
		invoker.setServiceUrl(serviceUrl);
		//invoker.setHttpInvokerRequestExecutor(commonsHttpInvokerRequestExecutor());
		invoker.afterPropertiesSet();
		return invoker;
	}
	
	/*@Bean
	public CommonsHttpInvokerRequestExecutor commonsHttpInvokerRequestExecutor() {
		CommonsHttpInvokerRequestExecutor chr = new CommonsHttpInvokerRequestExecutor();
		HttpClient httpClient = new HttpClient();
		//httpClient.setParams(params);
		chr.setHttpClient(httpClient);
		return chr;
	}*/
}

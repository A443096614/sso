package cn.com.nlj.sso.config;
/**
 * @classDesc 类说明：
 * @author NLJ 2017年12月21日 下午12:53:30
 */

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;

import cn.com.nlj.sso.service.SpringServiceAware;

public class ApplicationConfig {

	@Bean
	public SpringServiceAware springServiceAware() {
		SpringServiceAware ssa = new SpringServiceAware();
		return ssa;
	}
	
	@Bean
	public DozerBeanMapperFactoryBean dozerBeanMapper() {
		DozerBeanMapperFactoryBean dozerBeanMapper = new DozerBeanMapperFactoryBean();
		return dozerBeanMapper;
	}
}

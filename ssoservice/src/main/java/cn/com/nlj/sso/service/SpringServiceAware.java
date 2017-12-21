package cn.com.nlj.sso.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @classDesc 类说明：
 * @author NLJ 2017年12月20日 下午1:59:53
 */
public class SpringServiceAware implements BeanFactoryAware{

	private static BeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringServiceAware.beanFactory = beanFactory;
	}
	
	public static Object getService(RemoteApi api) throws ClassNotFoundException {
		Class<?> cls = Class.forName(api.getServiceName());
		return beanFactory.getBean(cls);
	}

}

package cn.com.nlj.sso.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.nlj.sso.shiro.MyShiroRealm;

/***
* 类说明：
* @author nlj
* 2017年12月10日 下午5:24:09
*/
@Configuration
public class ShiroConfig {

	@Bean
	public Realm realm() {
		return new MyShiroRealm();
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		//所有访问都通过认证后才可以访问
		chainDefinition.addPathDefinition("/**", "authc");  
		return chainDefinition;
	}
}

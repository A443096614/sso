package cn.com.nlj.sso.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.nlj.sso.service.RemoteService;
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
		MyShiroRealm realm = new MyShiroRealm();
		return realm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		//静态资源文件
		chainDefinition.addPathDefinition("/layui-v2.2.45/**", "anon");//anon游客访问
		chainDefinition.addPathDefinition("/style/**", "anon");
		chainDefinition.addPathDefinition("/js/**", "anon");
		//验证码
		chainDefinition.addPathDefinition("/defaultKaptcha", "anon");
		//登录请求
		chainDefinition.addPathDefinition("/sys/login", "anon");
		//所有访问都通过认证后才可以访问
		chainDefinition.addPathDefinition("/**", "authc");  
		return chainDefinition;
	}
}

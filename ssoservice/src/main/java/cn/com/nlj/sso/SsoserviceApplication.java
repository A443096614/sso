package cn.com.nlj.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"cn.com.nlj.sso"})
public class SsoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoserviceApplication.class, args);
	}
}

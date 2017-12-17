package cn.com.nlj.sso.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/***
* 类说明：
* @author nlj
* 2017年12月17日 上午11:15:07
*/
@Configuration
public class DataSourceConfig {

    @Primary//@Primary当有多个数据源时必须指定
    @Bean(name="dataSource")
    @ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
    	return DataSourceBuilder.create().build();
    }
}

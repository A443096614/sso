package cn.com.nlj.sso.service;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

/***
* 类说明：
* @author nlj
* 2017年12月18日 下午11:29:24
*/
@Service
public class RemotServiceImpl implements RemotService {

	@Override
	public Object getRemotService(RemotApi api, String method, Object ...params) throws Exception {
		Class<?> cls = Class.forName(api.getServiceName());
		Method setMethod = cls.getDeclaredMethod(method,Integer.class);
		setMethod.setAccessible(true);//设置允许访问 
		Object invoke = setMethod.invoke(cls.newInstance());
		return invoke;
	}

}

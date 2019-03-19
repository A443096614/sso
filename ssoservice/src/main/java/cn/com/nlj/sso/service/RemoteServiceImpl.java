package cn.com.nlj.sso.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/***
* 类说明：
* @author nlj
* 2017年12月18日 下午11:29:24
*/
@Service
public class RemoteServiceImpl implements RemoteService {

	@Override
	public Object getRemotService(RemoteApi api, String method, Object ...params) throws Exception {
		int len = params.length;
		Class<?>[] parameterTypes = null;
		if (params == null || params.length == 0) {
			
		} else {
			parameterTypes = new Class[len];
			for (int i = 0; i < len; i++) {
				if (params[i] instanceof Boolean) {
					parameterTypes[i] = Boolean.TYPE;
				}else if(params[i] instanceof Integer) {
					parameterTypes[i] = Integer.TYPE;
				} else if (params[i] instanceof Map) {
					parameterTypes[i] = Map.class;
				} else if (params[i] instanceof List) {
					parameterTypes[i] = List.class;
				} else {
					parameterTypes[i] = params[i].getClass();
				}
			}
		}
		Object service = SpringServiceAware.getService(api);
		Method setMethod = service.getClass().getMethod(method, parameterTypes);//.getDeclaredMethod(method, parameterTypes);
		setMethod.setAccessible(true);//设置允许访问 
		Object invoke = setMethod.invoke(service, params);
		return invoke;
	}

}

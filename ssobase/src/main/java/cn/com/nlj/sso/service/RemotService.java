package cn.com.nlj.sso.service;
/***
* 类说明：
* @author nlj
* 2017年12月18日 下午11:23:44
*/
public interface RemotService {

	public Object getRemotService(RemotApi api, String method, Object ...params) throws Exception;
}

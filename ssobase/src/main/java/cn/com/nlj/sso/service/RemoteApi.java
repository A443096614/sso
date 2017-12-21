package cn.com.nlj.sso.service;
/***
* 类说明：
* @author nlj
* 2017年12月18日 下午11:26:23
*/
public enum RemoteApi {

	LOGINSERVICE("cn.com.nlj.sso.service.LoginService");
	
	private String serviceName;
	
	private RemoteApi(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
}

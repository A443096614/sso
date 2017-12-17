package cn.com.nlj.sso.dto;

import java.util.HashMap;
import java.util.Map;

/***
* 类说明：用于页面返回
* @author nlj
* 2017年12月16日 下午4:43:58
*/
public class R extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4921793475425942089L;
	
	public R() {
		super.put("code", RCODE.CODE_200.getCode());
		super.put("msg", "操作成功！");
	}
	
	public static R error(RCODE code, String msg) {
		R r = new R();
		r.put("code", code.getCode());
		r.put("msg", msg);
		return r;
	}
	
	public static R error() {
		return error(RCODE.CODE_500, "系统发生异常，请联系来管理员！");
	}
	
	public static R error(String msg) {
		return error(RCODE.CODE_500, msg);
	}
	
	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	
	enum RCODE{
		CODE_200("200"),
		CODE_404("404"),
		CODE_500("500");
		
		private String code;

		private RCODE(String code) {
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

}

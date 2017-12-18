package cn.com.nlj.sso.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/***
* 类说明：
* @author nlj
* 2017年12月16日 下午10:21:15
*/
public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 322459561711034400L;
	
	private String userNo;
	private String userName;
	private Timestamp createTime;
	private Timestamp lastTime;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
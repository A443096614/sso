package cn.com.nlj.sso.dto;

import java.io.Serializable;
import java.util.Date;

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
	
	private Integer id;
    private String userNo;
    private String userName;
    private String isLock;
    private String passWord;
    private Date createTime;
    private Date lastTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}
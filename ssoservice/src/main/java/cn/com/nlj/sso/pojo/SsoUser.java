package cn.com.nlj.sso.pojo;

import java.util.Date;
import java.util.List;

public class SsoUser {
    private Integer id;

    private String userNo;

    private String userName;

    private String passWord;

    private String isLock;

    private Date createTime;

    private Date lastTime;
    
    private List<SsoRole> roleList;

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
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
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

	public List<SsoRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SsoRole> roleList) {
		this.roleList = roleList;
	}
    
    
}
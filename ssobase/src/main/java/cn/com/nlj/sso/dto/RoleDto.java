package cn.com.nlj.sso.dto;

import java.io.Serializable;

/***
* 类说明：
* @author nlj
* 2018年1月13日 下午11:19:50
*/
public class RoleDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 254478597421360943L;

	private Integer id;

    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
}

/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.entity.authenticate.User.java
 * Class:			User
 * Date:			2012-8-2
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.fate.entity;


/** 
 * 
 * TODO 用户信息
 * @author  MrNnnn
 * @data:  2015年5月13日 下午8:31:54
 * @version:  V1.0
 */
public class User {
	
	public static final String STATUS_DISABLED = "disabled";
	public static final String STATUS_ENABLED = "enabled";
	
	private Long userId;
	
	private String realName;

	private String userName;
	
	private String password;
	
	private String plainPassword;
	
	private String salt;
	
	private String phone;
	
	private String email;
	
	private String status = STATUS_ENABLED;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", realname=" + realName + ", username="
				+ userName + ", password=" + password + ", plainPassword="
				+ plainPassword + ", salt=" + salt + ", phone=" + phone
				+ ", email=" + email + ", status=" + status + "]";
	}
}

/**
 * 
 */
package com.fate.utils;

import org.apache.shiro.subject.Subject;

import com.fate.entity.User;
import com.fate.shiro.ShiroUser;

/**
 * @author KETAYAO
 *
 */
public abstract class SecurityUtils {
	public static User getLoginUser() {
		return getShiroUser().getUser();
	}
	
	public static ShiroUser getShiroUser() {
		Subject subject = getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		return shiroUser;
	}

	public static Subject getSubject() {
		return org.apache.shiro.SecurityUtils.getSubject();
	}
}

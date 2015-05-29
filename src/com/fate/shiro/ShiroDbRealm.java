package com.fate.shiro;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fate.entity.User;
import com.fate.service.UserInfoService;
import com.fate.utils.Digests;
import com.fate.utils.Encodes;


/**
 * 
 * TODO 权限认证
 * @author  MrNnnn
 * @data:  2015年5月13日 下午8:54:59
 * @version:  V1.0
 */

public class ShiroDbRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);
	
	private static final int INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final String ALGORITHM = "SHA-1";

	// 是否启用超级管理员
	protected boolean activeRoot = false;
	
	// 是否使用验证码
	protected boolean useCaptcha = false;
	
	//用户信息
	@Autowired
	private UserInfoService infoService;
	
	/**
	 * 给ShiroDbRealm提供编码信息，用于密码密码比对
	 * 描述
	 */	
	public ShiroDbRealm() {
		super();
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		matcher.setHashIterations(INTERATIONS);

		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 认证回调函数, 登录时调用.
	 */
	// TODO 对认证进行缓存处理
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//		if (useCaptcha) {
//			CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
//			String parm = token.getCaptcha();
//			if (!PatchcaServlet.validate(SecurityUtils
//					.getSubject().getSession().getId().toString(), parm.toLowerCase())) {//忽略大小写。
//				throw new IncorrectCaptchaException("验证码错误！");
//			}
//		} 
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		//根据登录名称获取一个User
		User user = infoService.getUserByName(token.getUsername());
		if (user != null) {
			if (user.getStatus().equals(User.STATUS_DISABLED)) {
				throw new DisabledAccountException();
			}
			byte[] salt = Encodes.decodeHex(user.getSalt());
			ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getUserName());
			shiroUser.setUser(user);
			// 这里可以缓存认证
			return new SimpleAuthenticationInfo(shiroUser,user.getPassword(),ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
		
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Collection<?> collection = principals.fromRealm(getName());
		if (collection == null || collection.isEmpty()) {
			return null;
		}
		
		ShiroUser shiroUser = (ShiroUser) collection.iterator().next();
		// 设置、更新User
//		shiroUser.setUser(userService.get(shiroUser.getId()));
		return newAuthorizationInfo(shiroUser);
	}
	
	private SimpleAuthorizationInfo newAuthorizationInfo(ShiroUser shiroUser) {
		Collection<String> hasPermissions = new HashSet<String>();
		Collection<String> hasRoles = new HashSet<String>();
		
		// 是否启用超级管理员 && id==1为超级管理员，构造所有权限 
		if (activeRoot && shiroUser.getId() == 1) {
			hasRoles.add("*");
			hasPermissions.add("*");
			
			if (log.isInfoEnabled()) {
				log.info("使用了" + shiroUser.getLoginName() + "的超级管理员权限:" + "。At " + new Date());
				log.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);
				log.info(shiroUser.getLoginName() + "拥有的权限:" + hasPermissions);
			}
		} else {
			log.info("暂不支持非超级管理员登录");
			hasRoles.add("");
			hasPermissions.add("");
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(hasRoles);
		info.addStringPermissions(hasPermissions);
		
		return info;
	}
	
	public static class HashPassword {
		public String salt;
		public String password;
	}
	
	public static HashPassword encryptPassword(String plainPassword) {
		HashPassword result = new HashPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		result.salt = Encodes.encodeHex(salt);

		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, INTERATIONS);
		result.password = Encodes.encodeHex(hashPassword);
		return result;
	}
	
	/**
	 * 
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @param salt 盐值
	 * @return
	 */
	public static boolean validatePassword(String plainPassword, String password, String salt) {
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), INTERATIONS);
		String oldPassword = Encodes.encodeHex(hashPassword);
		return password.equals(oldPassword);
	}
	
	/* 
	 * 覆盖父类方法，设置AuthorizationCacheKey为ShiroUser的loginName，这样才能顺利删除shiro中的缓存。
	 * 因为shiro默认的AuthorizationCacheKey为PrincipalCollection的对象。
	 * @see org.apache.shiro.realm.AuthorizingRealm#getAuthorizationCacheKey(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser)principals.getPrimaryPrincipal();
		return shiroUser.getLoginName();
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String loginName) {
		ShiroUser shiroUser = new ShiroUser(loginName);
		
		SimplePrincipalCollection principals = new SimplePrincipalCollection(shiroUser, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	/**  
	 * 设置 isActiveRoot 的值  
	 * @param isActiveRoot
	 */
	public void setActiveRoot(boolean activeRoot) {
		this.activeRoot = activeRoot;
	}

	/**  
	 * 设置 useCaptcha 的值  
	 * @param useCaptcha
	 */
	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}
}

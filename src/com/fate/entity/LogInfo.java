package com.fate.entity;

import java.util.Date;

import com.fate.log.LogLevel;

/**
 * 
 * TODO 日志信息
 * @author  MrNnnn
 * @data:  2015年5月15日 下午3:21:30
 * @version:  V1.0
 */
public class LogInfo{
	private Long id;
	
	private String userName;

	private String message;
	
	private String ipAddress;
	
	private Date createTime;
	
	private LogLevel logLevel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

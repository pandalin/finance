package com.company.finance.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @ClassName: SysLog
 * @Description: 系统日志
 * @author linxiaomin@sina.cn
 * @date 2014年6月17日 下午4:31:26
 * 
 */
public class SysLog implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long	serialVersionUID	= 1L;

	private String				log_id;

	/**
	 * 操作类型
	 */
	private String				log_type;

	/**
	 * 操作模块
	 */
	private String				log_module;

	/**
	 * 操作人
	 */
	private String				log_user;

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public String getLog_type() {
		return log_type;
	}

	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}

	public String getLog_module() {
		return log_module;
	}

	public void setLog_module(String log_module) {
		this.log_module = log_module;
	}

	public String getLog_user() {
		return log_user;
	}

	public void setLog_user(String log_user) {
		this.log_user = log_user;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

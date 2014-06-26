package com.company.finance.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * pub_authorities权限表
 * 
 * @author lin
 * 
 */
public class Authorities implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				id;

	private String				authority_code;

	private String				authority_name;

	private String				authority_desc;

	private String				authority_status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority_code() {
		return authority_code;
	}

	public void setAuthority_code(String authority_code) {
		this.authority_code = authority_code;
	}

	public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public String getAuthority_desc() {
		return authority_desc;
	}

	public void setAuthority_desc(String authority_desc) {
		this.authority_desc = authority_desc;
	}

	public String getAuthority_status() {
		return authority_status;
	}

	public void setAuthority_status(String authority_status) {
		this.authority_status = authority_status;
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

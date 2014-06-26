package com.company.finance.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 资源表pub_resources
 * 
 * @author lin
 * 
 */
public class Resources implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				id;

	private String				resource_name;

	private String				resource_type;				// url、method

	private String				resource_parent;			// 上级

	private String				resource_url;				// 资源链接

	private String				resource_desc;

	private Integer				resource_status;

	private Integer				resource_priority;			// 序号

	private String				parent_name;				// 上级资源名称,临时字段
	
	private List<Resources>		childList;					// 子资源

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_parent() {
		return resource_parent;
	}

	public void setResource_parent(String resource_parent) {
		this.resource_parent = resource_parent;
	}

	public String getResource_url() {
		return resource_url;
	}

	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}

	public String getResource_desc() {
		return resource_desc;
	}

	public void setResource_desc(String resource_desc) {
		this.resource_desc = resource_desc;
	}

	public Integer getResource_status() {
		return resource_status;
	}

	public void setResource_status(Integer resource_status) {
		this.resource_status = resource_status;
	}

	public Integer getResource_priority() {
		return resource_priority;
	}

	public void setResource_priority(Integer resource_priority) {
		this.resource_priority = resource_priority;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public List<Resources> getChildList() {
		return childList;
	}

	public void setChildList(List<Resources> childList) {
		this.childList = childList;
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

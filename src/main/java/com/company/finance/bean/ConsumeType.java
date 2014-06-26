package com.company.finance.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @ClassName: ConsumeType
 * @Description: TODO(消费类型)
 * @author linxiaomin@sina.cn
 * @date 2014年6月9日 下午3:28:54
 * 
 */
public class ConsumeType implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				ct_id;

	/**
	 * 分类名称
	 */
	private String				ct_name;

	/**
	 * 描述信息
	 */
	private String				ct_desc;

	/**
	 * 状态
	 */
	private String				ct_status;

	public String getCt_id() {
		return ct_id;
	}

	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_name() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}

	public String getCt_desc() {
		return ct_desc;
	}

	public void setCt_desc(String ct_desc) {
		this.ct_desc = ct_desc;
	}

	public String getCt_status() {
		return ct_status;
	}

	public void setCt_status(String ct_status) {
		this.ct_status = ct_status;
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

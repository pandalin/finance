package com.company.finance.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
* @ClassName: Dictionary
* @Description: TODO(数据字典)
* @author linxiaomin@sina.cn
* @date 2014年6月9日 下午3:31:07
*
 */
public class Dictionary implements Serializable {

	private static final long	serialVersionUID	= 1L;

	
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

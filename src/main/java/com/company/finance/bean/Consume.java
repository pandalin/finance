package com.company.finance.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @ClassName: Finance
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author linxiaomin@sina.cn
 * @date 2014年5月28日 下午2:33:13
 * 
 */
public class Consume implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				consume_id;

	/**
	 * 消费类型
	 */
	private String				consume_type;

	/**
	 * 金额
	 */
	private Double				consume_money;

	/**
	 * 日期
	 */
	private Date				consume_date;

	/**
	 * 描述
	 */
	private String				consume_desc;

	/**
	 * 消费人员
	 */
	private User				consume_user;

	public String getConsume_id() {
		return consume_id;
	}

	public void setConsume_id(String consume_id) {
		this.consume_id = consume_id;
	}

	public String getConsume_type() {
		return consume_type;
	}

	public void setConsume_type(String consume_type) {
		this.consume_type = consume_type;
	}

	public Double getConsume_money() {
		return consume_money;
	}

	public void setConsume_money(Double consume_money) {
		this.consume_money = consume_money;
	}

	public Date getConsume_date() {
		return consume_date;
	}

	public void setConsume_date(Date consume_date) {
		this.consume_date = consume_date;
	}

	public String getConsume_desc() {
		return consume_desc;
	}

	public void setConsume_desc(String consume_desc) {
		this.consume_desc = consume_desc;
	}

	public User getConsume_user() {
		return consume_user;
	}

	public void setConsume_user(User consume_user) {
		this.consume_user = consume_user;
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

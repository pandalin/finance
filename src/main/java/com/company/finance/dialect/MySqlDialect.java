package com.company.finance.dialect;

/**
 * 类似hibernate的Dialect,只精简出分页部分
 * @see http://zhenghuazhi.iteye.com/blog/1124361
 * @author lin
 *
 */
public class MySqlDialect implements Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), limit,
				Integer.toString(limit));
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}

	public String getCountString(String sql) {
		return "select count(1) from (" + sql + ") tmp_count";
	}

	public boolean supportsLimitOffset() {
		return true;
	}  
	  
}

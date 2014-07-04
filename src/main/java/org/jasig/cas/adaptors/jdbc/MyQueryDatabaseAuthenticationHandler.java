/*package org.jasig.cas.adaptors.jdbc;

import javax.validation.constraints.NotNull;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

*//**
 * 
 * <p>
 * 自定义的CAS-SERVER端登录验证{@see
 * org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler}
 * </p>
 * 
 * @author linxiaomin@sina.cn
 * @date 2014年7月3日 下午5:51:00
 * @version v1.0
 *//*
public class MyQueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	@NotNull
	private String	sql;
	
	*//**
	 * 原来的{@link org.jasig.cas.authentication.handler.PasswordEncoder}
	 *//*
	@NotNull
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoders;

	@SuppressWarnings("deprecation")
	protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException {
		
		final String username = getPrincipalNameTransformer().transform(credentials.getUsername());
        final String password = credentials.getPassword();
        try {
        	*//**
        	 * {@link select user_password from pub_user where user_code=?}
        	 * 这里已经是加密后的密码了,由于采用的{@link org.springframework.security.crypto.password.PasswordEncoder}
        	 * 所以这里每次加密的密码是不一样的
        	 *//*
            final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            return this.passwordEncoders.matches(password, dbPassword);
        } catch (final IncorrectResultSizeDataAccessException e) {
            // this means the username was not found.
            return false;
        }
	}
	
	*//**
     * @param sql The sql to set.
     *//*
    public void setSql(final String sql) {
        this.sql = sql;
    }

	public org.springframework.security.crypto.password.PasswordEncoder getPasswordEncoders() {
		return passwordEncoders;
	}

	public void setPasswordEncoders(final org.springframework.security.crypto.password.PasswordEncoder passwordEncoders) {
		this.passwordEncoders = passwordEncoders;
	}

}
*/
package com.company.finance.security;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.company.finance.WebContext;
import com.company.finance.bean.User;
import com.company.finance.service.UserService;

/**
 * 
 * <p>
 * 自定义用户登录验证,相当于 <form-login login-page="/login" default-target-url="/welcome"
 * authentication-failure-url="/loginfailed" always-use-default-target="true"/>
 * 
 * @author linxiaomin@sina.cn
 * @date 2014年6月19日 下午3:44:16
 * @version V1.0
 */
public class MyUsernamePasswordAuthticationFilter extends UsernamePasswordAuthenticationFilter {

	private boolean					postOnly			= true;

	@Autowired
	private UserService				userService;

	/**
	 * 登录成功后的跳转地址/welcome {@link default-target-url}
	 */
	private String					successUrl;

	/**
	 * 登录失败后的跳转地址/loginfailed {@link authentication-failure-url}
	 */
	private String					failureUrl;

	/**
	 * (see
	 * {@code org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler}
	 * )
	 */
	private static final boolean	USE_DEFAULT_TARGET	= true;

	/**
	 * bean初始化的时候调用或者在xml的bean中使用init-method
	 */
	@PostConstruct
	public void init() {
		logger.debug("...init MyUsernamePasswordAuthticationFilter...");

		// 验证成功，跳转的页面SavedRequestAwareAuthenticationSuccessHandler
		SavedRequestAwareAuthenticationSuccessHandler successHandler = (SavedRequestAwareAuthenticationSuccessHandler) this.getSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		successHandler.setAlwaysUseDefaultTargetUrl(USE_DEFAULT_TARGET);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = (SimpleUrlAuthenticationFailureHandler) this.getFailureHandler();
		failureHandler.setDefaultFailureUrl(failureUrl);
		this.setAuthenticationFailureHandler(failureHandler);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("不支持" + request.getMethod()+"方式提交");
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			AuthenticationServiceException authenticationException = new AuthenticationServiceException("用户名或密码不能为空");
			throw authenticationException;
		}
		User user = userService.loadUserByCode(username);
		if (user == null) {
			AuthenticationServiceException authenticationException = new AuthenticationServiceException("用户名或密码错误");
			throw authenticationException;
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		HttpSession session = request.getSession(false);// 不创建新会话
		if (session != null && getAllowSessionCreation()) {
			session.setAttribute(WebContext.CURRENT_USER, user);
		}

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

}

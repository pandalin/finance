package com.company.finance.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Authorities;
import com.company.finance.bean.Resources;
import com.company.finance.dao.AuthoritiesMapper;
import com.company.finance.dao.ResourcesMapper;

/**
 * 
 * <p> 
 *  加载资源与权限的关系,资源做key,对应的权限列表做value
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月19日 下午4:13:17
 * @version v1.0
 */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private Logger											logger		= LoggerFactory.getLogger(getClass());

	@Autowired
	private ResourcesMapper									resourcesMapper;
	
	@Autowired
	private AuthoritiesMapper								authoritiesMapper;

	private AntPathMatcher									urlMatcher	= new AntPathMatcher();

	private static Map<String, Collection<ConfigAttribute>>	resourceMap	= null;

	/**
	 * @param object
	 *            请求的url
	 * @return 返回所请求资源所需要的权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		// 获取请求的url地址
		String url = ((FilterInvocation) object).getRequestUrl();
		if (url.contains("%")) {
			url = url.replaceAll("%", "%25");
		}
		logger.debug("请求地址：" + url);

		Iterator<String> it = resourceMap.keySet().iterator();
		while (it.hasNext()) {
			String _url = it.next();
			if (!StringUtils.isEmpty(_url)) {

				if (_url.indexOf("?") != -1) {
					_url = _url.substring(0, _url.indexOf("?"));
				}
				if (urlMatcher.match(_url, url)) {
					return resourceMap.get(_url);
				}
			}
		}
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * bean 初始化时调用,需要jdk1.5
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			
			//所有有效的权限
			List<Authorities> authList = this.authoritiesMapper.queryAll();
			
			if (authList != null && !authList.isEmpty()) {
				for (Authorities auth : authList) {
					
					ConfigAttribute configAttribute = new SecurityConfig(auth.getAuthority_code());

					//权限对应的资源
					List<Resources> resourceList = this.resourcesMapper.getAuthoritiesResourcesList(auth.getId());
					if (resourceList != null && !resourceList.isEmpty()) {
						for (Resources re : resourceList) {
							String url = re.getResource_url();
							if (url.indexOf("%25") > -1) {
								url = url.substring(0,url.indexOf("%25"))+"**";
							}
							if (resourceMap.containsKey(url)) {
								Collection<ConfigAttribute> value = resourceMap.get(url);
								value.add(configAttribute);
								resourceMap.put(url, value);
							} else {
								Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
								atts.add(configAttribute);
								resourceMap.put(url, atts);
							}
						}
					}
				}
			}

		}

		// Set<Entry<String, Collection<ConfigAttribute>>> resourceSet =
		// resourceMap.entrySet();
		// Iterator<Entry<String, Collection<ConfigAttribute>>> iterator =
		// resourceSet.iterator();

	}

}

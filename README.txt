
本系统采用spring4.0.2+springsecurity3.2.4+mybatis3.2.7构建
	目前暂时只有基础功能部分：
		用户设置
			用户列表
			用户管理
		角色设置
			角色列表
			角色管理
		资源设置
			权限列表
			权限管理
			资源列表
			资源管理
		消费管理
			新增消费
			消费列表
		监控管理
			数据库监控
			操作日志
springsecurity表设计采用了7张表，具体可参加finance.sql初始化语句
	在前期的项目框架搭建的过程中也遇到了很多问题，在解决的过程中也学习到了很多新的知识和方法。
	由于对前端框架不熟悉，前端采用国外的html5模板，运用bootstrap实现界面上一些简易布局。
	后续逐渐完善
参考资料：
	maven仓库地址：
		1.http://mvnrepository.com/
		2.http://search.maven.org/#browse
	springsecurity参考资料：
		http://www.mossle.com/docs/springsecurity3/html/springsecurity.html
		http://blog.csdn.net/bluishglc/article/details/12709557
		http://lengyun3566.iteye.com/blog/1114470
	eclipse模板：
		/**  
		 * <p> 
		 *  ${todo}
		 *
		 * @author  linxiaomin@sina.cn  
		 * @date    ${date} ${time}
		 * @version v1.0  
		 */ 
	git：
		http://download.tortoisegit.org/tgit

#*****************************cas配置************************************
资料：http://my.oschina.net/xpbug/blog/204794
1.CAS证书配置：http://www.kafeitu.me/sso/2010/11/05/sso-cas-full-course.html
2.cas server,下载地址:http://www.jasig.org/cas/download
      将cas server解压后得到modules下cas-server-webapp-3.5.2.war包部署到tomcat下,改名casserver,
      即可访问：https://xxx.com:8443/casserver/login
     美化casserver的界面步骤：
     登录界面：
     1.复制WEB-INF/view/jsp/default,重命名为mytheme
     2.复制WEB-INF/classes/default_views.properties,重命名为mytheme_views.properties
     3.修改其中的default为mytheme
     4.修改WEB-INF/cas.properties中的cas.viewResolver.basename=mytheme_views
      登录成功界面：casGenericSuccess.jsp
      退出界面:casLoginView.jsp
      修改验证方式可参考：spring-cas-server.xml
3.客户端配置：
	注意：既然是使用了httpclient发送请求，如果service url使用的是https, 那需要把不信任的证书先导入到服务器运行环境中
	其他详见spring-security-cas.xml
	
 

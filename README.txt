
系统采用spring4.0.2+springsecurity3.2.4+mybatis3.2.7构建

1.当然，你在设计了7张表之后，那么对于这些之间相互关联的关系内容及信息内容，就得由你来进行维护了，大约有用户、角色、权限、资源的增删改查，并还需要设置用户和角色、角色和权限、权限和资源之间的关系。可考虑分为三个菜单进行维护，用户设置、角色设置、资源设置。 在用户设置里分别管理用户、用户与角色的关系；在角色设置里管理角色、角色与权限的关系； 在资源设置里分别管理权限、权限与资源的关系等
2.maven镜像查找地址：
	1.http://mvnrepository.com/
	2.http://search.maven.org/#browse
3.springsecurity中文文档地址：
	http://www.mossle.com/docs/springsecurity3/html/springsecurity.html
4.springsecurity启动细节：
	http://blog.csdn.net/bluishglc/article/details/12709557
5.eclipse模板：
	/**  
	 * <p> 
	 *  ${todo}
	 * </p>
	 *
	 * @author  linxiaomin@sina.cn  
	 * @date    ${date} ${time}
	 * @version v1.0  
	 */ 
6.springSecurity文档：http://lengyun3566.iteye.com/blog/1114470
7.tortoisegit：
	http://download.tortoisegit.org/tgit/1.8.8.0/
	学习地址：http://blog.csdn.net/luckyyulin/article/details/21090905
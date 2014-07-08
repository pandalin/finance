<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!--Responsive navigation button-->  
        <div class="resBtn">
            <a href="javascript:void(0);"><span class="icon16 minia-icon-list-3"></span></a>
        </div>
        <!--Left Sidebar collapse button-->  
        <div class="collapseBtn leftbar">
             <a href="javascript:void(0);" class="tipR" title="Hide Left Sidebar"><span class="icon12 minia-icon-layout"></span></a>
        </div>

        <!--Sidebar background-->
        <div id="sidebarbg"></div>
        <!--Sidebar content-->
        <div id="sidebar">

            <div class="shortcuts">
                <ul>
                    <li><a href="<c:url value="/welcome"/>" title="Home" class="btip"><span class="icon24 icomoon-icon-home-7"></span></a></li>
                    <li><a href="javascript:void(0);" title="Twitter" class="btip"><span class="icon24 icomoon-icon-twitter"></span></a></li>
                    <li><a href="javascript:void(0);" title="Android" class="btip"><span class="icon24 icomoon-icon-android"></span></a></li>
                    <li><a href="javascript:void(0);" title="Apple" class="btip"><span class="icon24 icomoon-icon-apple"></span></a></li>
                </ul>
            </div><!-- End search -->            

            <div class="sidenav">

                <div class="sidebar-widget" style="margin: -1px 0 0 0;">
                    <h5 class="title" style="margin-bottom:0">菜单管理</h5>
                </div><!-- End .sidenav-widget -->
				
                <div class="mainnav" id="mainnav">
                    <ul>
                        <li>
                            <a href="javascript:void(0);"><span class="icon16 icomoon-icon-folder-4"></span>用户设置</a>
                            <ul class="sub">
                                <li><a href="<c:url value="/user/listUser/%25/%25/%25"/>"><span class="icon16 icomoon-icon-apple"></span>用户列表</a></li>
                                <li><a href="<c:url value="/user/toEdit/%25"/>"><span class="icon16 icomoon-icon-apple"></span>用户管理</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><span class="icon16 icomoon-icon-folder-4"></span>角色设置</a>
                            <ul class="sub">
                                <li><a href="<c:url value="/role/listRole/%25/%25/%25"/>"><span class="icon16 icomoon-icon-apple"></span>角色列表</a></li>
                                <li><a href="<c:url value="/role/toEdit/%25"/>"><span class="icon16 icomoon-icon-apple"></span>角色管理</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><span class="icon16 icomoon-icon-folder-4"></span>资源设置</a>
                            <ul class="sub">
                                <li><a href="<c:url value="/authorities/toList/%25/%25"/>"><span class="icon16 icomoon-icon-apple"></span>权限列表</a></li>
                                <li><a href="<c:url value="/authorities/toEdit/%25"/>"><span class="icon16 icomoon-icon-apple"></span>权限管理</a></li>
                                <li><a href="<c:url value="/resources/toList/%25/%25"/>"><span class="icon16 icomoon-icon-apple"></span>资源列表</a></li>
                                <li><a href="<c:url value="/resources/toEdit/%25"/>"><span class="icon16 icomoon-icon-apple"></span>资源管理</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><span class="icon16 icomoon-icon-folder-4"></span>消费设置</a>
                            <ul class="sub">
                                <li><a href="<c:url value="/consume/toEdit/%25"/>"><span class="icon16 icomoon-icon-apple"></span>新增消费</a></li>
                                <li><a href="<c:url value="/consume/listConsume/%25/%25"/>"><span class="icon16 icomoon-icon-apple"></span>消费列表</a></li>
                                <li><a href="<c:url value="/upload/toupload"/>"><span class="icon16 icomoon-icon-apple"></span>文件上传</a></li>
                            </ul>
                        </li>
                        <li>
                        	<a href="javascript:void(0);"><span class="icon16 icomoon-icon-folder-4"></span>监控管理</a>
                        	<ul class="sub">
                                <li><a href="<c:url value="/admin/druid/index.html"/>" target="_blank"><span class="icon16 icomoon-icon-apple"></span>数据库监控</a></li>
                                <li><a href="#"><span class="icon16 icomoon-icon-apple"></span>操作日志</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div><!-- End sidenav -->

        </div><!-- End #sidebar -->

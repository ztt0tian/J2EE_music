<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.ztt.bean.Admin" %>
<%--
  Created by IntelliJ IDEA.
  Admin: asus-wh
  Date: 2018/2/26
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Admin admin = (Admin) session.getAttribute("login_Admin");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <!--<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar">
    <ul class="nav nav-pills nav-justified">
        <li class="active"><a href="<%=basePath %>index.jsp"><strong>首页</strong></a></li>
        <li><a href="<%=basePath %>musicrank/song_rank?rankType=playTop"><strong>榜单</strong></a></li>
        <li><a href="<%=basePath %>pages/singer.jsp"><strong>歌手</strong></a></li>
        <li><a href="<%=basePath %>pages/songsheet.jsp"><strong>歌单</strong></a></li>
        <li><a href="<%=basePath%>music/recommend"><strong>我的音乐</strong></a></li>
    </ul>
</div>
</body>
</html>

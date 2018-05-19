<%@ page import="com.ztt.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String message= (String) request.getAttribute("init_message");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Z-music</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <!--<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="http://www.bootcss.com/p/buttons/css/buttons.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="top row">
    <strong class="col-md-12 text-center">z-music管理者界面</strong>
</div>
<div class="navbar">
    <ul class="nav nav-pills nav-justified">
        <li class="active"><a href="#"><strong>推荐初始化</strong></a></li>
        <li><a href="#"><strong>歌曲管理</strong></a></li>
        <li><a href="#"><strong>用户管理</strong></a></li>
    </ul>
</div>
<div class="container text-center" style="margin-top: 200px">
    <a href="<%=basePath %>admin/init_data" class="button button-royal button-pill button-giant">推荐初始化</a>
</div>
<div class="footer row navbar-fixed-bottom ">

    <div class="col-md-6 text-center">
        <p>作者：赵田田</p>
    </div>
    <div class="col-md-6 text-center">
        <p>大学：武汉理工大学</p>
    </div>

    <div class="col-md-6 text-center">
        <p>指导老师：潘昊</p>
    </div>
    <div class="col-md-6 text-center">
        <p>邮箱：1290507445@qq.com</p>
    </div>
</div>
<script>
    var mess="<%=message%>";
    if(mess!=null&&mess!="null"){
    alert("<%=message%>");
    }
</script>
</body>
</html>
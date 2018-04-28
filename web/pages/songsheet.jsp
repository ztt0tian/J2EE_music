<%--
  Created by IntelliJ IDEA.
  User: asus-wh
  Date: 2018/4/22
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.ztt.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    User user = (User) session.getAttribute("login_user");
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
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/index.css" rel="stylesheet">
    <link href="<%=basePath %>css/songsheet.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<script src="<%=basePath %>js/swiper.min.js"></script>
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="lgoinForm" role="form" action="<%=basePath%>user/login" method="get">
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="text" name="user.email" class="form-control" id="name2" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="text" name="user.password" class="form-control" id="name23" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <button type="reset" class="btn btn-default">重置</button>
                        <button type="submit" class="btn btn-primary btn-success">登录</button>
                    </div>
                </form>
            </div>
            <!--<div class="modal-footer">-->
            <!--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
            <!--<button type="submit" class="btn btn-primary btn-success">登录</button>-->
            <!--</div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="RegistModalLabel">注册</h4>
            </div>
            <div class="modal-body">
                <form class="RegistForm" role="form" action="<%=basePath%>user/regist" method="get">
                    <%--<div class="form-group" hidden>--%>
                    <%--<input type="text" name="user.id" class="form-control" value="1214564">--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="text" name="user.email" class="form-control" id="dsad" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="user.password" class="form-control" id="ddd" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" name="user.name" class="form-control" id="54" placeholder="请输入名称">
                    </div>
                    <div class="form-group" hidden>
                        <label>用户类型</label>
                        <input type="text" name="user.type" class="form-control" id="545" value="0">
                    </div>
                    <div class="form-group">
                        <button type="reset" class="btn btn-default">重置</button>
                        <button type="submit" class="btn btn-primary btn-success">注册</button>
                    </div>
                </form>
            </div>
            <!--<div class="modal-footer">-->
            <!--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
            <!--<button type="submit" class="btn btn-primary btn-success">登录</button>-->
            <!--</div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="top row">
    <strong class="col-md-4 text-center">z-music</strong>
    <form id="searchForm" role="form" action="<%=basePath%>music/search?" method="get">
        <div class="input-group col-md-3" style="position: relative;float: left">

            <input type="text" class="form-control input-sm" name="keyword">
            <input type="hidden" name="search_type" value="song">
            <a class="input-group-addon btn btn-primary"
               href="javascript:document.getElementById('searchForm').submit();">搜索</a>

        </div>
    </form>
        <%
        if(user==null){


    %>
    <div class="col-md-3 btn-group col-md-offset-2" style="margin-top: 12px">
        <button data-toggle="modal" data-target="#LoginModal" href="" type="button" class="btn btn-default">登录</button>
        <button data-toggle="modal" data-target="#RegisterModal" href="" type="button" class="btn btn-default">注册
        </button>
        <%
        } else {
        %>
        <div class="col-md-4 btn-group col-md-offset-1">
            <strong class="text-center">欢迎,<a href="#"><%=user.getName()%>
            </a></strong>
            <strong class="text-center"> <a href="<%=basePath%>user/exit">注销</a></strong>
            <%
                }
            %>
        </div>
    </div>
    <div class="navbar">
        <ul class="nav nav-pills nav-justified">
            <li><a href="<%=basePath %>index.jsp"><strong>首页</strong></a></li>
            <li><a href="<%=basePath %>pages/ranklist.jsp?rankType=1"><strong>榜单</strong></a></li>
            <li><a href="<%=basePath %>pages/singer.jsp"><strong>歌手</strong></a></li>
            <li  class="active" ><a href="<%=basePath %>pages/songsheet.jsp"><strong>歌单</strong></a></li>
            <li><a href="<%=basePath%>music/recommend"><strong>我的音乐</strong></a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="col-md-3">
        <ul class="nav nav-pills nav-stacked text-center">
            <li class="active"><a href="#">全部</a></li>
            <li><a href="#">最热</a></li>
            <li><a href="#">飙升</a></li>
        </ul>
    </div>
    <div class="col-md-9 pull-right ">
        <div class="col-md-2">
            <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20170709/20170709080149269610.jpg">
        </div>
        <div class="col-md-10 detail">
            <a href="#" class="left"><strong>夜店至嗨中文DJ推荐</strong></a>
            <em class="pull-right">制作人：慕容倾城</em>
            <div class="describle">最新一期最火爆的中文DJ推荐，尽管嗨起来吧！</div>
            <a class="btn btn-primary">播放</a>
            <a class="btn btn-primary">分享</a>
        </div>
        <div class="col-md-12">
            <hr>
        </div>

    </div>
    <div class="col-md-9 pull-right ">
        <div class="col-md-2">
            <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20170709/20170709080149269610.jpg">
        </div>
        <div class="col-md-10 detail">
            <a href="#" class="left"><strong>夜店至嗨中文DJ推荐</strong></a>
            <em class="pull-right">制作人：慕容倾城</em>
            <div class="describle">最新一期最火爆的中文DJ推荐，尽管嗨起来吧！</div>
            <a class="btn btn-primary">播放</a>
            <a class="btn btn-primary">分享</a>
        </div>
        <div class="col-md-12">
            <hr>
        </div>

    </div>
    <div class="col-md-9 pull-right ">
        <div class="col-md-2">
            <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20170709/20170709080149269610.jpg">
        </div>
        <div class="col-md-10 detail">
            <a href="#" class="left"><strong>夜店至嗨中文DJ推荐</strong></a>
            <em class="pull-right">制作人：慕容倾城</em>
            <div class="describle">最新一期最火爆的中文DJ推荐，尽管嗨起来吧！</div>
            <a class="btn btn-primary">播放</a>
            <a class="btn btn-primary">分享</a>
        </div>
        <div class="col-md-12">
            <hr>
        </div>

    </div>
    <div class="col-md-9 pull-right ">
        <div class="col-md-2">
            <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20170709/20170709080149269610.jpg">
        </div>
        <div class="col-md-10 detail">
            <a href="#" class="left"><strong>夜店至嗨中文DJ推荐</strong></a>
            <em class="pull-right">制作人：慕容倾城</em>
            <div class="describle">最新一期最火爆的中文DJ推荐，尽管嗨起来吧！</div>
            <a class="btn btn-primary">播放</a>
            <a class="btn btn-primary">分享</a>
        </div>
        <div class="col-md-12">
            <hr>
        </div>

    </div>
    <div class="row">
        <div class="pagecode col-md-3 col-md-offset-6" >
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>

</div>
<div class="footer row">

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
</body>
</html>

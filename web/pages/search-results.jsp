<%@ page import="com.ztt.bean.User" %>
<%@ page import="com.ztt.bean.Song" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: asus-wh
  Date: 2018/2/26
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
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
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/method.js"></script>
    <script src="<%=basePath%>js/ranklist.js"></script>
</head>
<body>
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">用户登录</h4>
            </div>
            <div class="modal-body">
                <form class="lgoinForm" role="form" action="<%=basePath%>user/login" method="post">
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="text" name="user.email" class="form-control" id="name2" placeholder="请输入邮箱"
                               required>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="user.password" class="form-control" id="name23"
                               placeholder="请输入密码" required>
                    </div>
                    <div class="form-group">
                        <button type="reset" class="btn btn-default">重置</button>
                        <button type="submit" class="btn btn-primary btn-success">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="RegistModalLabel">用户注册</h4>
            </div>
            <div class="modal-body">
                <form class="RegistForm" role="form" action="<%=basePath%>user/regist" method="post">
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="text" name="user.email" class="form-control" placeholder="请输入邮箱" required>
                    </div>
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" name="user.name" class="form-control" placeholder="请输入名称" required>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="user.password" class="form-control" placeholder="请输入密码" required>
                    </div>
                    <div class="form-group" hidden>
                        <label>用户类型</label>
                        <input type="text" name="user.type" class="form-control" value="0">
                    </div>
                    <div class="form-group">
                        <button type="reset" class="btn btn-default">重置</button>
                        <button type="submit" class="btn btn-primary btn-success">注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="top row">
    <strong class="col-md-4 text-center">z-music</strong>
    <form id="searchForm" role="form" action="<%=basePath%>music/search" method="get">
        <div class="input-group col-md-3" style="position: relative;float: left">

            <input type="text" class="form-control input-sm" name="keyword" value="${keyword }">
            <input type="hidden" name="search_type" value="song">
            <a class="input-group-addon btn btn-primary"
               href="javascript:document.getElementById('searchForm').submit();">搜索</a>

        </div>
    </form>
    <%
        if (user == null) {


    %>
    <div class="col-md-3 btn-group col-md-offset-2" style="margin-top: 12px">
        <button data-toggle="modal" data-target="#LoginModal" href="" class="btn btn-default">登录</button>
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
            <li><a href="<%=basePath %>musicrank/song_rank?rankType=playTop"><strong>榜单</strong></a></li>
            <li><a href="<%=basePath %>pages/singer.jsp"><strong>歌手</strong></a></li>
            <li><a href="<%=basePath %>pages/songsheet.jsp"><strong>歌单</strong></a></li>
            <li><a href="<%=basePath%>music/recommend"><strong>我的音乐</strong></a></li>
        </ul>
    </div>
</div>
<div class="container">
    <form id="searchForm2" role="form" action="<%=basePath%>music/search" method="get">
        <div class="input-group col-md-6 col-md-offset-3 row" style="position: relative;float: left">
            <input type="text" class="form-control input-lg" name="keyword" value="${keyword }">
            <input type="hidden" name="search_type" value="song">
            <a class="input-group-addon btn btn-primary"
               href="javascript:document.getElementById('searchForm2').submit();">搜索</a>
        </div>
    </form>
    <ul class="nav nav-pills col-md-8 " style="margin-top: 1em">
        <li id="song"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=song">单曲</a></li>
        <li id="album"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=album">专辑</a></li>
        <li id="singer"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=singer">歌手</a></li>
        <li id="lyric"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=lyric">歌词</a></li>
        <li id="songsheet"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=songsheet">歌单</a></li>
        <li id="mv"><a href="<%=basePath%>music/search?keyword=${keyword }&search_type=mv">MV</a></li>
    </ul>
    <div class="search_song_table col-md-12">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>歌名</th>
                <th>作者</th>
                <th>专辑</th>
                <th>播放/下载</th>
                <th>收藏</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator var="song" value="#request.song_songs" status="st">
                <tr>
                    <td><s:property value="#song.song_name"/></td>
                    <td><s:property value="#song.singer.getSinger_name()"/></td>
                    <td><s:property value="#song.album.getAlbum_name()"/></td>
                    <td class="operate">
                        <a href="<%=basePath %>music/play?song_id=<s:property value="#song.song_id"/>"
                           target="_blank"><img
                                class="img-responsive operateItem" src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="<%=basePath %>music/collect?song_id=<s:property value="#song.song_id"/>"><img
                                id="<s:property value="#song.song_id"/>" class="img-responsive operateItem"
                                src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <div class="pagecode col-md-4 col-md-offset-4 row">
            <ul class="pagination ">
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
</div>
<script type="text/javascript">
    var search_type = GetQueryString("search_type");
    switch (search_type) {
        case 'song':
            $("#song").addClass('active');
            break;
        case 'album':
            $("#album").addClass('active');
            break;
        case 'singer':
            $("#singer").addClass("active");
            break;
        case 'lyric':
            $("#lyric").addClass("active");
            break;
        case 'songsheet':
            $("#songsheet").addClass("active");
            break;
        case 'mv':
            $("#mv").addClass("active");
            break;
    }
</script>
</body>
</html>

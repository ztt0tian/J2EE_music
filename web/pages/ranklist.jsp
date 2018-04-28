<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <link href="<%=basePath %>css/ranklist.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<script src="<%=basePath%>js/ranklist.js"></script>
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
    <form id="searchForm" role="form" action="<%=basePath%>music/search" method="get">
        <div class="input-group col-md-3" style="position: relative;float: left">

            <input type="text" class="form-control input-sm" name="keyword">
            <input type="hidden" name="search_type" value="song">
            <a class="input-group-addon btn btn-primary"
               href="javascript:document.getElementById('searchForm').submit();">搜索</a>

        </div>
    </form>
    <%
        if (user == null) {


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
            <li class="active"><a href="<%=basePath %>musicrank/song_rank?rankType=playTop"><strong>榜单</strong></a></li>
            <li><a href="<%=basePath %>pages/singer.jsp"><strong>歌手</strong></a></li>
            <li><a href="<%=basePath %>pages/songsheet.jsp"><strong>歌单</strong></a></li>
            <li><a href="<%=basePath%>music/recommend"><strong>我的音乐</strong></a></li>
        </ul>
    </div>

    <div class="container rise-rank " id="rise-list-rank">
        <div class="row">
            <div class="col-md-2">
                <ul class="list-group text-center">
                    <a href="<%=basePath %>musicrank/song_rank?rankType=playTop" class="list-group-item active">TOP30</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=collectTop" class="list-group-item">收藏榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=downloadTop" class="list-group-item">下载榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=newTop" class="list-group-item">新歌榜</a>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="itemTitle text-center">
                    <strong style="font-size: 150%">TOP30</strong>
                    <hr>
                </div>
                <table class="table table-striped table-hover" style="margin-top: 10px">
                    <thead>
                    <tr class="text-center">
                        <th>歌名</th>
                        <th>作者</th>
                        <th>专辑</th>
                        <th>播放/下载</th>
                        <th>收藏</th>
                        <th>播放量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator var="song" value="#request.top_play" status="st">
                        <tr>
                            <td><s:property value="#song.song_name"/></td>
                            <td><s:property value="#song.singer.getSinger_name()"/></td>
                            <td><s:property value="#song.album.getAlbum_name()"/></td>
                            <td class="operate">
                                <a href="<%=basePath %>music/play?song_id=<s:property value="#song.song_id"/>" target="_blank"><img
                                        class="img-responsive operateItem" src="<%=basePath %>image/play.png"></a>
                                <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                            </td>
                            <td class="collect">
                                <a href="<%=basePath %>music/collect?song_id=<s:property value="#song.song_id"/>"><img id="<s:property value="#song.song_id"/>" class="img-responsive operateItem"  src="<%=basePath %>image/collection.png"></a>
                            </td>
                            <td><s:property value="#song.listen_nums"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div class="pagecode col-md-6 col-md-offset-3">
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
    </div>
    <div class="container top-rank " id="top-list-rank">
        <div class="row">
            <div class="col-md-2">
                <ul class="list-group text-center">
                    <a href="<%=basePath %>musicrank/song_rank?rankType=playTop" class="list-group-item">TOP30</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=collectTop" class="list-group-item active">收藏榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=downloadTop" class="list-group-item">下载榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=newTop" class="list-group-item">新歌榜</a>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="itemTitle text-center">
                    <strong style="font-size: 150%">收藏榜</strong>
                    <hr>
                </div>
                <table class="table table-striped table-hover" style="margin-top: 10px">
                    <thead>
                    <tr class="text-center">
                        <th>歌名</th>
                        <th>作者</th>
                        <th>专辑</th>
                        <th>播放/下载</th>
                        <th>收藏</th>
                        <th>次数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator var="song" value="#request.top_collect" status="st">
                        <tr>
                            <td><s:property value="#song.song_name"/></td>
                            <td><s:property value="#song.singer.getSinger_name()"/></td>
                            <td><s:property value="#song.album.getAlbum_name()"/></td>
                            <td class="operate">
                                <a href="<%=basePath %>music/play?song_id=<s:property value="#song.song_id"/>" target="_blank"><img
                                        class="img-responsive operateItem" src="<%=basePath %>image/play.png"></a>
                                <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                            </td>
                            <td class="collect">
                                <a href="<%=basePath %>music/collect?song_id=<s:property value="#song.song_id"/>"><img id="<s:property value="#song.song_id"/>" class="img-responsive operateItem"  src="<%=basePath %>image/collection.png"></a>
                            </td>
                            <td><s:property value="#song.collect_nums"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div class="pagecode col-md-6 col-md-offset-3">
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
    </div>
    <div class="container new-rank " id="new-list-rank">
        <div class="row">
            <div class="col-md-2">
                <ul class="list-group text-center">
                    <a href="<%=basePath %>musicrank/song_rank?rankType=playTop" class="list-group-item">TOP30</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=collectTop" class="list-group-item">收藏榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=downloadTop" class="list-group-item active">下载榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=newTop" class="list-group-item">新歌榜</a>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="itemTitle text-center">
                    <strong style="font-size: 150%">下载榜</strong>
                    <hr>
                </div>
                <table class="table table-striped table-hover" style="margin-top: 10px">
                    <thead>
                    <tr>
                        <th>歌名</th>
                        <th>作者</th>
                        <th>专辑</th>
                        <th>播放/下载</th>
                        <th>收藏</th>
                        <th>次数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator var="song" value="#request.top_download" status="st">
                        <tr>
                            <td><s:property value="#song.song_name"/></td>
                            <td><s:property value="#song.singer.getSinger_name()"/></td>
                            <td><s:property value="#song.album.getAlbum_name()"/></td>
                            <td class="operate">
                                <a href="<%=basePath %>music/play?song_id=<s:property value="#song.song_id"/>" target="_blank"><img
                                        class="img-responsive operateItem" src="<%=basePath %>image/play.png"></a>
                                <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                            </td>
                            <td class="collect">
                                <a href="<%=basePath %>music/collect?song_id=<s:property value="#song.song_id"/>"><img id="<s:property value="#song.song_id"/>" class="img-responsive operateItem"  src="<%=basePath %>image/collection.png"></a>
                            </td>
                            <td><s:property value="#song.download_nums"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div class="pagecode col-md-6 col-md-offset-3">
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
    </div>
    <div class="container light-rank " id="light-list-rank">
        <div class="row">
            <div class="col-md-2">
                <ul class="list-group text-center">
                    <a href="<%=basePath %>musicrank/song_rank?rankType=playTop" class="list-group-item">TOP30</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=collectTop" class="list-group-item">收藏榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=downloadTop" class="list-group-item">下载榜</a>
                    <a href="<%=basePath %>musicrank/song_rank?rankType=newTop" class="list-group-item active">新歌榜</a>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="itemTitle text-center">
                    <strong style="font-size: 150%">新歌榜</strong>
                    <hr>
                </div>
                <table class="table table-striped table-hover" style="margin-top: 10px">
                    <thead>
                    <tr class="text-center">
                        <th>歌名</th>
                        <th>作者</th>
                        <th>专辑</th>
                        <th>播放/下载</th>
                        <th>收藏</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator var="song" value="#request.top_new" status="st">
                        <tr>
                            <td><s:property value="#song.song_name"/></td>
                            <td><s:property value="#song.singer.getSinger_name()"/></td>
                            <td><s:property value="#song.album.getAlbum_name()"/></td>
                            <td class="operate">
                                <a href="<%=basePath %>music/play?song_id=<s:property value="#song.song_id"/>" target="_blank"><img
                                        class="img-responsive operateItem" src="<%=basePath %>image/play.png"></a>
                                <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                            </td>
                            <td class="collect">
                                <a href="<%=basePath %>music/collect?song_id=<s:property value="#song.song_id"/>"><img id="<s:property value="#song.song_id"/>" class="img-responsive operateItem"  src="<%=basePath %>image/collection.png"></a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div class="pagecode col-md-6 col-md-offset-3">
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
    </div>
    <script>
        var rankType = GetQueryString("rankType");
        switch (rankType) {
            case 'playTop':
                $("#rise-list-rank").css('display', 'block');
                break;
            case 'collectTop':
                $("#top-list-rank").css('display', 'block');
                break;
            case 'downloadTop':
                $("#new-list-rank").css('display', 'block');
                break;
            case 'newTop':
                $("#light-list-rank").css('display', 'block');
                break;
        }
    </script>
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


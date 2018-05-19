<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.ztt.bean.User" %>
<%@ page import="javax.annotation.Resource" %><%--
  Created by IntelliJ IDEA.
  User: asus-wh
  Date: 2018/2/26
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
    <link rel="stylesheet" href="<%=basePath %>css/swiper.min.css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<script>
    var message = "${regist_success}"
    if (message != null && message != "") {
        alert(message)
    }
</script>
<script>
    $.ajax({
        url: "<%=basePath%>ajax/index",
        type: 'post',
        data: "{}",
        dtatType: 'json',
        contentType: 'application/json; charset=utf-8',//设置编码格式
        error: function () {
            alert('加载异常');
            //window.location.reload();
        },
        success: function (backdata) {
            // alert("success");
            // alert(backdata[1]);
            // alert(backdata.songs);
            //首页热歌
            var topSongs =backdata.top_songs;
            var topSongsArray = eval(topSongs);
            var topElement1=document.getElementById("top_song_1");
            var top_song_1_a=topElement1.getElementsByTagName("a");
            top_song_1_a[0].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[0].song_id);
            top_song_1_a[0].innerHTML=topSongsArray[0].song_singer+"-"+topSongsArray[0].song_name;
            top_song_1_a[1].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[1].song_id);
            top_song_1_a[1].innerHTML=topSongsArray[1].song_singer+"-"+topSongsArray[1].song_name;
            top_song_1_a[2].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[2].song_id);
            top_song_1_a[2].innerHTML=topSongsArray[2].song_singer+"-"+topSongsArray[2].song_name;
            var topElement2=document.getElementById("top_song_2");
            var top_song_2_a=topElement2.getElementsByTagName("a");
            top_song_2_a[0].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[3].song_id);
            top_song_2_a[0].innerHTML=topSongsArray[3].song_singer+"-"+topSongsArray[3].song_name;
            top_song_2_a[1].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[4].song_id);
            top_song_2_a[1].innerHTML=topSongsArray[4].song_singer+"-"+topSongsArray[4].song_name;
            top_song_2_a[2].setAttribute("href","<%=basePath %>music/play?song_id=" + topSongsArray[5].song_id);
            top_song_2_a[2].innerHTML=topSongsArray[5].song_singer+"-"+topSongsArray[5].song_name;
            var newSongs = backdata.songs;
            var songArray = eval(newSongs);
            var tr_id = "new_song_";
            for (var i = 0; i < songArray.length; i++) {
                var new_song_tr = document.getElementById(tr_id + i);
                var cells = new_song_tr.getElementsByTagName("td");
                cells[0].innerText = songArray[i].song_name;//歌名
                cells[1].innerText = songArray[i].song_singer;//歌手
                cells[2].innerText = songArray[i].song_album_name;//專輯名
                cells[3].getElementsByTagName("a")[0].setAttribute("href", "<%=basePath %>music/play?song_id=" + songArray[i].song_id);//設置播放跳轉
                cells[3].getElementsByTagName("a")[1].setAttribute("href", "<%=basePath %>file/download?song_id=" + songArray[i].song_id);//設置下載跳轉
                cells[4].getElementsByTagName("a")[0].setAttribute("href", "<%=basePath %>music/collect?song_id=" + songArray[i].song_id);//設置收收藏跳轉
            }
        }
    });
</script>
<script src="<%=basePath %>js/swiper.min.js"></script>
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
                        <input type="text" name="user.email" class="form-control" id="name2" placeholder="请输入邮箱" required>
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
<div class="modal fade" id="AdminLoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="AdminModalLabel">管理员登录</h4>
            </div>
            <div class="modal-body">
                <form class="lgoinForm" role="form" action="<%=basePath%>admin/login" method="post">
                    <div class="form-group">
                        <label>账号</label>
                        <input type="text" name="admin.adminname" class="form-control" id="admin_account" placeholder="请输入账号" required>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="admin.password" class="form-control" id="admin_password"
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
<div class="top row">
    <a href="<%=basePath %>index.jsp"><strong class="col-md-4 text-center">z-music</strong></a>
    <form id="searchForm" role="form" action="<%=basePath%>music/search" method="get" target="_blank">
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
            <li class="active"><a href="<%=basePath %>index.jsp"><strong>首页</strong></a></li>
            <li><a href="<%=basePath %>musicrank/song_rank?rankType=playTop"><strong>榜单</strong></a></li>
            <li><a href="<%=basePath %>pages/singer.jsp"><strong>歌手</strong></a></li>
            <li><a href="<%=basePath %>pages/songsheet.jsp"><strong>歌单</strong></a></li>
            <li><a href="<%=basePath%>music/recommend"><strong>我的音乐</strong></a></li>
        </ul>
    </div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img class="img-responsive"
                                           src="http://imge.kugou.com/commendpic/20180227/20180227092844509871.jpg">
            </div>
            <div class="swiper-slide"><img class="img-responsive"
                                           src="http://imge.kugou.com/commendpic/20180116/20180116154559427100.jpg">
            </div>
            <div class="swiper-slide"><img class="img-responsive"
                                           src="http://imge.kugou.com/commendpic/20170913/20170913175748750902.jpg">
            </div>
        </div>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>

        <!-- 如果需要导航按钮 -->
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>

        <!--&lt;!&ndash; 如果需要滚动条 &ndash;&gt;-->
        <!--<div class="swiper-scrollbar"></div>-->
    </div>
    <div class="container">
        <div class="col-md-6 left">
            <div class="itemTitle">
                <strong class="leftItem">精选歌单</strong>
                <a class="rightItem" href="<%=basePath%>pages/songsheet.jsp">更多</a>
            </div>
            <div class="gedan row">
                <div class="col-md-4 gedanItem">
                    <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
                </div>
                <div class="col-md-4 gedanItem">
                    <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180502/20180502194026756957.jpg">
                </div>
                <div class="col-md-4 gedanItem">
                    <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180428/20180428102903763635.jpg">
                </div>
                <div class="col-md-4 gedanItem">
                    <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180427/20180427130438136436.jpg">
                </div>
                <div class="col-md-4 gedanItem">
                    <img  class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180428/20180428202607102518.jpg">
                </div>
                <div class="col-md-4 gedanItem">
                    <img class="img-responsive" src="http://imge.kugou.com/soft/collection/150/20180502/20180502232241957002.jpg">
                </div>
            </div>
        </div>
        <div class="col-md-6 left">
            <div class="itemTitle">
                <strong class="leftItem">热门榜单</strong>
                <a class="rightItem" href="<%=basePath%>musicrank/song_rank?rankType=playTop">更多</a>
            </div>
            <div class="bangdan  row">
                <div class="col-md-4 left">
                    <img class="img-responsive"
                         src="http://imge.kugou.com/v2/rank_cover/T1fHd4BXd_1RCvBVdK.jpg_240x240.jpg">
                </div>
                <div class="col-md-8 right" style="padding: 10px">
                    <ul class="list-group" id="top_song_1">
                        <li class="list-group-item"><a href="#">null</a></li>
                        <li class="list-group-item"><a href="#">null</a></li>
                        <li class="list-group-item"><a href="#">null</a></li>
                    </ul>
                </div>
            </div>
            <div class="bangdan  row">
                <div class="col-md-4 left">
                    <img class="img-responsive"
                         src="http://imge.kugou.com/v2/rank_cover/T1M4h4BKKj1RCvBVdK.jpg_240x240.jpg">
                </div>
                <div class="col-md-8 right" style="padding: 10px">
                    <ul class="list-group" id="top_song_2">
                        <li class="list-group-item"><a href="#">null</a></li>
                        <li class="list-group-item"><a href="#">null</a></li>
                        <li class="list-group-item"><a href="#">null</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-6 left">
            <div class="itemTitle">
                <strong class="leftItem">新歌首发</strong>
                <a class="rightItem" id="new-music" href="<%=basePath%>musicrank/song_rank?rankType=newTop">更多</a>
            </div>
            <table class="table table-striped table-hover mytable" style="table-layout: fixed;">
                <thead>
                <tr class="text-center">
                    <th style="width:30%;">歌名</th>
                    <th style="width:15%;">作者</th>
                    <th style="width:30%;">专辑</th>
                    <th style="width:15%;">播放/下载</th>
                    <th style="width:10%;">收藏</th>
                </tr>
                </thead>
                <tbody id="new_top_8_body tbody">
                <tr id="new_song_0">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_1">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_2">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_3">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_4">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_5">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_6">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>
                <tr id="new_song_7">
                    <td>null</td>
                    <td>null</td>
                    <td>null</td>
                    <td class="operate">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/play.png"></a>
                        <a href="#"><img class="img-responsive operateItem" src="<%=basePath %>image/download.png"></a>
                    </td>
                    <td class="collect">
                        <a href="#" target="_blank"><img class="img-responsive operateItem"
                                                         src="<%=basePath %>image/collection.png"></a>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="col-md-6 left">
            <div class="itemTitle">
                <strong class="leftItem">热门歌手</strong>
                <a class="rightItem" href="<%=basePath%>pages/singer.jsp">更多</a>
            </div>
            <div class="geshou row">
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20180117/20180117191408898.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20171227/20171227193328260.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20161209/20161209164303152140.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20180122/20180122113125103.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20170807/20170807145802238.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20180327/20180327183312661.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20180302/20180302142740562.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20160418/20160418100508296.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20140520/20140520101227819989.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20170602/20170602084928427.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20180124/20180124185737137.jpg"
                         class="img-responsive">
                </div>
                <div class="col-md-3 geshouItem">
                    <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/100/20140218/20140218175057634765.jpg"
                         class="img-responsive">
                </div>
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
        <div class="col-md-12 text-center">
            <a data-toggle="modal" data-target="#AdminLoginModal"><p>管理入口</p></a>
        </div>

        <div class="col-md-6 text-center">
            <p>指导老师：潘昊</p>
        </div>
        <div class="col-md-6 text-center">
            <p>邮箱：1290507445@qq.com</p>
        </div>

    </div>
    <script>
        var mySwiper = new Swiper('.swiper-container', {
            direction: 'horizontal',
            loop: true,
            autoplay: true,
            // 如果需要分页器
            pagination: {
                el: '.swiper-pagination',
            },
            // 如果需要前进后退按钮
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        })
    </script>
</body>
</html>

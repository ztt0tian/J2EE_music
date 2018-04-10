<%@ page import="com.ztt.bean.User" %><%--
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
    User user=(User)session.getAttribute("login_user");
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
<script src="<%=basePath %>js/swiper.min.js"></script>
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="lgoinForm" role="form" action="user/login" method="get">
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
                <form class="RegistForm" role="form" action="user/regist" method="get">
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
    <div class="input-group col-md-3" style="position: relative;float: left">
        <input type="text" class="form-control input-sm"><a class="input-group-addon btn btn-primary">搜索</a>
    </div>
    <%
        if(user==null){


    %>
    <div class="col-md-3 btn-group col-md-offset-2" style="margin-top: 12px">
        <button data-toggle="modal" data-target="#LoginModal" href="" class="btn btn-default">登录</button>
        <button data-toggle="modal" data-target="#RegisterModal" href="" type="button" class="btn btn-default">注册</button>
        <%
            }
            else{
                %>
        <div class="col-md-3 btn-group col-md-offset-2">
        <strong class="text-center">欢迎,<a href="#"><%=user.getName()%></a></strong>
            退出
        <%
            }
        %>
    </div>
</div>
<div class="navbar">
    <ul class="nav nav-pills nav-justified">
        <li class="active"><a href="index.html"><strong>首页</strong></a></li>
        <li><a href="ranklist.html?rankType=1"><strong>榜单</strong></a></li>
        <li><a href="singer.html"><strong>歌手</strong></a></li>
        <li><a href="songsheet.html"><strong>歌单</strong></a></li>
        <li><a href="mymusic.html"><strong>我的音乐</strong></a></li>
    </ul>
</div>
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img class="img-responsive"
                                       src="http://imge.kugou.com/commendpic/20180227/20180227092844509871.jpg"></div>
        <div class="swiper-slide"><img class="img-responsive"
                                       src="http://imge.kugou.com/commendpic/20180116/20180116154559427100.jpg"></div>
        <div class="swiper-slide"><img class="img-responsive"
                                       src="http://imge.kugou.com/commendpic/20170913/20170913175748750902.jpg"></div>
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
            <a class="rightItem" href="#">更多</a>
        </div>
        <div class="gedan row">
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
            <div class="col-md-4 gedanItem">
                <img src="http://imge.kugou.com/soft/collection/150/20180221/20180221211513730780.jpg">
            </div>
        </div>
    </div>
    <div class="col-md-6 left">
        <div class="itemTitle">
            <strong class="leftItem">热门榜单</strong>
            <a class="rightItem" href="#">更多</a>
        </div>
        <div class="bangdan  row">
            <div class="col-md-4 left">
                <img class="img-responsive"
                     src="http://imge.kugou.com/v2/rank_cover/T1M4h4BKKj1RCvBVdK.jpg_240x240.jpg">
            </div>
            <div class="col-md-8 right" style="padding: 5px">
                <ul class="list-group">
                    <li class="list-group-item">music1</li>
                    <li class="list-group-item">music1</li>
                    <li class="list-group-item">music1</li>
                </ul>
            </div>
        </div>
        <div class="bangdan  row">
            <div class="col-md-4 left">
                <img class="img-responsive"
                     src="http://imge.kugou.com/v2/rank_cover/T1fHd4BXd_1RCvBVdK.jpg_240x240.jpg">
            </div>
            <div class="col-md-8 right" style="padding: 5px">
                <ul class="list-group">
                    <li class="list-group-item">music1</li>
                    <li class="list-group-item">music1</li>
                    <li class="list-group-item">music1</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-md-6 left">
        <div class="itemTitle">
            <strong class="leftItem">新歌首发</strong>
            <a class="rightItem" href="#">更多</a>
        </div>
        <table class="table table-striped table-hover" style="margin-top: 10px">
            <thead>
            <tr>
                <th>歌名</th>
                <th>作者</th>
                <th>专辑</th>
                <th>播放/下载</th>
                <th>时长</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            <tr>
                <td>music1</td>
                <td>Anna</td>
                <td>album1</td>
                <td class="operate">
                    <img class="img-responsive operateItem" src="image/play.png">
                    <img class="img-responsive operateItem" src="image/download.png">
                </td>
                <td>5:00</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-md-6 left">
        <div class="itemTitle">
            <strong class="leftItem">热门歌手</strong>
            <a class="rightItem" href="#">更多</a>
        </div>
        <div class="geshou row">
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-3">
                <img src="http://singerimg.kugou.com/uploadpic/pass/softhead/240/20161209/20161209164303152140.jpg"
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

        // // 如果需要滚动条
        // scrollbar: {
        //     el: '.swiper-scrollbar',
        // },
    })
</script>
</body>
</html>

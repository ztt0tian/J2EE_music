<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.ztt.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: asus-wh
  Date: 2018/2/26
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user=(User)session.getAttribute("login_user");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    if(user!=null) {
        %>

        <%=user.getId() + user.getName()%>
<%
    }
%>
</body>
</html>

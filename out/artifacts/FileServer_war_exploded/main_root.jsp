<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>刘承济的文件服务器</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        .menu {
            color: blue;
            font-weight: bold;
            font-size: 22px;
            text-decoration: none;
        }

        .filetype {
            color: green;
            font-weight: bold;
            font-size: 22px;
            text-decoration: none;
        }

        .dirtype {
            color: red;
            font-weight: bold;
            font-size: 22px;
            text-decoration: none;
        }

        .lastTime {
            color: gray;
            font-weight: bold;
            font-size: 22px;
        }

        .size {
            color: gray;
            font-weight: bold;
            font-size: 22px;
        }

        .table1 {
            width: 100%;
            border-collapse: collapse;
        }

        .td_center {
            text-align: center;
        }

        th {
            color: white;
            font-weight: bold;
            font-size: 22px;
            background-color: green;
        }
    </style>
</head>
<%request.setCharacterEncoding("utf-8"); %>
<body>
<%--        <form action="Test" method="post">--%>
<%--            姓名：<input type="text" name="name"/><br>--%>
<%--            省份：<input type="text" name="province"><br>--%>
<%--            <input type="submit" value="返回到最起点目录">--%>
<%--        </form>--%>
<h1>欢迎您，管理员！</h1>
<br>
<a href="test" class="menu">显示根目录</a>
<br>
<a href="index.jsp" class="menu">注销登录</a>
<br
<a href="index.jsp" class="menu">管理用户</a>
<br>
<c:forEach items="${array}" var="f">
    ${f }
</c:forEach>

<form method="post" action="UploadServlet" enctype="multipart/form-data">
    <input type="file" name="file" value="file">
    <input type="submit" value="确定">
</form>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: ShanYang
  Date: 2019/9/2
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <base href="<%=basePath%>">

        <title>login</title>
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <!--必要样式-->
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <!--[if IE]>
        <script src="js/html5.js"></script>
        <![endif]-->
    </head>
    <body>
    <div class="container demo-1">
        <div class="content">
            <div id="large-header" class="large-header">
                <canvas id="demo-canvas"></canvas>
                <div class="logo_box">
                    <h3>欢迎使用lcjCloud文件服务系统</h3>
                    <form action="./login-action.jsp" method="post">
                        <div class="input_outer">
                            <span class="u_user"></span>
                            <input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
                        </div>
                        <div class="mb2">
                            <input type="submit" class="act-but submit" style="color: #FFFFFF" value="登录">
                        </div>
                    </form>

<%--                    <form action="./login-action.jsp" method="post">--%>
<%--                        用户名<input name="username">(只能由字母组成,3~12位) <br>密码<input--%>
<%--                            type="password" name="password">(6~12位)<br> <input--%>
<%--                            type="submit"  value="登录">--%>

<%--                    </form>--%>
                    <!-获取值,有就输出,没有就为空 --->
                    ${requestScope["kong"] }${requestScope["YWLJ"] } ${requestScope["BPP"] }
                </div>
            </div>
        </div>
    </div><!-- /container -->
    <script src="js/TweenLite.min.js"></script>
    <script src="js/EasePack.min.js"></script>
    <script src="js/rAF.js"></script>
    <script src="js/demo-1.js"></script>
    <script src="js/login.js"></script>
    </body>
</html>

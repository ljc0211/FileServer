<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <!-- 引入CSS -->
    <link href="h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <!-- 引入JS -->
    <script type="text/javascript" src="h-ui/js/H-ui.js"></script>
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="h-ui/lib/icheck/jquery.icheck.min.js"></script>

    <script type="text/javascript">
        $(function () {
            //注册操作
            $("#register").click(function () {
                var data = $("#form").serialize();
                $.ajax({
                    type: "post",
                    //验证登录
                    url: "RegisterServlet?method=register",
                    data: data,
                    dataType: "text", //设置返回的数据类型
                    success: function (msg) {
                        if ("registerError" == msg) {
                            $.messager.alert("消息提醒", "用户名或密码错误 !", "warning");
                        } else if ("registerSuccess" == msg) {
                            //进入系统主页面
                            window.location.href = "SysMainInterfaceServlet?method=toMainView";
                        }
                    }

                });
            });
            //登录操作
            $("#submitBtn").click(function () {
                var data = $("#form").serialize();
                $.ajax({
                    type: "post",
                    //验证登录
                    url: "LoginServlet?method=login",
                    data: data,
                    dataType: "text", //设置返回的数据类型
                    success: function (msg) {
                        if ("loginError" == msg) {
                            $.messager.alert("消息提醒", "用户名或密码错误 !", "warning");
                        } else if ("loginSuccess" == msg) {
                            //进入系统主页面
                            window.location.href = "SysMainInterfaceServlet?method=toMainView";
                        }
                    }

                });
            });
        })
    </script>
    <title>登录页面 | 文件服务系统</title>
    <meta name="keywords" content="学生信息管理系统">
</head>

<body style="font-weight: lighter; ">
<div class="header" style="padding: 0;">
    <h3 style="font-weight: lighter; color: white; width: 500px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">
        欢迎使用LLCloud文件服务器
    </h3>
</div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="form" class="form form-horizontal" method="post">
            <!-- 输入框 -->
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-8">
                    <input id="" name="userName" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-8">
                    <input id="" name="userPassword" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>

            <!-- 注册按钮 -->
            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="submitBtn" type="button" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>

            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="register" type="button" class="btn btn-success radius size-L"
                           value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;">
                </div>
            </div>
        </form>
        <%--    <form id="reg" method="post">--%>
        <%--      <div class="formControls col-8 col-offset-3">--%>
        <%--        <input id="register" type="button" class="btn btn-success radius size-L" value="注册">--%>
        <%--      </div>--%>
        <%--    </form>--%>
    </div>
</div>
<div class="footer">刘坤、刘承济 | 项目地址 : https://github.com/ljc0211/FileServer</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>业务逻辑判断</title>
</head>
    <body>



    <%
        //判断是否为空
        if (request.getParameter("username").equals("")
                && request.getParameter("password").equals("")) {
            //为空就将请求转发到登录页面报错
            request.setAttribute("kong", "您输入的内容为空");
            request.getRequestDispatcher("./index.jsp").forward(
                    request, response);
        } else {
            //不是空就创建
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //判断用户名密码是否符合业务逻辑
            if (username.matches("[a-zA-Z]{3,12}")
                    && password.matches("[a-zA-Z0-9]{6,12}")) {

                //判断用户名密码是否正确,正确就跳转到欢迎页面
                if (username.equals("tom") && password.equals("123456")) {
                    out.print("welcome!" + username);
                    //使用一个指定的名称绑定一个对象到session会话,request也有此方法
                    session.setAttribute("username", username);
                    //跳转到欢迎页面
                    response.sendRedirect("./main.jsp");
                } else {
                    //用户名密码不正确时
                    request.setAttribute("BPP", "您输入的用户名密码不匹配");
                    request.getRequestDispatcher("./index.jsp")
                            .forward(request, response);
                }
            } else {
                //不符合业务逻辑时
                request.setAttribute("YWLJ", "请按要求输入用户名和密码");
                request.getRequestDispatcher("./index.jsp")
                        .forward(request, response);

            }
        }
    %>


    </body>
</html>
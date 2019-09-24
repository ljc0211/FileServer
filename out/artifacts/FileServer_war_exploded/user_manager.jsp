<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户管理</title>
</head>
<body> <form method="post">
    请输入查询单号：<input type="text" maxlength="20" size="10" name="select"><input value="提交" type="submit" name="button1">
</form></body>
<%
    String select=request.getParameter("select");
    String sql=null;
    out.print(select);
    try{Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=GBK","root","123");
        sql="select * from user_admin";
        ps=con.prepareStatement(sql);
        ps.setString(1,select);
        rs=ps.executeQuery();
        while(rs.next()){
            out.print(rs.getString("status")+"--");
            out.print(rs.getString("id")+"--");
            out.print(rs.getString("name")+"--");
            out.print(rs.getString("price")+"--");
        }
        rs.close();
        ps.close();
        con.close();}
    catch(Exception e)
    {
        out.println(e.getMessage());
    }
%>
</html>
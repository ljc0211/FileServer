<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>消息提示</title>
    <script type="text/javascript">

        function next(){

            window.location = "main.jsp";

        }

    </script>
</head>

<body>
    ${message}
    <input type="button" value="返回文件存储页" onclick="next()">
</body>
</html>
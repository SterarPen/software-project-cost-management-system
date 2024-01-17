<%--
  Created by IntelliJ IDEA.
  User: pengxiong
  Date: 2023/12/5
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message</title>
    <style>
        a {
            display: block;
            margin: 0 auto;
            color: darkgray;
            width: 50%;
            height: 30px; border:1px solid dimgray; text-align: center
        }
        a:hover {
            color: red;
        }
        div {
            font-size: 20px;
            color:white;
            margin: 0 auto;
            width: 50%; height: 400px; background-color: #4e73df
        }
    </style>
</head>
<body>
<a href="${ahead_link}">返回上一个页面</a>
<div style="">${message}</div>

</body>
</html>

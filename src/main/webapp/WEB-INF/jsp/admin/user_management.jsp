<%--
  Created by IntelliJ IDEA.
  User: pengxiong
  Date: 2023/12/3
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/admin/user_management.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/user_management.js"></script>
</head>
<body>
<div class="tar">
    <button id="add_user_button">创建用户</button>
    <button id="delete_user_button">删除用户</button>
    <input type="text" placeholder="请输入查询内容"/>
    <button>搜索</button>
</div>
<div class="reqiurement">
    <table id="user_information_table" style="top: 70px;">
        <tr style="position: sticky; top: -1px;">
            <th id="th_id" style="width: 100px;">ID</th>
            <th id="th_content" style="width: 160px;">姓名</th>
            <th id="th_create_time" style="width: 150px;">手机号</th>
            <th id="th_status" style="width: 170px;">邮箱</th>
            <th id="th_type" style="width: 130px;">创建时间</th>
            <th id="th_operate" style="width: 80px;">角色</th>
            <th id="th_handling_project" style="width: 60px">当前项目</th>
        </tr>
    </table>
</div>

<div id="mask"></div>

<div class="dialog" id="add_user_dialog">
    <div>创建用户</div>
    <div>
        <form action="/admin/op/add_user" method="post">
            <input name="name" type="text" placeholder="用户名"/>
            <input name="password" type="text" placeholder="密码"/>
            <input name="email" type="email" placeholder="邮箱"/>
            <input name="phone" type="text" placeholder="手机号"/>
            <select id="role" name="role">
                <option value="1">购买方</option>
                <option value="2">项目经理</option>
            </select>
            <input name="projectId" type="text" placeholder="项目ID"/>
            <input id="add_user_submit_button" type="submit" value="确认"/>
        </form>
        <button class="cancel">取消</button>
    </div>
</div>
<div class="dialog" id="delete_user_dialog">
    <div>删除用户</div>
    <div>
        <form action="/admin/op/delete_user" method="post">
            <input name="id" type="text" placeholder="请输入删除用户的ID"/>
            <select id="role_select" name="role">
                <option value="1">购买方</option>
                <option value="2">项目经理</option>
            </select>
            <input id="delete_user_submit_button" type="submit", value="确认"/>
        </form>
        <button class="cancel">取消</button>
    </div>
</div>
</body>
</html>
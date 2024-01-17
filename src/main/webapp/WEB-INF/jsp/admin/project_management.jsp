<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/admin/project_management.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/project_management.js"></script>
</head>
<body>
<div id="thread">
    <button id="add_project_button">添加项目</button>
    <button id="delete_project_button">删除项目</button>
    <input type="text" placeholder="请输入查询内容" style="margin-left: 500px"/>
    <button>搜索</button>
</div>
<div class="reqiurement">
    <table id="project_information_table" style="top: 70px;">
        <tr style="position: sticky; top: -1px;">
            <th id="th_id" style="width: 60px;">ID</th>
            <th id="th_content" style="width: 450px;">项目名</th>
            <th id="th_create_time" style="width: 160px;">创建时间</th>
            <th id="th_status" style="width: 80px;">(预计)完成时间</th>
            <th id="th_type" style="width: 70px;">购买方</th>
            <th id="th_operate" style="width: 30px;">项目经理</th>
        </tr>
    </table>
</div>

<div id="mask"></div>

<div class="dialog" id="add_project_dialog">
    <div>添加项目</div>
    <div>
        <form action="/admin/op/add_project" method="post" accept-charset="UTF-8">
            <input name="project_name" type="text" placeholder="项目名称">
            <select id="buyer" name="buyer_id">  </select>
            <select id="developer_manager" name="developer_manager_id">  </select>
            <input id="add_project_submit_button" type="submit" value="确认"/>
        </form>
        <button class="cancel">取消</button>
    </div>
</div>
<div class="dialog" id="delete_project_dialog">
    <div>删除项目</div>
    <div>
        <form action="/admin/op/delete_project" method="post">
            <input name="project_id" type="text" placeholder="请输入删除项目的ID"/>
            <input id="delete_project_submit_button" type="submit", value="确认"/>
        </form>
        <button class="cancel">取消</button>
    </div>
</div>
</body>
</html>
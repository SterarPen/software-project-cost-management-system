<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/manager/project_task.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manager/project_task.js"></script>
</head>
<body>
<div class="top">
    <div class="left">
        <button class="new_requirement" onclick="addDemand()">新建任务</button>
        <button class="modify_requirement" onclick="modifyDemand()">修改任务</button>
        <button class="delete_requirement" onclick="deleteDemand()">删除任务</button>
    </div>
    <div class="right">
        <form id="query_condition" action="/data/task/condition" method="post">
            <input type="text" name="task_id" placeholder="请输入查询内容"/>
            <select name="stage" id="id_stage">
            </select>
            <select name="status">
                <option value="">全部</option>
                <option value="0">
                    未完成
                </option>
                <option value="1">
                    已完成
                </option>
            </select>
            <button>搜索</button>
        </form>
    </div>

</div>
<table class="table" id="requirement_table">
    <thead>
    <tr>
        <th class="one">ID</th>
        <th class="two">内容</th>
        <th class="three">创建时间</th>
        <th class="three">结束时间</th>
        <th class="four">状态</th>
        <th class="five">已支出</th>
        <th class="six">阶段名</th>
    </tr>
    </thead>
</table>

<div class="mask"></div>

<div class="dialog" id="create_requirement">
    <div class="dialog_title">添加任务</div>
    <div>
        <form action="/operate/task/add" method="post">
            <textarea class="text_req" id="text_task" name="task_content" placeholder="任务内容"></textarea>
            <label>开始时间：</label>
            <input type="date" name="start_time"/>
            <label>结束时间：</label>
            <input type="date" name="end_time"/>
            <input type="text" name="cost" placeholder="已支出金额"/>
            <select name="stage_id" class="class_stage_name" id="id_add_stage_name"> </select>
            <button type="submit" class="confirm" >确认</button>
            <button type="button" class="cancel" onclick="cancel()">取消</button>
        </form>
    </div>
</div>

<div class="dialog" id="modify_requirement">
    <div class="dialog_title">修改任务</div>
    <div>
        <form action="/operate/task/update" method="POST">
            <div class="query_demand">
                <input type="text" class="demand_id" id="demand_id1" name="task_id" placeholder="任务ID"/>
                <button type="button" class="query" onclick="getTask()">查询</button>
            </div>

            <textarea class="text_req" id="id_update_task_content" name="task_content"></textarea>
            <label>开始时间：</label>
            <input type="date" id="id_update_start_time" name="start_time"/>
            <label>结束时间：</label>
            <input type="date" id="id_update_end_time" name="end_time"/>
            <input type="text" id="id_update_cost" name="cost" placeholder="已支出金额"/>
            <select  name="status">
                <option value="0">未完成</option>
                <option value="1">已完成</option>
            </select>

            <button type="submit" class="confirm">确认</button>
            <button class="cancel" type="button" onclick="cancel()">取消</button>
        </form>
    </div>
</div>

<div class="dialog" id="update_requirement">
    <div class="dialog_title">删除任务</div>
    <div class="dialog_content">
        <form action="/operate/task/delete" method="POST">
            <input type="text" class="class_task_id" id="demand_id2" name="task_id"  placeholder="任务ID"/>
            <button class="confirm" type="submit">确认</button>
            <button class="cancel" type="button" onclick="cancel()">取消</button>
        </form>
    </div>
</div>
</body>
</html>
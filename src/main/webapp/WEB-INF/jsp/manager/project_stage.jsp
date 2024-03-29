<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/manager/project_stage.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manager/project_stage.js"></script>
</head>
<body>
<div class="top">
    <div class="left">
        <button class="new_requirement" onclick="addDemand()">创建阶段</button>
        <button class="modify_requirement" onclick="modifyDemand()">修改阶段</button>
        <button class="delete_requirement" onclick="deleteDemand()">删除阶段</button>
    </div>


</div>
<table class="table" id="requirement_table">
    <thead>
    <tr>
        <th class="one">ID</th>
        <th class="two">内容</th>
        <th class="three">开始时间</th>
        <th class="four">结束时间</th>
        <th class="five">预计支出</th>
    </tr>
    </thead>
</table>

<div class="mask"></div>

<div class="dialog" id="create_requirement">
    <div class="dialog_title">添加阶段</div>
    <div>
        <form action="/operate/stage/add" method="post" id="add_stage">
            <label class="stage_name">阶段名：</label>
            <input type="text" name="stage_name"/>
            <label class="start_date">开始日期：</label>
            <input type="date" name="start_date"/>
            <label class="end_date">结束日期：</label>
            <input type="date" name="end_date"/>
            <label class="predict_cost">预计支出：</label>
            <input type="text" name="predict_cost"/>
            <button class="confirm" >确认</button>
            <button class="cancel" type="button" onclick="cancel()">取消</button>
        </form>
    </div>
</div>

<div class="dialog" id="modify_requirement">
    <div class="dialog_title">修改阶段</div>
    <div>
        <form action="/operate/stage/update" method="post" id="update_stage">
            <div class="query_demand">
                <input type="text" id="stage_id" class="demand_id" name="stageId" placeholder="阶段ID"/>
                <button type="button" class="query" onclick="queryStage()">查询</button>
            </div>
            <label class="stage_name">阶段名：</label>
            <input type="text" name="stage_name" id="id_stage_name"/>
            <label class="start_date">开始日期：</label>
            <input type="date" name="start_date" id="id_start_time"/>
            <label class="end_date">结束日期：</label>
            <input type="date" name="end_date" id="id_end_time"/>
            <label class="predict_cost">预计支出：</label>
            <input type="text" name="predict_cost" id="id_predict_cost"/>
            <button class="confirm" >确认</button>
            <button class="cancel" type="button" onclick="cancel()">取消</button>
        </form>
    </div>
</div>

<div class="dialog" id="update_requirement">
    <div class="dialog_title">删除阶段</div>
    <div class="dialog_content">
        <form action="/operate/stage/delete" method="post" id="delete_stage">
            <input type="text" class="demand_id" name="stageId"  placeholder="阶段ID"/>

            <button class="confirm">确认</button>
            <button class="cancel" type="button" onclick="cancel()">取消</button>
        </form>
    </div>
</div>
</body>
</html>

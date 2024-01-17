
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>软件工程项目成本控制管理系统</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/buyer/project_information.css"/>
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/buyer/project_information.js"></script>
</head>
<body>
<table>
  <tr>
    <td colspan="3" id="project_name_table">
      <span id="project_name">项目名称：</span>
      <span id="project_name_value">${projectDto.getProjectName()}</span>
    </td>
  </tr>
  <tr id="project_main_information" style="height: 285px;">
    <td id="main_information" style="width: 30%;">
      <ul>
        <li>
          <span>购买方：</span>
          <span>${buyerDto.getUserName()}</span>
        </li>
        <li>
          <div>
            <span>项目创建时间：</span>
            <span>${projectDto.getCreateTime()}</span>
          </div>
        </li>
        <li>
          <div>
            <span>项目完成时间：</span>
            <span>${projectDto.getFinishTime()}</span>
          </div>
        </li>
      </ul>
    </td>
    <td style="position: relative;;popadding-bottom: 0; background-color: white;">
      <div id="schedule" style="width: 800px; height: 260px; margin-top: 15px; margin-left: 20px">

      </div>
      <script>
        var myChart1 = echarts.init(document.getElementById('schedule'));
        myChart1.showLoading();  // 开启 loading 效果
        $(document).ready(function () {
          $.get('/data/cost/nowZ', function (data) {

            myChart1.setOption({
              title: {
                text: '成本统计'
              },
              tooltip: {
                text: '单位：元'
              },
              legend: {
                data:['支出成本(元)']
              },
              xAxis: {
                data: data.stages
              },
              yAxis: {

              },
              series: [{
                name: '支出成本(元)',
                type: 'bar',
                data: data.expenses
              }]
            });
            myChart1.hideLoading();
          }, 'json');
        })

      </script>
    </td>
  </tr>
  <tr style="height: 286px;">
    <td id="project_manager_information" style="width: 30%;">
      <span id="project_manager">项目负责人</span>
      <ul>
        <li>
          <div>
            <span>姓名：</span>
            <span>${managerDto.getUserName()}</span>
          </div>
        </li>
        <li>
          <div>
            <span>手机号：</span>
            <span>${managerDto.getPhone()}</span>
          </div>
        </li>
        <li>
          <div>
            <span>邮箱：</span>
            <span>${managerDto.getEmail()}</span>
          </div>
        </li>
      </ul>
    </td>
    <td style="position: relative;;popadding-bottom: 0; background-color: white;">
      <div id="main" style="width: 800px; height: 260px; margin-top: 15px; margin-left: 20px"></div>
      <script>
        var myChart = echarts.init(document.getElementById('main'));
        myChart.showLoading();  // 开启 loading 效果
        $(document).ready(function () {
          $.get('/data/task/count', function (data) {

            myChart.setOption({
              title: {
                text: '进度完成情况',
              },
              tooltip: {
                trigger: 'axis',
              },
              legend: {
                data: ['已完成任务数量', '未完成任务数量']
              },
              xAxis: {
                data: data.stages
              },
              yAxis: {
              },
              series: [
                {
                  name: '已完成任务数量',
                  data: data.finishTaskNum,
                  type: 'bar',
                  stack: 'x'
                },
                {
                  name: '未完成任务数量',
                  data: data.noFinishTaskNum,
                  type: 'bar',
                  stack: 'x'
                }
              ]
            });
            myChart.hideLoading();  // 隐藏 loading 效果
          }, 'json');
        })

      </script>
    </td>
  </tr>
</table>
</body>
</html>

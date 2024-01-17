<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>软件工程项目成本控制管理系统</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/manager/project_information.css"/>
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/manager/project_information.js"></script>
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
                            <span>项目负责人：</span>
                            <span>${managerDto.getUserName()}</span>
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
                    <%-- 柱状图：当前项目已支出的成本 --%>
                    <div id="chart_cost" style="width: 800px; height: 260px; margin-top: 15px; margin-left: 20px"></div>
                    <script>
                        // 初始化图表
                        const chart_cost = echarts.init(document.getElementById('chart_cost'));
                        // 开启 loading 效果
                        chart_cost.showLoading();
                        // 在页面加载完成后，执行的操作
                        $(document).ready(function () {
                            // 异步获取数据：对图表中的数据进行异步获取及加载到图表中
                            $.get('/data/cost/nowZ', function (stageExpense) {
                                // 将数据加载到图表中
                                chart_cost.setOption({
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
                                        data: stageExpense.stages
                                    },
                                    yAxis: {

                                    },
                                    series: [{
                                        name: '支出成本(元)',
                                        type: 'bar',
                                        data: stageExpense.expenses
                                    }]
                                });
                                chart_cost.hideLoading();
                            }, 'json');
                        });
                    </script>
                </td>
            </tr>
            <tr style="height: 286px;">
                <td id="project_manager_information" style="width: 30%;">
                    <span id="project_manager">甲方</span>
                    <ul>
                        <li>
                            <div>
                                <span>姓名：</span>
                                <span>${buyerDto.getUserName()}</span>
                            </div>
                        </li>
                        <li>
                            <div>
                                <span>手机号：</span>
                                <span>${buyerDto.getPhone()}</span>
                            </div>
                        </li>
                        <li>
                            <div>
                                <span>邮箱：</span>
                                <span>${buyerDto.getEmail()}</span>
                            </div>
                        </li>
                    </ul>
                </td>
                <td style="position: relative;;popadding-bottom: 0; background-color: white;">
                    <!-- 柱状图：当前项目的每个阶段已完成和未完成的任务数量 -->
                    <div id="chart_task_num" style="width: 800px; height: 260px; margin-top: 15px;
                        margin-left: 20px"></div>
                    <script>
                        // 初始化图表
                        const chart_task_num = echarts.init(document.getElementById('chart_task_num'));
                        // 开启 loading 效果
                        chart_task_num.showLoading();
                        $(document).ready(function () {
                            $.get('/data/task/count', function (stageTaskNum) {
                                chart_task_num.setOption({
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
                                        data: stageTaskNum.stages
                                    },
                                    yAxis: {
                                    },
                                    series: [
                                        {
                                        name: '已完成任务数量',
                                        data: stageTaskNum.finishTaskNum,
                                        type: 'bar',
                                        stack: 'x'
                                        },
                                        {
                                        name: '未完成任务数量',
                                        data: stageTaskNum.noFinishTaskNum,
                                        type: 'bar',
                                        stack: 'x'
                                        }
                                    ]
                                });
                                // 隐藏 loading 效果
                                chart_task_num.hideLoading();
                            }, 'json');
                        });
                    </script>
                </td>
            </tr>
        </table>
    </body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>软件工程项目成本控制管理系统</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/common/project_summary.css"/>
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/project_summary.js"></script>
    </head>
    <body>
        <table>
            <tr>
                <td>
                    项目名：<span id="project_name">${project_name}</span>
                </td>
                <td>
                    进度：<span id="project_schedule">${project_schedule}%</span>
                </td>
                <td>
                    已支出成本：<span id="project_cost">${project_cost}元</span>
                </td>
                <td id="last">
                    预计成本：<span id="project_pre_cost">${project_predict_cost}元</span>
                </td>
            </tr>
        </table>
        <%-- 饼状图：每个阶段预计成本 --%>
        <div id="predict" class="pie_chart"></div>
        <script>
            // 初始化echarts实例
            const chart1 = echarts.init(document.getElementById('predict'));
            chart1.showLoading();
            $(document).ready(function () {
                $.get("/data/cost/predict", function (data) {
                    // 指定图表的配置项和数据
                    const option = {
                        title: {
                            text: '预计成本'
                        },
                        series: [
                            {
                                name: '成本统计',
                                type: 'pie',    // 设置图表类型为饼图
                                radius: '45%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                                avoidLabelOverlap: true,
                                label: {
                                    show: true,
                                    // formatter: '{b}: {c} ({d}%)'
                                    formatter: '{b} ({d}%)'
                                },
                                emphasis: {
                                    label: {
                                        show: true,
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                },
                                labelLine: {
                                    show: true
                                },
                                data: data
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    chart1.setOption(option);
                    chart1.hideLoading();
                }, 'json');
            })
        </script>
    <div id="cost" class="pie_chart"></div>
    <script>
        // 基于准备好的dom，初始化echarts实例
        const myChart1 = echarts.init(document.getElementById('cost'));
        myChart1.showLoading();
        $(document).ready(function () {
            $.get("/data/cost/now", function (data) {
                // 指定图表的配置项和数据
                const option = {
                    title: {
                        text: '已支出成本'
                    },
                    series: [
                        {
                            name: '成本统计',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '45%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            avoidLabelOverlap: true,
                            label: {
                                show: true,
                                // formatter: '{b}: {c} ({d}%)'
                                formatter: '{b} ({d}%)'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: true
                            },
                            data: data
                        }
                    ]
                };
                myChart1.hideLoading();
                // 使用刚指定的配置项和数据显示图表。
                myChart1.setOption(option);
            }, 'json');

        });

    </script>
    <div id="cost_analysis" class="column_chart"></div>
        <script>
            const myChart2 = echarts.init(document.getElementById('cost_analysis'));
            myChart2.showLoading();  // 开启 loading 效果

            $(document).ready(function (data) {
                $.get("/data/cost/saveZ", function (data) {
                    myChart2.setOption({

                        title: {
                            text: '成本分析(负数-超出，正数-节约)'
                        },
                        tooltip: {
                            text: '单位：元'
                        },
                        legend: {
                            data: ['超出/节约成本(元)']
                        },
                        xAxis: {
                            data: data.stages
                        },
                        yAxis: {},
                        series: [{
                            name: '超出/节约成本(元)',
                            type: 'bar',
                            data: data.expenses
                        }]
                    });
                    myChart2.hideLoading();
                }, 'json');
            });
        </script>
    </body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: pengxiong
  Date: 2023/12/28
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="predict" class="pie_chart"></div>
    <script>
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('predict'));
        // myChart.showLoding();
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '预计成本'
            },
            series : [
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
                    data: ${project_predict_cost_information}
                    //     [          // 数据数组，name 为数据项名称，value 为数据项值
                    //     {value:1000, name:'计划'},
                    //     {value:5000, name:'需求分析'},
                    //     {value:6000, name:'总体设计'},
                    //     {value:8000, name:'详细设计'},
                    //     {value:60000, name:'程序编码'},
                    //     {value:3000, name:'软件测试'},
                    //     {value:2000, name:'运行维护'}
                    // ]
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        // myChart.hideLoading();
    </script>
</body>
</html>

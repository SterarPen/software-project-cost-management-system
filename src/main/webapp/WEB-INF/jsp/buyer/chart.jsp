<%--
  Created by IntelliJ IDEA.
  User: pengxiong
  Date: 2023/12/3
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>图表</title>
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
  <!-- <script src="js/chart.js"></script> -->
</head>
<body>
<div id="main" style="width: 100%; height: 680px; margin: 0 auto;"></div>
<script>
  function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
      arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
      while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
  }

  var chart_type = getParam('chartype');
  var param1 = getParam('param1');
  var param2 = getParam('param2');



  if(chart_type == 'pie' && (param1 != null && param1 !='') && (param2 == null || param2 == '')) {
    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();
    $.get('json/' + getParam('datau') + '.json', function (data) {
      myChart.setOption( {
        title: {
          text: '已支出成本'
        },
        series : [
          {
            name: '成本统计',
            type: 'pie',    // 设置图表类型为饼图
            radius: '45%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
            avoidLabelOverlap: true,
            label: {
              show: true,
              formatter: '{b}: {c} ({d}%)'
              // formatter: '{b} ({d}%)'
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
            data:[          // 数据数组，name 为数据项名称，value 为数据项值
              {value:1000, name:'计划'},
              {value:5000, name:'需求分析'},
              {value:6000, name:'总体设计'},
              {value:8000, name:'详细设计'},
              {value:60000, name:'程序编码'},
              {value:3000, name:'软件测试'},
              {value:2000, name:'运行维护'}
            ]
          }
        ]
      });

      myChart.hideLoading();
    }, 'json');
  } else if(chart_type == 'bar' && (param1 != null && param1 !='') && (param2 == null || param2 == '')) {
    var myChart1 = echarts.init(document.getElementById('main'));
    myChart1.showLoading();  // 开启 loading 效果
    $.get('json/' + getParam('datau') + '.json', function (data) {

      myChart1.setOption({
        title: {
          text: '成本分析'
        },
        tooltip: {
          text: '单位：元'
        },
        legend: {
          data:['超出/节约成本(元)']
        },
        xAxis: {
          data: ["计划","需求分析","总体设计","详细设计","程序编码","软件测试","运行维护"]
        },
        yAxis: {

        },
        series: [{
          name: '超出/节约成本(元)',
          type: 'bar',
          data: data.expense
        }]
      });
      myChart1.hideLoading();
    }, 'json');
  } else if(chart_type == 'bar' && (param1 != null && param1 !='') && (param2 != null && param2 != '')) {
    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();  // 开启 loading 效果
    $.get('json/' + getParam('datau') + '.json', function (data) {

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
          data: ['计划','需求分析','总体设计','详细设计','程序编码','软件测试','运行维护']
        },
        yAxis: {
        },
        series: [
          {
            name: '已完成任务数量',
            data: data[param1],
            type: chart_type,
            stack: 'x'
          },
          {
            name: '未完成任务数量',
            data: data[param2],
            type: chart_type,
            stack: 'x'
          }
        ]
      });
      myChart.hideLoading();  // 隐藏 loading 效果
    }, 'json');
  }
</script>
</body>
</html>

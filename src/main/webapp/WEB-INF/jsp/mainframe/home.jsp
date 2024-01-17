
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>软件工程项目成本控制管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainframe/home.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/mainframe/home.js"></script>
    <script>// 加载后直接显示项目汇总
        window.onload = function() {
            $('.first a').click();
            let a = $('.first a').attr("href");
            $('iframe').prop('src', a);
        };

    </script>
</head>
<body>
<div class="bar">
    <div class="loge">
        <img src="/img/loge.png"/>
    </div>
    <div class="bar_items">
        <ul class="bar_ul">
            <c:choose>
                <c:when test="${userDto.getRole() == 2}">
                    <li class="first">

                        <a href="${pageContext.request.contextPath}/page/common/project_summary"
                           target="content_part"><img src="img/wallet.png"/>项目汇总</a>

                        <div class="selector_minitor"></div>
                    </li>
                    <li class="second">

                        <a href="${pageContext.request.contextPath}/page/manager/project_information"
                           target="content_part"><img src="img/home.png"/>项目信息</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="third">

                        <a href="${pageContext.request.contextPath}/page/common/project_requirement"
                           target="content_part"><img src="img/plate.png"/>项目需求</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="third">

                        <a href="${pageContext.request.contextPath}/page/manager/project_stage"
                           target="content_part"><img src="img/bet.png"/>阶段管理</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="third">

                        <a href="${pageContext.request.contextPath}/page/manager/project_task"
                           target="content_part"><img src="img/Form.png"/>任务管理</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="fifth">
                        <a href="${pageContext.request.contextPath}/page/common/user_information"
                           target="content_part"><img src="img/users.png"/>账号信息</a>
                        <div class="selector_minitor"></div>
                    </li>
                </c:when>
                <c:when test="${userDto.getRole() == 1}">
                    <li class="first">
                        <a href="${pageContext.request.contextPath}/page/common/project_summary"
                           target="content_part"><img src="img/wallet.png"/>项目分析</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="second">
                        <a href="${pageContext.request.contextPath}/page/buyer/project_information"
                           target="content_part"><img src="img/home.png"/>项目信息</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="third">
                        <a href="${pageContext.request.contextPath}/page/common/project_requirement"
                           target="content_part"><img src="img/plate.png"/>项目需求</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="fifth">
                        <a href="${pageContext.request.contextPath}/page/common/user_information"
                           target="content_part"><img src="img/users.png"/>账号信息</a>
                        <div class="selector_minitor"></div>
                    </li>
                </c:when>
                <c:when test="${userDto.getRole() == 0}">
                    <li class="first">
                        <a href="${pageContext.request.contextPath}/page/admin/user_management"
                           target="content_part"><img src="img/users.png"/>用户管理</a>
                        <div class="selector_minitor"></div>
                    </li>
                    <li class="second">
                        <a href="${pageContext.request.contextPath}/page/admin/project_management"
                           target="content_part"><img src="img/wallet.png"/>项目管理</a>
                        <div class="selector_minitor"></div>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </div>
</div>
<div class="right">
    <div class="top">
        <span id="title">项目汇总</span>
        <div class="top_right">
<%--            <img id="information" src="${pageContext.request.contextPath}/img/Alert1.png"/>--%>
            <a href="/login_out" target="_top"><img id="login_out"src="${pageContext.request.contextPath}/img/Info1.png"/></a>
            <span id="user_name">${userDto.getUserName()}</span>
        </div>
    </div>
    <iframe name="content_part" class="content"></iframe>
</div>

</body>
</html>
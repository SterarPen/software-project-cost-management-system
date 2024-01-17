<%@ page import="com.starer.util.CookieUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/common/project_requirement.css"/>
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/project_requirement.js"></script>
    </head>
    <body>
        <div class="top">
            <div class="left">
                <c:choose>
                    <c:when test="${userDto.getRole() == 1}">
                        <button class="new_requirement" onclick="addDemand()">创建需求</button>
                        <button class="modify_requirement" onclick="modifyDemand()">修改需求</button>
                        <button class="delete_requirement" onclick="deleteDemand()">修改需求状态</button>
                    </c:when>
                    <c:otherwise>
                        <button class="modify_requirement" onclick="modifyDemand()">修改需求</button>
                        <button class="update_requirement" onclick="updateDemand()">更改需求状态</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="right">
                <form id="query_condition" action="/data/demand/condition" method="post">
                    <input type="text" name="demandId" placeholder="请输入查询内容"/>
                    <select name="day">
                        <option value="">全部</option>
                        <option value="1">今天</option>
                        <option value="3">近三天</option>
                        <option value="5">近五天</option>
                        <option value="7">近一周</option>
                        <option value="14">近两周</option>
                        <option value="30">近一个月</option>
                        <option value="90">近三个月</option>
                        <option value="180">半年内</option>
                        <option value="360">一年内</option>
                        <option value="1000">一年以外</option>
                    </select>
                    <select name="status">
                        <option value="">全部</option>
                        <option value="-1">
                            已删除
                        </option>
                        <option value="0">
                            正处理
                        </option>
                        <option value="1">
                            已通过
                        </option>
                        <option value="2">
                            未通过
                        </option>
                        <option value="3">
                            待删除
                        </option>
                    </select>
                    <select name="type">
                        <option value="">全部</option>
                        <option value="1">文本</option>
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
            <th class="four">状态</th>
            <th class="five">类型</th>
            <th class="six">操作</th>
        </tr>
        </thead>
    </table>

    <div class="mask"></div>

    <div class="dialog" id="create_requirement">
        <div class="dialog_title">添加需求</div>
        <div>
            <form action="/operate/demand/add" method="post">
                <select id="req_type">
                    <option value="text">文本</option>
                </select>
                <textarea id="text_req" name="demandContent"></textarea>
                <button type="submit" class="confirm" >确认</button>
                <button type="button" class="cancel" onclick="cancel()">取消</button>
            </form>
        </div>
    </div>

    <div class="dialog" id="modify_requirement">
        <div class="dialog_title">修改需求内容</div>
        <div>
            <form action="/operate/demand/modify" method="POST">
                <div class="query_demand">
                    <input type="text" class="demand_id" id="demand_id1" name="demandId" placeholder="需求ID"/>
                    <button type="button" class="query" onclick="getDemandContent()">查询</button>
                </div>

                <textarea class="text_req" id="demand_content" name="demandContent"></textarea>
                <button type="submit" class="confirm">确认</button>
                <button class="cancel" type="button" onclick="cancel()">取消</button>
            </form>
        </div>
    </div>

    <div class="dialog" id="update_requirement">
        <div class="dialog_title">更改需求状态</div>
        <div class="dialog_content">
            <form action="/operate/demand/update_status" method="POST">
                <div class="query_demand">
                    <input type="text" class="demand_id" id="demand_id2" name="demandId"  placeholder="需求ID"/>
                    <button type="button" class="query" id="query" onclick="getValidDemandStatus()">查询</button>
                </div>

<%--                <select class="demand_status">--%>
<%--                    <option id="handling" value="0">正处理</option>--%>
<%--                    <option id="agree" value="1">已通过</option>--%>
<%--                    <option id="not_agree" value="2">未通过</option>--%>
<%--                    <option id="ready_delete" value="3">待删除</option>--%>
<%--                    <option id="delete" value="-1">已删除</option>--%>
<%--                </select>--%>

                <div id="radio_status">

                </div>
<%--                <input type="radio" name="demandStatus" value="0"/>--%>
<%--                <label class="demandStatusName">正处理</label>--%>
<%--                <input type="radio" name="demandStatus" value="0"/>--%>
<%--                <label class="demandStatusName">已通过</label>--%>
<%--                <input type="radio" name="demandStatus" value="0"/>--%>
<%--                <label class="demandStatusName">未通过</label>--%>
<%--                <input type="radio" name="demandStatus" value="0"/>--%>
<%--                <label class="demandStatusName">待删除</label>--%>
<%--                <input type="radio" name="demandStatus" value="0"/>--%>
<%--                <label class="demandStatusName">已删除</label>--%>

                <button class="confirm" type="submit">确认</button>
                <button class="cancel" type="button" onclick="cancel()">取消</button>
            </form>
        </div>
    </div>
    </body>
</html>
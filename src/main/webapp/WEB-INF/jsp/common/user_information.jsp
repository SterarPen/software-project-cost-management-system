<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>软件工程成本控制管理系统</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/common/user_information.css"/>
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/common/user_information.js"></script>
    </head>
    <body>
        <div id="background_information">
            <table id="information_table">
                <tr class="information_show">
                    <td colspan="3">
                        <div>
                            <span class="information_type">账号ID：</span>
                            <input type="text" value="${userDto.getUserId()}" disabled/>
                        </div>
                    </td>
                </tr>
                <tr class="information_show">
                    <td colspan="3">
                        <div>
                            <span class="information_type">用户名：</span>
                            <input type="text" value="${userDto.getUserName()}" disabled/>
                        </div>
                    </td>
                </tr>
                <tr class="information_show">
                    <td colspan="3">
                        <div>
                            <span class="information_type">手机号：</span>
                            <input type="text" value="${userDto.getPhone()}" disabled/>
                        </div>
                    </td>
                </tr>
                <tr class="information_show">
                    <td colspan="3">
                        <div>
                            <span class="information_type">邮箱：</span>
                            <input type="text" value="${userDto.getEmail()}" disabled/>
                        </div>
                    </td>
                </tr>
                <tr class="information_show">
                    <td colspan="3">
                        <div>
                            <span class="information_type">注册时间：</span>
                            <input type="text" value="${userDto.getCreateTime()}" disabled/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button id="update_password">重置密码</button>
                    </td>
                    <td>
                        <button id="update_phone">更新手机号</button>
                    </td>
                    <td>
                        <button id="update_email">修改邮箱</button>
                    </td>
                </tr>
            </table>
        </div>

        <div id="mask"></div>

        <div class="dialog" id="dialog_update_password">
<%--            <form action="/buyer/update_password/${buyer.getId()}" method="post">--%>
                <div class="title">重置密码</div>
                <div>
                    <form action="/update_password" method="post">
                        <button class="get" onclick="sendEmail()" type="button">获取邮箱验证码</button>
                        <input type="text" name="code"/>
                        <input type="text" placeholder="新密码" name="new_password"/>
                        <input type="submit" id="pwd_update" value="确认" class="confirm"/>
                        <button type="button" class="cancel">取消</button>
                    </form>
               </div>
<%--            </form>--%>

        </div>
        <div class="dialog" id="dialog_update_phone">
            <div class="title">更新手机号</div>
            <div>
                <form action="/update_phone" method="post">
                    <button type="button" class="get" onclick="sendPhoneMessage()">获取手机验证码</button>
                    <input type="text" name="code"/>
                    <input type="text" placeholder="新手机号" name="new_phone"/>
                    <button class="confirm" type="submit">确认</button>
                    <button type="button" class="cancel">取消</button>
                </form>

            </div>
        </div>
        <div class="dialog" id="dialog_update_email">
            <div class="title">修改邮箱</div>
            <div>
                <form action="/update_email" method="post">
                    <button type="button" class="get" onclick="sendEmail()" >获取邮箱验证码</button>
                    <input type="text" name="code"/>
                    <input type="text" placeholder="新邮箱" name="new_email"/>
                    <button class="confirm">确认</button>
                    <button type="button" class="cancel">取消</button>
                </form>

            </div>
        </div>
    </body>
</html>


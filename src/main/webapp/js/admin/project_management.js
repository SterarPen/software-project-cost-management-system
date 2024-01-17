$(document).ready(function() {
    /***
     * 项目管理页面获取项目信息并显示的相关JS
     */
    $.getJSON("/admin/all_project_information", function(data) {
        $.each(data, function(key, val) {
            const newRow = $('<tr>');
            newRow.append($('<td>').text(val.projectId));
            newRow.append($('<td>').text(val.projectName));
            newRow.append($('<td>').text(val.createTime));
            newRow.append($('<td>').text(val.finishTime));
            newRow.append($('<td>').text(val.buyerId));
            newRow.append($('<td>').text(val.developerManagerId));
            $("#project_information_table").append(newRow);
        });
    });

    /***
     * 添加项目相关JS
     */
    $("#add_project_button").click(function() {
        $("#mask").show();
        $(".dialog").slideUp(0);
        $("#add_project_dialog").slideDown(2000);
    });
    $.getJSON("/admin/data/all_buyer", function (data) {
        $.each(data, function (key, val) {
            const newLi = $('<option>');
            newLi.text(val.userId + "<" + val.userName +　">");
            newLi.attr("value", val.userId);
            $("#buyer").append(newLi);
        })
    });
    $.getJSON("/admin/data/all_developer_manager", function (data) {
        $.each(data, function (key, val) {
            const newLi = $('<option>');
            newLi.text(val.userId + "<" + val.userName + ">");
            newLi.attr("value", val.userId);
            $("#developer_manager").append(newLi);
        })
    });

    /***
     * 删除项目相关JS
     */
    $("#delete_project_button").click(function () {
        $("#mask").show();
        $(".dialog").slideUp(0);
        $("#delete_project_dialog").slideDown(2000);
    })

    /***
     * 共有的JS
     */
    $(".cancel").click(function() {
        $(".dialog").slideUp(2000);
        $("#mask").slideUp();
    });
});

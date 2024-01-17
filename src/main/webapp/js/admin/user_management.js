$(document).ready(function() {

    /***
     * 用户管理页面获取项目信息并显示的相关JS
     */
    $.getJSON("/admin/all_user_information", function(data) {
        $.each(data, function(key, val) {
            const newRow = $('<tr>');
            newRow.append($('<td>').text(val.userId));
            newRow.append($('<td>').text(val.userName));
            newRow.append($('<td>').text(val.email));
            newRow.append($('<td>').text(val.phone));
            newRow.append($('<td>').text(val.createTime));
            switch(val.role) {
                case 1:
                    newRow.append($('<td>').text('购买方'));
                    break;
                case 2:
                    newRow.append($('<td>').text('项目经理'));
                    break;
            }
            newRow.append($('<td>').text(val.handlingProject))
            $("#user_information_table").append(newRow);
        });
    });

    /***
     * 创建用户相关JS
     */
    $("#add_user_button").click(function() {
        $("#mask").show();
        $(".dialog").slideUp(0);
        $("#add_user_dialog").slideDown(2000);
    });

    /***
     * 删除用户相关JS
     */
    $("#delete_user_button").click(function () {
        $("#mask").show();
        $(".dialog").slideUp(0);
        $("#delete_user_dialog").slideDown(2000);
    })



    /***
     *
     */
    $(".cancel").click(function() {
        $(".dialog").slideUp(2000);
        $("#mask").slideUp();
    });

});

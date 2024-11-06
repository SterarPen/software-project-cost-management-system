$(document).ready(function() {

    // $("#req_type").change(function() {
    //     var myselect = document.getElementById("req_type");
    //     console.log(myselect.selectedIndex);
    //     if(myselect.selectedIndex == 1) {
    //         $("#select_file").hide();
    //         $("#text_req").show();
    //         $("#text_req").text(localStorage.getItem("text_task"));
    //     } else {
    //         $("textarea").hide();
    //         $("#select_file").show();
    //     }
    // });
    $("#text_req").blur(function() {
        localStorage.setItem("text_task", document.getElementById("text_task").value);
    });

    // 进行条件查询所执行的JS操作
    const elementById = document.getElementById("query_condition");
    elementById.addEventListener('submit', function (event) {
        event.preventDefault(); // 阻止表单的默认提交行为
        // 执行 AJAX 请求
        var xhr = new XMLHttpRequest();

        xhr.open(elementById.method, elementById.action, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.responseType = 'json';
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 响应已接收，执行相应的操作
                const response = xhr.response;
                if(response['status'] != 300) {
                    alert(response['msg']);
                    return;
                }
                let date;
                let year;
                let month;
                let day;
                const tbody = document.createElement('tbody');
                response['data'].forEach(item => {
                    const row = tbody.insertRow();
                    let cell = row.insertCell();
                    cell.className = "one";
                    cell.textContent = item['taskId'];
                    cell = row.insertCell();
                    cell.className = "two";
                    cell.textContent = item['taskContent'];

                    date = new Date(item['startTime']);
                    year = date.getFullYear();
                    month = date.getMonth() + 1; // 月份是从0开始的，需要加1
                    day = date.getDate();

                    cell = row.insertCell();
                    cell.className = "three";
                    cell.textContent = year + '-' + month + '-' + day;

                    date = new Date(item['endTime']);
                    year = date.getFullYear();
                    month = date.getMonth() + 1; // 月份是从0开始的，需要加1
                    day = date.getDate();

                    cell = row.insertCell();
                    cell.className = "three";
                    cell.textContent = year + '-' + month + '-' + day;

                    let taskStatus;
                    switch (item['status']) {
                        case 0:
                            demandStatus = '未完成';
                            break;
                        case 1:
                            demandStatus = '已完成';
                            break;
                    }
                    cell = row.insertCell();
                    cell.className = "four";
                    cell.textContent = demandStatus;
                    cell = row.insertCell();
                    cell.className = "five";
                    cell.textContent = item['cost'];
                    cell = row.insertCell();
                    cell.className = "six";
                    cell.textContent = item['stageName'];
                });
                const table = document.getElementById('requirement_table');
                const tbodys = table.getElementsByTagName('tbody'); // 获取表格中所有的tbody元素
                for (let i = 0; i < tbodys.length; i++) {
                    table.removeChild(tbodys[i]); // 移除每个tbody元素
                }
                table.appendChild(tbody);
            }
        };
        const formData = new FormData(elementById);
        let sendData = '';
        for (let key of formData.keys()) {
            sendData += key + '=' + formData.get(key) + '&';
        }
        xhr.send(sendData);

    });


    $.getJSON("/data/task/all", function(data) {
        let date;
        let year;
        let month;
        let day;
        const tbody = document.createElement('tbody');
        if(data['status'] != 300) {
            alert(data['msg']);
            return;
        }
        data['data'].forEach(item => {
            const row = tbody.insertRow();
            let cell = row.insertCell();
            cell.className = "one";
            cell.textContent = item['taskId'];
            cell = row.insertCell();
            cell.className = "two";
            cell.textContent = item['taskContent'];

            date = new Date(item['startTime']);
            year = date.getFullYear();
            month = date.getMonth() + 1; // 月份是从0开始的，需要加1
            day = date.getDate();

            cell = row.insertCell();
            cell.className = "three";
            cell.textContent = year + '-' + month + '-' + day;

            date = new Date(item['endTime']);
            year = date.getFullYear();
            month = date.getMonth() + 1; // 月份是从0开始的，需要加1
            day = date.getDate();

            cell = row.insertCell();
            cell.className = "three";
            cell.textContent = year + '-' + month + '-' + day;

            let taskStatus;
            switch (item['status']) {
                case 0:
                    demandStatus = '未完成';
                    break;
                case 1:
                    demandStatus = '已完成';
                    break;
            }
            cell = row.insertCell();
            cell.className = "four";
            cell.textContent = demandStatus;
            cell = row.insertCell();
            cell.className = "five";
            cell.textContent = item['cost'];
            cell = row.insertCell();
            cell.className = "six";
            cell.textContent = item['stageName'];
        });
        const table = document.getElementById('requirement_table');
        table.appendChild(tbody);
    });

        $.getJSON("/data/stage/all", function (data) {
            if(data['status'] != 300) {
                alert(data['msg']);
                return;
            }

            let selectElement = document.getElementById('id_stage');
            while (selectElement.options.length > 0) {
                selectElement.remove(0);
            }

            const newOption = document.createElement("option");
            newOption.text = '全部阶段';
            newOption.value = '';
            selectElement.add(newOption);


            let selectElement1 = document.getElementById('id_add_stage_name');
            while (selectElement1.options.length > 0) {
                selectElement1.remove(0);
            }

            data['data'].forEach(item => {
                const newOption = document.createElement("option");
                newOption.text = item['stageName'];
                newOption.value = item['stageId'];
                selectElement.add(newOption);

                const newOption1 = document.createElement("option");
                newOption1.text = item['stageName'];
                newOption1.value = item['stageId'];
                selectElement1.add(newOption1);
            });
        }, 'json');

    // $.getJSON("/data/stage/all", function (data) {
    //     let selectElement = document.getElementById('id_add_stage_name');
    //     while (selectElement.options.length > 0) {
    //         selectElement.remove(0);
    //     }
    //     data['data'].forEach(item => {
    //         const newOption = document.createElement("option");
    //         newOption.text = item['stageName'];
    //         newOption.value = item['stageId'];
    //         selectElement.add(newOption);
    //     });
    // }, 'json');

    const forms = document.querySelectorAll('.dialog form');
    for (let i = 0; i < forms.length; i++) {
        forms[i].addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止表单的默认提交行为

            // 执行 AJAX 请求
            submitForm(this);
        });
    }

    function submitForm(formElement) {
        var xhr = new XMLHttpRequest();

        xhr.open(formElement.method, formElement.action, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 响应已接收，执行相应的操作
                handleResponse(xhr.responseText);
            }
        };
        const formData = new FormData(formElement);
        let sendData = '';
        for (let key of formData.keys()) {
            sendData += key + '=' + formData.get(key) + '&';
        }
        xhr.send(sendData);

    }

    function handleResponse(response) {
        // 处理从服务器返回的响应数据
        alert(response);
        // 执行其他操作...
        location.reload();
    }

});


//------------------------
// 点击按钮，唤起弹窗时的响应事件
//------------------------

// 点击添加需求按钮的响应事件
function addDemand1() {
    $('.top,.table').css('z-index', '0');
    $(".dialog").css('visibility', 'hidden');
    $(".mask").css('visibility', 'visible');
    $("#create_requirement").css('visibility', 'visible');
}
function addDemand() {
    $(".dialog").hide();
    $(".mask").show();
    $("#create_requirement").slideDown(1000);
    document.getElementById("text_task").value = localStorage.getItem("text_task");
}
// 点击修改需求按钮的响应事件
function modifyDemand1() {
    $('.top,.table').css('z-index', '0');
    $(".dialog").css('visibility', 'hidden');
    $(".mask").css('visibility', 'visible');
    $("#modify_requirement").css('visibility', 'visible');
}
function modifyDemand() {
    $(".dialog").hide();
    $(".mask").show();
    $("#modify_requirement").slideDown(1000);
}
// 点击删除需求按钮的响应事件
function deleteDemand1() {
    $('.top,.table').css('z-index', '0');
    $(".dialog").css('visibility', 'hidden');
    $(".mask").css('visibility', 'visible');
    $("#update_requirement").css('visibility', 'visible');
}
function deleteDemand() {
    $(".dialog").hide();
    $(".mask").show();
    $("#update_requirement").slideDown(1000);
}
// 点击更改状态按钮的响应事件
function updateDemand1() {
    $('.top,.table').css('z-index', '0');
    $(".dialog").css('visibility', 'hidden');
    $(".mask").css('visibility', 'visible');
    $("#update_requirement").css('visibility', 'visible');
}
function updateDemand() {
    $(".dialog").hide();
    $(".mask").show();
    $("#update_requirement").slideDown(1000);
}

//--------------------------
// 弹窗内按钮点击发生的响应事件
//--------------------------

// 弹窗内取消按钮的响应事件
function cancel1() {
    $(".dialog").css('visibility', 'hidden');
    $(".mask").css('visibility', 'hidden');
    $('.top,.table').css('z-index', '3');
}

function cancel() {
    $('.dialog').slideUp(1000);
    $('.mask').hide();
}

function getTask() {
    const elementById = document.getElementById('demand_id1');
    if(elementById.value == null || elementById.value.length === 0) {
        alert('任务ID不能为空');
        return;
    }
    fetch('/data/task/get?taskId=' + elementById.value)
        .then(response => response.json())
        .then(data => {
            // 在这里处理从服务器返回的数据
            if (data.status == 400) {
                alert(data.msg);
            }
            const id_update_task_content = document.getElementById('id_update_task_content');
            id_update_task_content.value = data.data.taskContent;
            const id_update_start_time = document.getElementById('id_update_start_time');
            id_update_start_time.value = data.data.startTime;
            const id_update_end_time = document.getElementById('id_update_end_time');
            id_update_end_time.value = data.data.endTime;
            const id_update_cost = document.getElementById('id_update_cost');
            id_update_cost.value = data.data.cost;
            const id_update_stage_name = document.getElementById('id_update_stage_name');
            id_update_stage_name.value = data.data.demandContent;

        })
        .catch(error => {
            // 处理请求错误
            console.error('发生错误:', error);
        });
}


function getValidDemandStatus() {

    const elementById1 = document.getElementById('demand_id2');
    if(elementById1.value == null || elementById1.value.length == 0) {
        alert('需求ID不能为空');
        return;
    }
    // $.ajax((), function (data)  {
    //
    // });
    fetch('/data/demand/get?demandId=' + elementById1.value)
        .then(response => response.json())
        .then(data => {
            // 在这里处理从服务器返回的数据
            if (data.status == 400) {
                alert(data.msg);
            }
            const radio_status = document.getElementById('radio_status');
            while (radio_status.firstChild) {
                radio_status.removeChild(radio_status.firstChild);
            }
            let role;
            $.get("/user_type",function(role_data,status){
                if (role_data == 1) {
                    switch (data.data.status) {
                        case -1:
                            addRadio(radio_status, '-1', '已删除', true);
                            break;
                        case 0:
                            addRadio(radio_status, '0', '正处理', true);
                            addRadio(radio_status, '3', '待删除', false);
                            break;
                        case 1:
                            addRadio(radio_status, '1', '已通过', true);
                            addRadio(radio_status, '3', '待删除', false);
                            break;
                        case 2:
                            addRadio(radio_status, '2', '未通过', true);
                            addRadio(radio_status, '3', '待删除', false);
                            break;
                        case 3:
                            addRadio(radio_status, '3', '待删除', true);
                            break;
                    }
                } else {
                    switch (data.data.status) {
                        case -1:
                            addRadio(radio_status, '-1', '已删除', true);
                            break;
                        case 0:
                            addRadio(radio_status, '0', '正处理', true);
                            addRadio(radio_status, '1', '已通过', false);
                            addRadio(radio_status, '2', '未通过', false);
                            break;
                        case 1:
                            addRadio(radio_status, '1', '已通过', true);
                            break;
                        case 2:
                            addRadio(radio_status, '2', '未通过', true);
                            break;
                        case 3:
                            addRadio(radio_status, '3', '待删除', true);
                            addRadio(radio_status, '-1', '已删除', false);
                            break;
                    }
                }
            });


        })
        .catch(error => {
            // 处理请求错误
            console.error('发生错误:', error);
        });
}

function getSessionCookieValue(cookieName) {
    const name = cookieName + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const cookieArray = decodedCookie.split(';');
    for (let i = 0; i < cookieArray.length; i++) {
        var cookie = cookieArray[i];
        while (cookie.charAt(0) == ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) == 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}

function addRadio(radio_status, status, statusName, checkOk) {
    const input1 = document.createElement('input');
    input1.type = 'radio';
    input1.name = 'demandStatus';
    input1.value = status;
    input1.checked = checkOk;
    input1.disabled = checkOk;
    const label1 = document.createElement('label');
    label1.className = 'demandStatusName';
    label1.textContent = statusName;
    radio_status.appendChild(input1);
    radio_status.appendChild(label1);
}

function addDelete(radio_status) {
    const input1 = document.createElement('input');
    input1.type = 'radio';
    input1.name = 'demandStatus';
    input1.value = '3';
    const label1 = document.createElement('label');
    label1.className = 'demandStatusName';
    label1.textContent = ''
    radio_status.appendChild(input1);
    radio_status.appendChild(label1);
}

function addHandling(radio_status) {
    const input1 = document.createElement('input');
    input1.type = 'radio';
    input1.name = 'demandStatus';
    input1.value = '0';
    const label1 = document.createElement('label');
    label1.className = 'demandStatusName';
    radio_status.appendChild(input1);
    radio_status.appendChild(label1);
}

function addAgree(radio_status) {
    const input1 = document.createElement('input');
    input1.type = 'radio';
    input1.name = 'demandStatus';
    input1.value = '0';
    const label1 = document.createElement('label');
    label1.className = 'demandStatusName';
    radio_status.appendChild(input1);
    radio_status.appendChild(label1);
}

function addNotAgree(radio_status) {
    const input1 = document.createElement('input');
    input1.type = 'radio';
    input1.name = 'demandStatus';
    input1.value = '0';
    const label1 = document.createElement('label');
    label1.className = 'demandStatusName';
    radio_status.appendChild(input1);
    radio_status.appendChild(label1);
}

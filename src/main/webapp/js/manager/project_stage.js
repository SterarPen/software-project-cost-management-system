$(document).ready(function() {

    $.getJSON("/data/stage/all", function(data1) {
        if(data1.status != 300) {
            alert(data1.msg);
        }
        let date;
        let year;
        let month;
        let day;
        const tbody = document.createElement('tbody');
        data1.data.forEach(item => {
            const row = tbody.insertRow();
            let cell = row.insertCell();
            cell.className = "one";
            cell.textContent = item['stageId'];
            cell = row.insertCell();
            cell.className = "two";
            cell.textContent = item['stageName'];

            date = new Date(item['startTime']);
            year = date.getFullYear();
            month = date.getMonth() + 1; // 月份是从0开始的，需要加1
            day = date.getDate();

            cell = row.insertCell();
            cell.className = "three";
            cell.textContent = year + '-' + month + '-' + day;

            date = new Date(item['costTime']);
            year = date.getFullYear();
            month = date.getMonth() + 1; // 月份是从0开始的，需要加1
            day = date.getDate();

            cell = row.insertCell();
            cell.className = "four";
            cell.textContent = year + '-' + month + '-' + day;

            cell = row.insertCell();
            cell.className = "five";
            cell.textContent = item['predictCost'];
        });
        const table = document.getElementById('requirement_table');
        table.appendChild(tbody);
    });

    const elementById = document.querySelectorAll('.dialog form');
    elementById.forEach(function(element) {
        element.addEventListener('submit', function (event) {
            event.preventDefault(); // 阻止表单的默认提交行为
            // 执行 AJAX 请求
            var xhr = new XMLHttpRequest();

            xhr.open(element.method, element.action, true);
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhr.responseType = 'json';
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // 响应已接收，执行相应的操作
                    const data = xhr.response;
                    alert(data['msg']);
                    location.reload();
                }
            };
            const formData = new FormData(element);
            let sendData = '';
            for (let key of formData.keys()) {
                sendData += key + '=' + formData.get(key) + '&';
            }
            xhr.send(sendData);

        });
    });

    // 获取表格
    var table = document.getElementById('requirement_table');
// 获取表格中的行
    var rows = table.getElementsByTagName('tr');

// 将行转换为数组以便排序
    var rowsArray = Array.from(rows).slice(1); // 从索引1开始，跳过表头

// 定义一个比较函数，用于按照时间进行排序
    function compare(rowA, rowB) {
        var timeA = new Date(rowA.cells[2].innerText);
        var timeB = new Date(rowB.cells[2].innerText);
        return timeA - timeB;
    }

// 对行数组进行排序
    rowsArray.sort(compare);

// 清空表格
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }

// 将排序后的行重新添加到表格中
    for (var i = 0; i < rowsArray.length; i++) {
        table.appendChild(rowsArray[i]);
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

function queryStage() {

    const elementById1 = document.getElementById('stage_id');
    fetch('/data/stage/id?stageId=' + elementById1.value)
        .then(response => response.json())
        .then(data => {
            // 在这里处理从服务器返回的数据
            if (data.status == 400) {
                alert(data.msg);
            }

            document.getElementById('id_stage_name').value = data.data.stageName;
            document.getElementById('id_start_time').value = data.data.startTime;
            document.getElementById('id_end_time').value = data.data.costTime;
            document.getElementById('id_predict_cost').value = data.data.predictCost;

        })
        .catch(error => {
            // 处理请求错误
            console.error('发生错误:', error);
        });
}
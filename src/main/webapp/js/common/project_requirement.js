$(document).ready(function() {

	// $("#req_type").change(function() {
	// 	var myselect = document.getElementById("req_type");
	// 	alert(2)
	// 	console.log(myselect.selectedIndex);
	// 	if(myselect.selectedIndex == 1) {
	// 		$("#select_file").hide();
	// 		$("#text_req").show();
	// 		$("#text_req").text(localStorage.getItem("text_req"));
	// 	} else {
	// 		$("textarea").hide();
	// 		$("#select_file").show();
	// 	}
	// });
	$("#text_req").blur(function() {
		localStorage.setItem("text_req", document.getElementById("text_req").value);
	});

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
				const data = xhr.response;
				let date;
				let year;
				let month;
				let day;
				let hours;
				let minutes;
				let seconds;
				const tbody = document.createElement('tbody');
				data['data'].forEach(item => {
					const row = tbody.insertRow();
					let cell = row.insertCell();
					cell.className = "one";
					cell.textContent = item['demandId'];
					cell = row.insertCell();
					cell.className = "two";
					cell.textContent = item['demandContent'];

					date = new Date(item['createTime']);
					year = date.getFullYear();
					month = date.getMonth() + 1; // 月份是从0开始的，需要加1
					day = date.getDate();
					// hours = date.getHours();
					// minutes = date.getMinutes();
					// seconds = date.getSeconds();

					cell = row.insertCell();
					cell.className = "three";
					cell.textContent = year + '-' + month + '-' + day; // + ' ' + hours + ':' + minutes + ':' + seconds;

					let demandStatus;
					switch (item['status']) {
						case 0:
							demandStatus = '正处理';
							break;
						case 1:
							demandStatus = '未通过';
							break;
						case 2:
							demandStatus = '已通过';
							break;
						case 3:
							demandStatus = '待删除';
							break;
						case -1:
							demandStatus = '已删除';
							break;
					}
					cell = row.insertCell();
					cell.className = "four";
					cell.textContent = demandStatus;
					cell = row.insertCell();
					cell.className = "five";
					cell.textContent = '';
					cell = row.insertCell();
					cell.className = "six";
					cell.textContent = '';
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


	$.getJSON("/data/demand/all", function(data) {
		let date;
		let year;
		let month;
		let day;
		let hours;
		let minutes;
		let seconds;
		const tbody = document.createElement('tbody');

		data.forEach(item => {
			const row = tbody.insertRow();
			let cell = row.insertCell();
			cell.className = "one";
			cell.textContent = item['demandId'];
			cell = row.insertCell();
			cell.className = "two";
			cell.textContent = item['demandContent'];

			date = new Date(item['createTime']);
			year = date.getFullYear();
			month = date.getMonth() + 1; // 月份是从0开始的，需要加1
			day = date.getDate();
			// hours = date.getHours();
			// minutes = date.getMinutes();
			// seconds = date.getSeconds();

			cell = row.insertCell();
			cell.className = "three";
			cell.textContent = year + '-' + month + '-' + day; // + ' ' + hours + ':' + minutes + ':' + seconds;

			let demandStatus;
			switch (item['status']) {
				case 0:
					demandStatus = '正处理';
					break;
				case 1:
					demandStatus = '未通过';
					break;
				case 2:
					demandStatus = '已通过';
					break;
				case 3:
					demandStatus = '待删除';
					break;
				case -1:
					demandStatus = '已删除';
					break;
			}
			cell = row.insertCell();
			cell.className = "four";
			cell.textContent = demandStatus;
			cell = row.insertCell();
			cell.className = "five";
			cell.textContent = '';
			cell = row.insertCell();
			cell.className = "six";
			cell.textContent = '';
		});
		const table = document.getElementById('requirement_table');
		table.appendChild(tbody);
	});

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
	document.getElementById("text_req").value = localStorage.getItem("text_req");
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

function getDemandContent() {
	const elementById = document.getElementById('demand_id1');
	if(elementById.value == null || elementById.value.length == 0) {
		alert('需求ID不能为空');
		return;
	}
	fetch('/data/demand/get?demandId=' + elementById.value)
		.then(response => response.json())
		.then(data => {
			// 在这里处理从服务器返回的数据
			if (data.status == 400) {
				alert(data.msg);
			}
			const demand_status = document.getElementById('demand_content');
			demand_status.value = data.data.demandContent;

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

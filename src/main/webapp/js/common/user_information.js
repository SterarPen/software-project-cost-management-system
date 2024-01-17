$(document).ready(function() {
	$("#update_password").click(function() {
		$("#mask").show();
		$(".dialog").slideUp(0);
		$("#dialog_update_password").slideDown(2000);
	});
	$(".cancel").click(function() {
		$(".dialog").slideUp(2000);
		$("#mask").hide();
	});
	$("#update_phone").click(function() {
		$("#mask").show();
		$(".dialog").slideUp(0);
		$("#dialog_update_phone").slideDown(2000);
	});
	$("#update_email").click(function() {
		$("#mask").show();
		$(".dialog").slideUp(0);
		$("#dialog_update_email").slideDown(2000);
	});

	$("#pwd_update").click(function () {
		//TODO:验证手机号


	})

	const elementNodeListOf = document.querySelectorAll('.dialog form');
	elementNodeListOf.forEach(function(element) {
		element.addEventListener('submit', function (event) {
			event.preventDefault(); // 阻止表单的默认提交行为
			// 执行 AJAX 请求
			const xhr = new XMLHttpRequest();

			xhr.open(element.method, element.action, true);
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.responseType = 'json';
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					// 响应已接收，执行相应的操作
					const response = xhr.response;
					alert(response['msg']);
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
})

function sendEmail() {
	fetch('/send_email')
		.then(response => response.json())
		.then(data => {
			// 在这里处理从服务器返回的数据
			alert(data.msg);

		})
		.catch(error => {
			// 处理请求错误
			console.error('发生错误:', error);
		});
}

function sendPhoneMessage() {
	fetch('/send_email')
		.then(response => response.json())
		.then(data => {
			// 在这里处理从服务器返回的数据
			alert(data.msg);

		})
		.catch(error => {
			// 处理请求错误
			console.error('发生错误:', error);
		});
}
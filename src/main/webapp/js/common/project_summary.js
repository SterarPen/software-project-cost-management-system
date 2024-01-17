$(document).ready(function() {
	// let cookies = document.cookie.split(';');
	// let project_id;
	// for(let temp in cookies) {
	// 	if(cookies[temp].trim().split('=')[0] == 'handling-project') {
	// 		project_id = cookies[temp].split('=')[1];
	// 	}
	// };
	$("#predict").click(function() {
		window.open("/chart?datau=predict_cost&chartype=pie&param1=expense", "_blank");
	});
	$("#cost").click(function() {
		window.open("/chart?datau=cost&chartype=pie&param1=expense", "_blank");
	});
	$("#cost_analysis").click(function() {
		window.open("/chart?datau=up_down&chartype=bar&param1=expense", "_blank");
	});

	// $.get('/project_information/' + project_id, function (data) {
	// 	$('#project_name').text(data['projectId']);
	// }, 'json');
})


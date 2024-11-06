$(document).ready(function() {
	$("#predict").click(function() {
		window.open("chart.html?datau=predict_cost&chartype=pie&param1=expense", "_blank");
	});
	$("#cost").click(function() {
		window.open("chart.html?datau=cost&chartype=pie&param1=expense", "_blank");
	});
	$("#cost_analysis").click(function() {
		window.open("chart.html?datau=up_down&chartype=bar&param1=expense", "_blank");
	});
})
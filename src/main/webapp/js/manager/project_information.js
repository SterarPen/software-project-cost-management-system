$(document).ready(function() {
	$("#schedule").click(function() {
		window.open("/chart?datau=cost&chartype=bar&param1=expense", "_blank");
	});
	$("#main").click(function() {
		window.open("/chart?datau=schedule&chartype=bar&param1=finish&param2=no_finish", "_blank");
	});
})
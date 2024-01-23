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
})
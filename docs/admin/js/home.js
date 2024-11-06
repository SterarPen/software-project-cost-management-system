$(document).ready(function() {
	$(".bar_ul a").click(function() {
		$(".bar_ul li").css("color", "white");
		$(".bar_ul li a").css({"color": "#6b7280", "font-weight": 400});
		$(".bar_ul li div").css("background-color", "white");
		
		$(this).parent().css({"color": "rgb(251, 199, 86)"});
		$(this).next().css({"background-color":  "rgb(251, 199, 86)"});
		$(this).css({"color": "#000000", "font-weight": 700});
		
		$(".top #title").text($(this).text());
	})
	
});

// 加载后直接显示项目汇总
window.onload = function() {
	$('#first_a').click();
	$('iframe').prop('src', 'project_summary.html');
};
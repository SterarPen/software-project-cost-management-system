$(document).ready(function() {
	$(".new_requirement").click(function() {
		$("#mask").show();
		$(".dialog").slideUp(0);
		$("#create_requirement").slideDown(2000);
	});
	$(".cancel").click(function() {
		$(".dialog").slideUp(2000);
		$("#mask").slideUp();
	});
	$("#req_type").change(function() {
		var myselect = document.getElementById("req_type");
		console.log(myselect.selectedIndex);
		if(myselect.selectedIndex == 1) {
			$("#select_file").hide();
			$("#text_req").show();
			$("#text_req").text(localStorage.getItem("text_req"));
		} else {
			$("textarea").hide();
			$("#select_file").show();
		}
	});
	$("#text_req").blur(function() {
		localStorage.setItem("text_req", document.getElementById("text_req").value)
	});
	
	
	
	$.getJSON("/admin/all_project_information", function(data) {
		// $("#table_req").remove('<td>');
		$.each(data, function(key, val) {
			var newRow = $('<tr>');
			
			newRow.append($('<td>').text(val.projectId));
			newRow.append($('<td>').text(val.projectName));
			newRow.append($('<td>').text(val.createTime));
			newRow.append($('<td>').text(val.finishTime));
			newRow.append($('<td>').text(val.developerManagerId));
			newRow.append($('<td>').append(newButton));
			$(".reqiurement #table_req").append(newRow);
		});
	});
	
});

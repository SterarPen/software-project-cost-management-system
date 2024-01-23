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
	
	
	
	$.getJSON("json/requirement.json", function(data) {
		// $("#table_req").remove('<td>');
		$.each(data, function(key, val) {
			var newRow = $('<tr>');
			
			var newColumn2 = $('<td>');
			newColumn2.text(val.content);
			
			
				
			
			newRow.append($('<td>').text(val.id));
			newRow.append(newColumn2);
			newRow.append($('<td>').text(val.create_time));
			newRow.append($('<td>').text(val.status));
			newRow.append($('<td>').text(val.type));
			
			if("file" == val.type) {
				// 通过在href中给定不同URL参数，来下载对应的文件
				var newButton = $("<a href='https://baidu.com' style='width: 30px;' target='_blank'>OP</a>");
				
			} else {
				// var newButton = $("<button style='width: 30px; background-color: white; padding: 0; border:0; color: #55aaff'>OP</button>");
				newColumn2.hover(function(){
					$(this).css('color', 'red');
				});
				newColumn2.mouseleave(function() {
					$(this).css('color', 'black');
				});
				
				newColumn2.click(function() {
					window.open('', '_blank').document.body.innerText = val.content;
				});
			}
			
			newRow.append($('<td>').append(newButton));
			$(".reqiurement #table_req").append(newRow);
			// $("button").click(function() {
			// 	$(this).prop("color", "blue");
			// })
		});
	});
	
});

//顯示換行及空格
$(document).ready(function() {
	var content = $('#article').text();
	// alert(content)
	$('#article').html((content.replace(/\n/g, '<br/>')).replace(/\s/g,"&nbsp;"));
})

// 若無輸入遊戲發售日則顯示尚無確切發行日期
$(document).ready(function() {
	var id = $('#gameId').text();
	var publicationDate = $('#gamePublicationDate').text();
	// console.log("id="+id)
	// xxx.text().length如果該行沒加</p>且是空值一行長度為5
	// console.log("id.length="+id.length)
	// console.log("publicationDate="+publicationDate)
	// console.log("publicationDate.length="+publicationDate.length)
	if (id.length != 0) {
		if (publicationDate.length == 6) {
			$("#gamePublicationDate").text("遊戲發售日:尚無確切發行日期");
		}
		$("#gameDetail").show();
	}
})

// 依不同的活動日數顯示時間
$(document).ready(function() {
	var html = "";
	var id = $('#activityId').text();
	var startingDate_time = $('#startingDate_time').text();
	var startingTime_date = $('#startingTime_date').text();
	// console.log("startingTime_date="+startingTime_date)//活動時間:00:00:00
	var startingDate = $('#startingDate').text();
	var endingDate = $('#endingDate').text();
	if (id.length != 0) {
		if (startingDate_time.length == 5) {
			$("#startingDate_time").html(html);
		}
		if (startingTime_date == "活動時間:00:00:00") {
			$("#startingTime_date").html(html);
		}
		if (startingDate.length == 6) {
			$("#startingDate").html(html);
		}
		if (endingDate.length == 6) {
			$("#endingDate").html(html);
		}
		$("#activityDetail").show();
	}
})

// 點入消息的計次功能
function countView(newsId) {
	$.ajax({
		type : "POST",
		url : "countForNews",
		dataType : "json",
		data : {
			count : 1,
			newsId : newsId
		},
		success : function() {
			// alert("成功")
		},
	});
}

//新增評論
function addMemo() {
	let showComment = "";
	let newsId = $('#newsId').val();
	let member_id = $("#member_id").val();
	let memo = $("#addMemo").val();
	console.log("newsId=" + newsId);
	console.log("member_id=" + member_id);
	console.log("memo=" + memo);
	if (member_id == "") {
		alert("請先登入!!!!!")
	} else {
		$.ajax({
			type : "POST",
			url : "addMemo",
			dataType : "json",
			data : {
				newsId : newsId,
				member_id : member_id,
				memo : memo
			},
			success : function(response) {
				console.log(response);
					showComment += '<div class="media border p-3" style="width:600px;">';
					showComment += '<div class="media-body">';
					showComment += '<h4 style="color: #BBFFEE">'+ response.userName;
					showComment += '<small style="margin-left:5%"><i>Posted on '+ response.publicationDate+'</i></small>';
					showComment += '</h4>';
					showComment += '<p id="'+ response.messageId +'"style="color:#FFFFBB;margin-top:10px">'+ response.memo +'</p>';
					showComment += '</div></div>';
					 $("#addMemo").val("");
					 $("#add").before(showComment);
			}
		});
	}
}

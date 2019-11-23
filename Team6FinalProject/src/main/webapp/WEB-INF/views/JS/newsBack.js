// 傳消息類別更新資訊:
function updateNewsType(newsTypeId, newsTypeName) {
	// alert(newsTypeId)
	// alert(newsTypeName)
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新消息類別</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateNewsType">'
			+ '<input type="hidden" name="newsTypeId" value='
			+ newsTypeId
			+ ' /><p>'
			+ '消息類別名稱:<input name="newsTypeName" type="text" size="20px" value='
			+ newsTypeName
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

// 傳消息類別刪除資訊:
//function deleteNewsType(newsTypeId) {
//// alert(newsTypeId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除消息類別</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteNewsType">'
//		+ '<input type="hidden" name="newsTypeId" value='
//		+ newsTypeId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

// 傳遊戲類別更新資訊:
function updateGameType(gmaeTypeId, gmaeTypeName) {
	// alert(gmaeTypeId)
	// alert(gmaeTypeName)
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新遊戲類別</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateGameType">'
			+ '<input type="hidden" name="gameTypeId" value='
			+ gmaeTypeId
			+ ' /><p>'
			+ '遊戲類別名稱:<input name="gmaeTypeName" type="text" size="20px" value='
			+ gmaeTypeName
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

// 傳遊戲類別刪除資訊:
//function deleteGameType(gmaeTypeId) {
//// alert(gmaeTypeId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除遊戲類別</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteGameType">'
//		+ '<input type="hidden" name="gameTypeId" value='
//		+ gmaeTypeId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

// 傳活動類別更新資訊:
function updateActivityType(activityTypeId, activityTypeName) {
	// alert(activityTypeId)
	// alert(activityTypeName)
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新活動類別</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateActivityType">'
			+ '<input type="hidden" name="activityTypeId" value='
			+ activityTypeId
			+ ' /><p>'
			+ '活動類別名稱:<input name="activityTypeName" type="text" size="20px" value='
			+ activityTypeName
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

// 傳活動類別刪除資訊:
//function deleteActivityType(activityTypeId) {
//// alert(activityTypeId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除活動類別</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteActivityType">'
//		+ '<input type="hidden" name="activityTypeId" value='
//		+ activityTypeId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

// 傳遊戲細節更新資訊:
function updateGame(gameId, gameName,gameTypeId,gameTypeName,publicationDate,publisher,platform) {
// alert(gameId)
// alert(gameTypeName)
	let html;
	$.ajax({
		url : "newsBack/searchGameTypeByAjax",
		success : function(data) {
			const gameTypeIdList = Object.values(data).map(item => item.gameTypeId);
			const gameTypeNameList = Object.values(data).map(item => item.gameTypeName);
// alert(data.length);
// alert(gameTypeIdList);
// alert(gameTypeNameList);
			html = "遊戲類別:<select name='gameType'> ";
			html += "<option value="+ gameTypeId +">"+gameTypeName+"</option>";
			for(var i=0;i<data.length;i++){
				var id = gameTypeIdList[i];
				var name = gameTypeNameList[i];
				if(id != gameTypeId){
				html +="<option value="+ id +">"+name+"</option>";
				}
			}
			html += "</select>";
			$("#test").html(html);
		}
	});
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新遊戲細節</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateGame">'
			+ '<input type="hidden" name="gameId" value='
			+ gameId
			+ ' /><p>'
			+ '遊戲名稱:<input name="gameName" type="text" size="20px" value='
			+ gameName
			+ ' /><p>'
			+ '<span id="test"></span>'
			+ '<p><p>'
			+ '遊戲發售日:<input name="publicationDate" type="text" size="20px" value='
			+ publicationDate
			+ '><p>'
			+ '遊戲發行商:<input name="publisher" type="text" size="20px" value='
			+ publisher
			+ ' /><p>'
			+ '遊戲平台:<input name="platform" type="text" size="20x" value='
			+ platform
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

// 傳遊戲刪除資訊:
//function deleteGame(gameId) {
//// alert(gameId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除遊戲</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteGame">'
//		+ '<input type="hidden" name="gameId" value='
//		+ gameId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

// 傳一日活動細節更新資訊:
function updateActivityOne(activityId, activityName,activityTypeId,activityTypeName,startingDate_time,startingTime_date,location) {
// alert(activityId)
// alert(activityTypeName)
	let html;
	$.ajax({
		url : "newsBack/searchActivityTypeByAjax",
		success : function(data) {
			const activityTypeIdList = Object.values(data).map(item => item.activityTypeId);
			const activityTypeNameList = Object.values(data).map(item => item.activityTypeName);
//alert(data.length);
//alert(activityTypeIdList);
//alert(activityTypeNameList);
			html = "活動類別:<select name='activityType'> ";
			html += "<option value="+ activityTypeId +">"+activityTypeName+"</option>";
			for(var i=0;i<data.length;i++){
				var id = activityTypeIdList[i];
				var name = activityTypeNameList[i];
				if(id != activityTypeId){
				html +="<option value="+ id +">"+name+"</option>";
				}
			}
			html += "</select>";
			$("#test").html(html);
		}
	});
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新活動細節</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateActivityOne">'
			+ '<input type="hidden" name="activityId" value='
			+ activityId
			+ ' /><p>'
			+ '活動名稱:<input name="activityName" type="text" size="20px" value='
			+ activityName
			+ ' /><p>'
			+ '<span id="test"></span>'
			+ '<p><p>'
			+ '活動日期:<input name="startingDate_time" type="text" size="20px" value='
			+ startingDate_time
			+ '><p>'
			+ '活動時間:<input name="startingTime_date" type="text" size="20px" value='
			+ startingTime_date
			+ '><p>'
			+ '活動地點:<input name="location" type="text" size="20px" value='
			+ location
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

//傳一日活動刪除資訊:
//function deleteActivityOne(activityId) {
//// alert(activityId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除活動</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteActivity">'
//		+ '<input type="hidden" name="activityId" value='
//		+ activityId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

//傳多日活動細節更新資訊:
function updateActivityMore(activityId, activityName,activityTypeId,activityTypeName,startingDate,endingDate,location) {
// alert(activityId)
// alert(activityTypeName)
	let html;
	$.ajax({
		url : "newsBack/searchActivityTypeByAjax",
		success : function(data) {
			const activityTypeIdList = Object.values(data).map(item => item.activityTypeId);
			const activityTypeNameList = Object.values(data).map(item => item.activityTypeName);
//alert(data.length);
//alert(activityTypeIdList);
//alert(activityTypeNameList);
			html = "活動類別:<select name='activityType'> ";
			html += "<option value="+ activityTypeId +">"+activityTypeName+"</option>";
			for(var i=0;i<data.length;i++){
				var id = activityTypeIdList[i];
				var name = activityTypeNameList[i];
				if(id != activityTypeId){
				html +="<option value="+ id +">"+name+"</option>";
				}
			}
			html += "</select>";
			$("#test").html(html);
		}
	});
	document.getElementById("xxx").innerHTML = '<h5 class="modal-title" id="exampleModalLabel">更新活動細節</h5>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
			+ '<span>&times;</span>' + '</button>'
	document.getElementById("xxx1").innerHTML = '<form method="POST" action="updateActivityMore">'
			+ '<input type="hidden" name="activityId" value='
			+ activityId
			+ ' /><p>'
			+ '活動名稱:<input name="activityName" type="text" size="20px" value='
			+ activityName
			+ ' /><p>'
			+ '<span id="test"></span>'
			+ '<p><p>'
			+ '活動起始日:<input name="startingDate" type="text" size="20px" value='
			+ startingDate
			+ '><p>'
			+ '活動結束日:<input name="endingDate" type="text" size="20px" value='
			+ endingDate
			+ '><p>'
			+ '活動地點:<input name="location" type="text" size="20px" value='
			+ location
			+ ' /><p>'
			+ '<input type="submit" class="btn btn-warning" value="更新"></form>'
}

//傳多日活動刪除資訊:
//function deleteActivityMore(activityId) {
//// alert(activityId)
//	document.getElementById("xxx").innerHTML = 
//		'<h5 class="modal-title" id="exampleModalLabel">刪除活動</h5>'
//		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
//		+ '<span>&times;</span>'
//		+  '</button>'
//	document.getElementById("xxx1").innerHTML = 
//		'<form method="post" action="deleteActivity">'
//		+ '<input type="hidden" name="activityId" value='
//		+ activityId
//		+ ' /><p>'
//		+ '<input type="submit" class="btn btn-warning" value="確認刪除"></form>'
//}

//將發佈消息隱藏:
function deleteNewsShow(newsId) {
// alert(newsId)
	document.getElementById("xxx").innerHTML = 
		'<h5 class="modal-title" id="exampleModalLabel">消息即將隱藏</h5>'
		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+ '<span>&times;</span>'
		+  '</button>'
	document.getElementById("xxx1").innerHTML = 
		'<form method="post" action="deleteNewsShow">'
		+ '<input type="hidden" name="newsId" value='
		+ newsId
		+ ' /><p>'
		+ '<input type="submit" class="btn btn-warning" value="確認隱藏"></form>'
}

//將隱藏消息發佈:
function reopenNews(newsId) {
// alert(newsId)
	document.getElementById("xxx").innerHTML = 
		'<h5 class="modal-title" id="exampleModalLabel">消息即將發佈</h5>'
		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+ '<span>&times;</span>'
		+  '</button>'
	document.getElementById("xxx1").innerHTML = 
		'<form method="post" action="reopenNews">'
		+ '<input type="hidden" name="newsId" value='
		+ newsId
		+ ' /><p>'
		+ '<input type="submit" class="btn btn-warning" value="確認發佈"></form>'
}

$(document).ready(function () {
	hotNewsTop5();
})

function hotNewsTop5() {
	let titles = []
	let views = []
	$.ajax({
		type : "method",
		url: "hotNewsTop5.json",
		success: function (response) {
			titles=Object.keys(response.hotNewsTop5[0])
			for (let i = 0; i < titles.length; i++) {
				views.push(response.hotNewsTop5[0][titles[i]])
			}
			console.log(titles)
			console.log(views)
			showHotNewsTop5(titles,views)
		}
	});
}

function showHotNewsTop5(titles,views) {
	var ctx = document.getElementById("chart").getContext('2d');
	var chart = new Chart(ctx, {
		type: 'horizontalBar',
		data: {
			labels: titles,
			datasets: [{
				label: '發文截至今日之總觀看次數',
				data: views,
				backgroundColor: 'red',
				borderColor: 'red',
				borderWidth: 1,
			}]
		}
	});
} 
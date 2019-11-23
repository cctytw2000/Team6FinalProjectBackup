function GoBack() {
		history.go(-1)
	}

$(document).ready(function() {	
	showNewsType();
	showOriginalGameDetail();
	showOriginalActivityDetail();
	hasGame();
	hasActivity();
})

// 製造newsType下拉式選單
function showNewsType(){
	let newsTypeId=$("#newsType").text();
	console.log("oldNewsTypeId1="+newsTypeId)
	$.ajax({
		url : "searchNewsTypeByAjax1",
		success : function(data) {
			const newsTypeIdList = Object.values(data).map(item => item.newsTypeId);
			const newsTypenameList = Object.values(data).map(item => item.newsTypeName);
			let html = "<select name='newsType'>";
			 for(let i=0;i<data.length;i++){ 
				 let id = newsTypeIdList[i]; 
		            let name = newsTypenameList[i];
		            if(id==newsTypeId){
		            	html += "<option selected='selected' value='"+id+"'+>"+name+"</option>";
		            	console.log("oldNewsTypeId2="+id)
		            } else {
		            	html +="<option value="+ id +">"+name+"</option>";
		            }
		          }
			 html += "</select>";
			 $("#newsType").show().html(html);
		}
	});
}

// 有遊戲細節的話顯示遊戲細節
function showOriginalGameDetail(){
	let gameId=$("#gameDetail2").text();
	console.log("gameId="+gameId);
	if(gameId!=""){
		$.ajax({
			type : "POST",
			url : "searchGameByAjax2",
			dataType : "json",
			data : {
				gameId : gameId,
			},
			success : function(data) {
				$("#gameDetail1").show();
				let html ='<table border=2 RULES=ALL><tr><th>遊戲名稱</th><th>遊戲類別</th><th>遊戲發售日</th><th>遊戲發行商</th><th>遊戲平台</th></tr><tbody><tr>';
				html +="<td>"+data.name+"</td>";
				html +="<td>"+data.gameTypeName+"</td>";
				html +="<td>"+data.publicationDate+"</td>";
				html +="<td>"+data.publisher+"</td>";
				html +="<td>"+data.platform+"</td>";
				html += '</tr></tbody></table>'	
				$("#showOriginalGame1").html(html);
			}
		});
	}
}

// 有活動細節的話顯示活動細節
function showOriginalActivityDetail(){
	let activityId=$("#activityDetail2").text();
	console.log("activityId="+activityId);
	if(activityId!=""){
		$.ajax({
			type : "POST",
			url : "searchActivityByAjax2",
			dataType : "json",
			data : {
				activityId : activityId,
			},
			success : function(data) {
				$("#activityDetail1").show();
				if(!(data.startingDate_time == null) && !(data.startingTime_date == null)){
					let html ='<table border=2 RULES=ALL><tr><th>活動名稱</th><th>活動類別</th><th>活動地點</th><th>活動日期</th><th>活動時間</th></tr><tbody><tr>';
					html +="<td>"+data.name+"</td>";
					html +="<td>"+data.activityTypeName+"</td>";
					html +="<td>"+data.location+"</td>";
					html +="<td>"+data.startingDate_time+"</td>";
					html +="<td>"+data.startingTime_date+"</td>";
					html += '</tr></tbody></table>';
					$("#showOriginalActivity1").html(html);
				} else if(!(data.startingDate == null) && !(data.endingDate == null)){
					let html ='<table border=2 RULES=ALL><tr><th>活動名稱</th><th>活動類別</th><th>活動地點</th><th>活動起始日</th><th>活動結束日</th></tr><tbody><tr>';
					html +="<td>"+data.name+"</td>";
					html +="<td>"+data.activityTypeName+"</td>";
					html +="<td>"+data.location+"</td>";
					html +="<td>"+data.startingDate+"</td>";
					html +="<td>"+data.endingDate+"</td>";
					html += '</tr></tbody></table>'	;
					$("#showOriginalActivity1").html(html);
				}
			}
		});
	}
}

function hasGame(){
	let gameId=$("#gameDetail2").text();
	if(gameId==""){
		$("#noGame").show();
	} else {
		$("#hasGame").show();
	}
}

function updateGame() {
	let newsId=$("#newsId").val();
	let gameId=$("#gameDetail2").text();
//	alert("gameId=="+gameId);
    let html1="";
    html1 += '<h5 class="modal-title" id="exampleModalLabel">更改遊戲細節</h5>';
   	html1 += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
   	html1 += '<span>&times;</span>';
   	html1 += '</button>';
   	$("#xxx").html(html1);
   	
   	$.ajax({
		url : "searchGameByAjax2_1",
		success : function(data) {
			const gameIdList = Object.values(data).map(item => item.gameId);
			const gameNameList = Object.values(data).map(item => item.gameName);
			let html2 = "";
			html2 += '<form method="post" action="updateGameInUpdateNews">';
			html2 += '<input type="hidden" name="newsId" value="'+newsId+'" >';
			html2 += "遊戲:<span><select name='game' onchange='gameDetail(this.value)'>";
			html2 += "<option value='-1'>請挑選</option>";
			for(let i=0;i<data.length;i++){
				let id = gameIdList[i];
				let name = gameNameList[i];
				if(gameId != id){
					html2 +="<option value="+ id +">"+name+"</option>";
				}
			}
			html2 += "</select></span><p><div id='test'></div>";
			html2 += '<input type="submit" class="btn btn-warning" value="確認"></form>';
			$("#xxx1").html(html2);
		}
	});
}

function deleteGame(){
	let newsId=$("#newsId").val();
	console.log("newsId=="+newsId);
	document.getElementById("xxx").innerHTML = 
		'<h5 class="modal-title" id="exampleModalLabel">取消顯示遊戲細節</h5>'
		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+ '<span>&times;</span>'
		+  '</button>'
	document.getElementById("xxx1").innerHTML = 
		'<form method="post" action="deleteGameInUpdateNews">'
		+ '<input type="hidden" name="newsId" value="'+newsId+'" >'
		+ '<input type="hidden" name="gameId" value="" >'
		+ '<input type="submit" class="btn btn-warning" value="確認"></form>'
}

// 製造game下拉式選單
function showGame() {
	let newsId=$("#newsId").val();
    let html1="";
    html1 += '<h5 class="modal-title" id="exampleModalLabel">顯示遊戲細節</h5>';
   	html1 += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
   	html1 += '<span>&times;</span>';
   	html1 += '</button>';
   	$("#xxx").html(html1);
   	
   	$.ajax({
		url : "searchGameByAjax2_1",
		success : function(data) {
			const gameIdList = Object.values(data).map(item => item.gameId);
			const gameNameList = Object.values(data).map(item => item.gameName);
			let html2 = "";
			html2 += '<form method="post" action="showGameInUpdateNews">';
			html2 += '<input type="hidden" name="newsId" value="'+newsId+'" >';
			html2 += "遊戲:<span><select name='game' onchange='gameDetail(this.value)'>";
			html2 += "<option value='-1'>請挑選</option>";
			for(let i=0;i<data.length;i++){
				let id = gameIdList[i];
				let name = gameNameList[i];
				html2 +="<option value="+ id +">"+name+"</option>";
			}
			html2 += "</select></span><p><div id='test'></div>";
			html2 += '<input type="submit" class="btn btn-warning" value="確認"></form>';
			$("#xxx1").html(html2);
		}
	});
}

function gameDetail(number){
	if(number == -1){
		let html1 ="";
		$("#showGame3").html(html1);
	}
	$.ajax({
		type : "POST",
		url : "searchGameByAjax2",
		dataType : "json",
		data : {
			gameId : number,
		},
		success : function(data) {
			let html ='<table id="showGame3"border=2 RULES=ALL><tr><th>遊戲名稱</th><th>遊戲類別</th><th>遊戲發售日</th><th>遊戲發行商</th><th>遊戲平台</th></tr><tbody><tr>';
			html +="<td>"+data.name+"</td>";
			html +="<td>"+data.gameTypeName+"</td>";
			html +="<td>"+data.publicationDate+"</td>";
			html +="<td>"+data.publisher+"</td>";
			html +="<td>"+data.platform+"</td>";
			html += '</tr></tbody></table><p>'	
			$("#test").html(html);
		}
	});
}

function hasActivity(){
	let activityId=$("#activityDetail2").text();
	if(activityId==""){
		$("#noActivity").show();
	} else {
		$("#hasActivity").show();
	}
}

function updateActivity() {
	let newsId=$("#newsId").val();
	let activityId=$("#activityDetail2").text();
    let html1="";
    html1 += '<h5 class="modal-title" id="exampleModalLabel">更改活動細節</h5>';
   	html1 += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
   	html1 += '<span>&times;</span>';
   	html1 += '</button>';
   	$("#xxx").html(html1);
   	
   	$.ajax({
   		url : "searchActivityByAjax2_1",
    	success : function(data) {
    		const activityIdList = Object.values(data).map(item => item.activityId);
    		const activityNameList = Object.values(data).map(item => item.activityName);
    		let html2 = "";
    		html2 += '<form method="post" action="updateActivityInUpdateNews">';
    		html2 += '<input type="hidden" name="newsId" value="'+newsId+'" >';
    		html2 += "活動:<span><select name='activity' onchange='activityDetail(this.value)'>";
    		html2 += "<option value='-1'>請挑選</option>";
    		for(let i=0;i<data.length;i++){
    			let id = activityIdList[i];
    			let name = activityNameList[i];
    			if(activityId != id){
    				html2 +="<option value="+ id +">"+name+"</option>";
    			}
    		}
    		html2 += "</select></span><p><div id='activityShow'></div>";
    		html2 += '<input type="submit" class="btn btn-warning" value="確認"></form>';
    		$("#xxx1").html(html2);
    	}
   	});
}

function deleteActivity(){
	let newsId=$("#newsId").val();
	console.log("newsId=="+newsId);
	document.getElementById("xxx").innerHTML = 
		'<h5 class="modal-title" id="exampleModalLabel">取消顯示活動細節</h5>'
		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+ '<span>&times;</span>'
		+  '</button>'
	document.getElementById("xxx1").innerHTML = 
		'<form method="post" action="deleteActivityInUpdateNews">'
		+ '<input type="hidden" name="newsId" value="'+newsId+'" >'
		+ '<input type="hidden" name="activityId" value="" >'
		+ '<input type="submit" class="btn btn-warning" value="確認"></form>'
}

// 製造activity下拉式選單
function showActivity() {
	let newsId=$("#newsId").val();
    let html1="";
    html1 += '<h5 class="modal-title" id="exampleModalLabel">顯示活動細節</h5>';
   	html1 += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
   	html1 += '<span>&times;</span>';
   	html1 += '</button>';
   	$("#xxx").html(html1);
    		
   	$.ajax({
   		url : "searchActivityByAjax2_1",
    	success : function(data) {
    		const activityIdList = Object.values(data).map(item => item.activityId);
    		const activityNameList = Object.values(data).map(item => item.activityName);
    		let html2 = "";
    		html2 += '<form method="post" action="showActivityInUpdateNews">';
    		html2 += '<input type="hidden" name="newsId" value="'+newsId+'" >';
    		html2 += "活動:<span><select name='activity' onchange='activityDetail(this.value)'>";
    		html2 += "<option value='-1'>請挑選</option>";
    		for(let i=0;i<data.length;i++){
    			let id = activityIdList[i];
    			let name = activityNameList[i];
    			html2 +="<option value="+ id +">"+name+"</option>";
    		}
    		html2 += "</select></span><p><div id='activityShow'></div>";
    		html2 += '<input type="submit" class="btn btn-warning" value="確認"></form>';
    		$("#xxx1").html(html2);
    	}
   	});
}

// 產生activityDetail
function activityDetail(number) {
	if(number == -1){
		let html1 ="";
		$(".showActivity3").html(html1);
	}
	$.ajax({
		type : "POST",
		url : "searchActivityByAjax2",
		dataType : "json",
		data : {
			activityId : number,
		},
		success : function(data) {
			var html1="";
			if(!(data.startingDate_time == null) && !(data.startingTime_date == null)){
				let html ='<table class="showActivity3" border=2 RULES=ALL><tr><th>活動名稱</th><th>活動類別</th><th>活動地點</th><th>活動日期</th><th>活動時間</th></tr><tbody><tr>';
				html +="<td>"+data.name+"</td>";
				html +="<td>"+data.activityTypeName+"</td>";
				html +="<td>"+data.location+"</td>";
				html +="<td>"+data.startingDate_time+"</td>";
				html +="<td>"+data.startingTime_date+"</td>";
				html += '</tr></tbody></table><p>'	
				$("#activityShow").html(html);
			} else if(!(data.startingDate == null) && !(data.endingDate == null)){
				let html ='<table class="showActivity3" border=2 RULES=ALL><tr><th>活動名稱</th><th>活動類別</th><th>活動地點</th><th>活動起始日</th><th>活動結束日</th></tr><tbody><tr>';
				html +="<td>"+data.name+"</td>";
				html +="<td>"+data.activityTypeName+"</td>";
				html +="<td>"+data.location+"</td>";
				html +="<td>"+data.startingDate+"</td>";
				html +="<td>"+data.endingDate+"</td>";
				html += '</tr></tbody></table><p>'	
				$("#activityShow").html(html);
			}
		}
	});
}

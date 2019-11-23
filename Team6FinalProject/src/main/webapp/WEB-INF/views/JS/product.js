




$(document).ready(function () {
	
	
		let id = document.getElementById('game_id').value;
		let username = document.getElementById("loginusername").value;

		let showComment = "";
		$.ajax({
			url : "productComment.json?game_id="+id,
			success : function(response) {
				for (let i = 0; i < response.comments.length; i++) {
					console.log(response.comments[i].comment_id);
					console.log(response.comments[i].member_name);
					console.log(response.comments[i].time);
					console.log(response.comments[i].comment);
					
					showComment += '<div class="media border p-3" style="width: 600px;">'
					showComment += '<div class="media-body">'
					showComment += '<h4 style="color: #BBFFEE">'+ response.comments[i].member_name
					showComment += '<small style="margin-left:15%"><i>Posted on '+response.comments[i].time.replace(".0","")+'</i></small>'
					if(username == response.comments[i].member_name){
						showComment += "<small style='margin-left:5%'><i id='edit' onclick='edit("
							+'"'+response.comments[i].comment_id+
							'","'
							+response.comments[i].comment+
							'","'
							+id+'"'+
							")'>編輯</i></small>"
					}
					showComment += '</h4>'
					showComment += '<p id="'+response.comments[i].comment_id+'" style="color: #FFFFBB;margin-top:10px">'+response.comments[i].comment+'</p>'
					showComment += '</div></div>'
				}
				
				document.getElementById("commentInfo").innerHTML = showComment
			}
		});



	

});




function edit(comment_id, comment, game_id) {
	let info = "";
	info += '<form>'
	info += '<input type="hidden" name="game_id" value="' + game_id
			+ '"></input>'
	info += '<input type="hidden" name="comment_id" value="' + comment_id
			+ '"></input>'
	info += '<input class="form-control" style="width: 200px;float:left" type="text" name="comment" value="' + comment
			+ '"></input>'
	info += '<input class="btn btn-success" style="width:80px;;" value="送出"  onclick="'
	info += "sendEdit('"+comment_id+"','"+game_id+"')"
	info += '"></input></form>'
	document.getElementById(comment_id).innerHTML = info

}

function sendEdit(comment_id, game_id){
	let id = document.getElementById('game_id').value;
	let username = document.getElementById("loginusername").value;
	let comment = $("input[name='comment']").val();
	let showComment = "";
	$.ajax({

		url: 'http://localhost:8080/Team6FinalProject/edditComment?comment_id=' + comment_id + '&comment=' + comment + '&game_id=' + game_id,

		success: function (response) {
			for (let i = 0; i < response.comments.length; i++) {
				console.log(response.comments[i].comment_id);
				console.log(response.comments[i].member_name);
				console.log(response.comments[i].time);
				console.log(response.comments[i].comment);
				
				showComment += '<div class="media border p-3" style="width: 600px;">'
				showComment += '<div class="media-body">'
				showComment += '<h4 style="color: #BBFFEE">'+ response.comments[i].member_name
				showComment += '<small style="margin-left:5%"><i>Posted on '+response.comments[i].time.replace(".0","")+'</i></small>'
				if(username == response.comments[i].member_name){
					showComment += "<small style='margin-left:5%'><i id='edit' onclick='edit("
						+'"'+response.comments[i].comment_id+
						'","'
						+response.comments[i].comment+
						'","'
						+id+'"'+
						")'>編輯</i></small>"
				}
				showComment += '</h4>'
				showComment += '<p id="'+response.comments[i].comment_id+'" style="color: #FFFFBB;margin-top:10px">'+response.comments[i].comment+'</p>'
				showComment += '</div></div>'
			}
			
			document.getElementById("commentInfo").innerHTML = showComment
		}
	});
}

function addComment(){
	let id = document.getElementById('game_id').value;
	let username = document.getElementById("loginusername").value;
	let comment = document.getElementById("addComment").value;
	let showComment = "";
	$.ajax({
		url:'http://localhost:8080/Team6FinalProject/addComment?comment=' + comment + '&game_id=' + id,
		success: function (response) {
			for (let i = 0; i < response.comments.length; i++) {
				console.log(response.comments[i].comment_id);
				console.log(response.comments[i].member_name);
				console.log(response.comments[i].time);
				console.log(response.comments[i].comment);
				
				showComment += '<div class="media border p-3" style="width: 600px;">'
				showComment += '<div class="media-body">'
				showComment += '<h4 style="color: #BBFFEE">'+ response.comments[i].member_name
				showComment += '<small style="margin-left:5%"><i>Posted on '+response.comments[i].time.replace(".0","")+'</i></small>'
				if(username == response.comments[i].member_name){
					showComment += "<small style='margin-left:5%'><i id='edit' onclick='edit("
						+'"'+response.comments[i].comment_id+
						'","'
						+response.comments[i].comment+
						'","'
						+id+'"'+
						")'>編輯</i></small>"
				}
				showComment += '</h4>'
				showComment += '<p id="'+response.comments[i].comment_id+'" style="color: #FFFFBB;margin-top:10px">'+response.comments[i].comment+'</p>'
				showComment += '</div></div>'
			}
			document.getElementById("addComment").value = "";
			document.getElementById("commentInfo").innerHTML = showComment
		}
	
	});
}

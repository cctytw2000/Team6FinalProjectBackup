




$(document).ready(function () {
	
	
		let id = document.getElementById('game_id').value;
		let username = document.getElementById("loginusername").value;

		let showComment = "";
		$.ajax({
			url : "http://localhost:8080/Team6FinalProject/productComment.json?game_id="+id,
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
					
						showComment += "<small style='margin-left:5%'><i id='remove' onclick='removeEdit("
							+'"'+response.comments[i].comment_id+
							'","'
							+id+'"'+
							")'>刪除</i></small>"
					
					showComment += '</h4>'
					showComment += '<p id="'+response.comments[i].comment_id+'" style="color: #FFFFBB;margin-top:10px">'+response.comments[i].comment+'</p>'
					showComment += '</div></div>'
				}
				
				document.getElementById("commentInfo").innerHTML = showComment
			}
		});



	

});




function removeEdit(comment_id, game_id){
	let id = document.getElementById('game_id').value;
	let username = document.getElementById("loginusername").value;
	let comment = $("input[name='comment']").val();
	let showComment = "";
	$.ajax({

		url: 'http://localhost:8080/Team6FinalProject/removeComment?comment_id=' + comment_id + '&game_id=' + game_id,

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
				
					showComment += "<small style='margin-left:5%'><i id='remove' onclick='removeEdit("
						+'"'+response.comments[i].comment_id+
						'","'
						+id+'"'+
						")'>刪除</i></small>"
				
				showComment += '</h4>'
				showComment += '<p id="'+response.comments[i].comment_id+'" style="color: #FFFFBB;margin-top:10px">'+response.comments[i].comment+'</p>'
				showComment += '</div></div>'
			}
			
			document.getElementById("commentInfo").innerHTML = showComment
		}
	});
}





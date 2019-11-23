$(document).ready(function() {
	
	for(let i = 0 ; i<3;i++){
		var article1 = $("#"+i).text();
		$("#"+i).text(article1.substr(0, 30))
	}

})

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

function homeupdateviews(id) {
	$.ajax({
		type : "POST",
		url : "member/updateMoviesViews",
		data : {
			movieId : id
		},

		success : function(response) {
			document.getElementById(id).setAttribute("onclick", "")
		}
	});
}
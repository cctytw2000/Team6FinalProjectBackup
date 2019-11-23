
function updateviews(id) {
	$.ajax({
		type : "POST",
		url : "updateMoviesViews",
		data : {
			movieId : id
		},

		success : function(response) {
			document.getElementById(id).setAttribute("onclick", "")
		}
	});
}
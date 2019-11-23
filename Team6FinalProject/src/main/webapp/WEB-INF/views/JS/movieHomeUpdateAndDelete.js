/**
 * 
 * function updateviews(id) {
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
 */

function movieHomeDelete(movieId) {
	
//	var movieId = "#movieId";
//	alert(movieId.get);
//	document.getElementById("deleteMovieId").value="movieId";
	var deleteMovieId = movieId;
//	alert (deleteMovieId);
	document.getElementById("deleteMovieId").value=deleteMovieId;
//	return movieId;
}

function movieHomeUpdate(movieId,movieName) {
	document.getElementById("deleteMovieId").value="movieId";
	document.getElementById("deleteMovieName").value="movieName";
}
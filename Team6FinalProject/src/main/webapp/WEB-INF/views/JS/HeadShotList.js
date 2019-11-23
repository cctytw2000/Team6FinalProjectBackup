//$(document).ready(function() {
//    $("#addnewimg").click(function(e) {
//
//        document.getElementById("addHeadShot").innerHTML = ' <input type="file" name="headshotImg" id="headshotImg" multiple="multiple">  \
//        	<button type="submit" class="btn btn-primary">新增大頭貼</button>'
//
//
//    });
//});

function deleteImg(id) {
	$.ajax({

		url : "deleteImg",
		data : {
			imgId : id
		},
		type : "POST",
		success : function(response) {
			location.reload();
		}
	});

}

$(document).ready(function() {
	$("#upfile1").click(function() {
		$("#headshotImg").trigger('click');

	});
	$("#headshotImg").change(function(e) {
		x = confirm("是否要上傳新的大頭照")
		if (x) {

		} else {
			return;
		}
		$("#addHeadShot").submit();

	});

});
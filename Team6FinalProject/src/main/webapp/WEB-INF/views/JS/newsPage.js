//一進入消息主頁-->照時間先後排序
$(document).ready(function() {
	$.ajax({
		url : "searchNewsByAjax.json",
		success : function(data) {
// console.log(data)
			var html = "";
			for (let i = 0; i < data.newsList.length; i++) {
				if (data.newsList[i].isVisable == true) {
					if((data.newsList[i].views) == null){
						data.newsList[i].views=0;
					}
					html += "<thead><tr><th colspan='2'>";
					html += (data.newsList[i].publicationDate).split(' ')[0];
					html += "</tr></thead><tbody><tr>";
					html += "<td style='text-align: center;'>";
					html += "<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newsList[i].newsId+ "'></a>";
					html += "<td><a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>"+ data.newsList[i].title+ "</a>";
					html += "<p>觀看人數:" + data.newsList[i].views + "</p>"
					html += "<p>"+ (data.newsList[i].article).substr(0, 100);
					html += " ...<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>繼續閱讀</a></p>";
					html += "</tr></tbody>";
				}
			}
			$("#newsOrderByTime").html(html);
		}
	});
	newsTypeSortOnLoad();
})

// 計觀看次數
function countView(newsId){
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

// 製造newsType下拉式按鈕
function newsTypeSortOnLoad(){
	$.ajax({
		url : "newsBack/searchNewsTypeByAjax",
		success : function(data) {
			const newsTypeIdList = Object.values(data).map(item => item.newsTypeId);
			const newsTypenameList = Object.values(data).map(item => item.newsTypeName);
// alert("成功")
			var html = "";
			for (let i = 0; i < data.length; i++) {
				var id = newsTypeIdList[i]; 
	            var name = newsTypenameList[i]; 
	            html += "<a class='dropdown-item' onclick='newsTypeSort("+ id +")'>"+name+"</a>";
				}
// console.log(html)
			$("#newsTypeSort").html(html);
		}
	});
}

// 照新聞類別排序
function newsTypeSort(id){
// alert(id)
	$.ajax({
		url : "searchNewsByAjax.json",
		success : function(data) {
// console.log(data)
			let html = "";
			for (let i =0 ; i < data.newsList.length; i++) {
				if(data.newsList[i].newsType.newsTypeId == id){
					if (data.newsList[i].isVisable == true) {
						if((data.newsList[i].views) == null){
							data.newsList[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.newsList[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newsList[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>"+ data.newsList[i].title+ "</a>";
						html += "<p>觀看人數:" + data.newsList[i].views + "</p>"
						html += "<p>"+ (data.newsList[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				}
			}
			$("#newsOrderByTime").html(html);
		}
	});
}

// 照觀看人數高低排序
function viewsSort(id){
// alert(id)
	$.ajax({
		url : "searchNewsByAjax.json",
		success : function(data) {
// console.log(data)
			var html = "";
			if(id == "low"){
				for (let i = data.newses.length-1; i >= 0; i--) {
					if (data.newses[i].isVisable == true) {
						if((data.newses[i].views) == null){
							data.newses[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.newses[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newses[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'>"+ data.newses[i].title+ "</a>";
						html += "<p>觀看人數:" + data.newses[i].views + "</p>"
						html += "<p>"+ (data.newses[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				} 
			} else {
				for (let i =0 ; i < data.newses.length; i++) {
					if (data.newses[i].isVisable == true) {
						if((data.newses[i].views) == null){
							data.newses[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.newses[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newses[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'>"+ data.newses[i].title+ "</a>";
						html += "<p>觀看人數:" + data.newses[i].views + "</p>"
						html += "<p>"+ (data.newses[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.newses[i].newsId+")' href='newsDetail?newsId="+ data.newses[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				}
			}
			$("#newsOrderByTime").html(html);
		}
	});
}

// 照消息發佈先後順序排序
function timeSort(id){
// alert(id)
	$.ajax({
		url : "searchNewsByAjax.json",
		success : function(data) {
// console.log(data)
			var html = "";
			if(id == "old"){
				for (let i = data.newsList.length-1; i >= 0; i--) {
					if (data.newsList[i].isVisable == true) {
						if((data.newsList[i].views) == null){
							data.newsList[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.newsList[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newsList[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>"+ data.newsList[i].title+ "</a>";
						html += "<p>觀看人數:" + data.newsList[i].views + "</p>"
						html += "<p>"+ (data.newsList[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				} 
			} else {
				for (let i =0 ; i < data.newsList.length; i++) {
					if (data.newsList[i].isVisable == true) {
						if((data.newsList[i].views) == null){
							data.newsList[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.newsList[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.newsList[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>"+ data.newsList[i].title+ "</a>";
						html += "<p>觀看人數:" + data.newsList[i].views + "</p>"
						html += "<p>"+ (data.newsList[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.newsList[i].newsId+")' href='newsDetail?newsId="+ data.newsList[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				}
			}
			$("#newsOrderByTime").html(html);
		}
	});
}

function searchByKeyWord(e){
	let keyWord =$("#keyWord").val();
	if(e.keyCode == 13){
	$.ajax({
		url: "searchByKeyWord",
		type: "POST",
		data: {
			keyWord: keyWord,
		},
		success: function (data) {
			console.log(data);
			let html = "";
			if (data.news.length > 0) {
				for(let i = 0; i < data.news.length; i++){
					if (data.news[i].isVisable == true) {
						if((data.news[i].views) == null){
							data.news[i].views=0;
						}
						html += "<thead><tr><th colspan='2'>";
						html += (data.news[i].publicationDate).split(' ')[0];
						html += "</tr></thead><tbody><tr>";
						html += "<td style='text-align: center;'>";
						html += "<a onclick='countView("+data.news[i].newsId+")' href='newsDetail?newsId="+ data.news[i].newsId +"'><img width='200' height='200' src='getNewsPicture/"+ data.news[i].newsId+ "'></a>";
						html += "<td><a onclick='countView("+data.news[i].newsId+")' href='newsDetail?newsId="+ data.news[i].newsId +"'>"+ data.news[i].title+ "</a>";
						html += "<p>觀看人數:" + data.news[i].views + "</p>"
						html += "<p>"+ (data.news[i].article).substr(0, 100);
						html += " ...<a onclick='countView("+data.news[i].newsId+")' href='newsDetail?newsId="+ data.news[i].newsId +"'>繼續閱讀</a></p>";
						html += "</tr></tbody>";
					}
				}
				$("#newsOrderByTime").html(html);
			}
			else {
				let html1 = "";
				html1 += "<thead><tr><th>無相關消息</tr></thead>";
				$("#newsOrderByTime").html(html1);
			}
		}
	});
	 }
}


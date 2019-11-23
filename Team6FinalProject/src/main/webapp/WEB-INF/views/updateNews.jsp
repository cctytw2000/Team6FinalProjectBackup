<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新消息</title>
<!-- 	套版用 -->
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/CSS/bootstrap.min.css'
	type="text/css" />
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/CSS/font-awesome.min.css'
	type="text/css" />
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/CSS/owl.carousel.css'
	type="text/css" />
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/CSS/style.css' type="text/css" />
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/CSS/animate.css'
	type="text/css" />
<%-- <script src="${pageContext.request.contextPath}/JS/membersBack.js"></script> --%>
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<!-- 	//套版用 -->
<!-- 自訂js設定檔  -->
<script src="${pageContext.request.contextPath}/JS/updateNews.js"></script>
<style>
.modal {
  position: absolute;
  float: left;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<jsp:include page="header/manageHeader.jsp" />

	<div class="container mt-3">
		<h1>更新消息資料</h1>
		<form action="${pageContext.request.contextPath}/updateNewsDetail"
			method="POST" enctype="multipart/form-data">
			<input type=hidden name="newsId" id="newsId" value="${news.newsId }">
			<p>
				消息類別:<span id="newsType" onload="showNewsType()"
					style="display: none">${news.newsType.newsTypeId }</span>
			
			<p style="margin-bottom: 0;">
				<span id="gameDetail1" style="display: none">原遊戲細節:</span><span
					id="gameDetail2" onload="showOriginalGameDetail()"
					style="display: none">${news.game.gameId}</span>
			<div id="showOriginalGame1"></div>
			<div class="row mb-2"></div>

			<p style="margin-bottom: 0;">
				<span id="activityDetail1" style="display: none">原活動細節:</span><span
					id="activityDetail2" onload="showOriginalActivityDetail()"
					style="display: none">${news.activity.activityId}</span>
			<div id="showOriginalActivity1"></div>
			<div class="row mb-2"></div>
			
			<p>
			<div style="display: none" onload="hasGame()" id="hasGame">
				是否需要更改或刪除遊戲細節:
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateGame()">更改</button>
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="deleteGame()">刪除</button>
			</div>
			
			<p>
			<div style="display: none" onload="hasActivity()" id="hasActivity">
				是否需要更改或刪除活動細節:
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateActivity()">更改</button>
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="deleteActivity()">刪除</button>
			</div>
			<p>
			
			<p>
			<div style="display: none" id="noGame">
				是否需要顯示遊戲細節: 
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="showGame()">是</button>
			</div>
			
			<p>
			<div style="display: none" id="noActivity">
				是否需要顯示活動細節: 
				<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="showActivity()">是</button>
			</div>

			<p>
				消息標題: <input name="title" type="text" id="title" style="width: 100%"
					value="${news.title }">
			<p>
				消息內容:
				<textarea class="form-control inline" name="article" id="article"
					rows="30" cols="100" style="resize: none;">${news.article }</textarea>
			<p>
				選擇圖片:<input id="newsImage" type="file" name="newsImage" />
			<p>
				<input type="submit" value="送出">
				<button type="button" onclick="GoBack()">取消</button>
		</form>
		
		<!-- 彈出式視窗		 -->
		<div id="ooo" class="modal fade" tabindex="-1" role="dialog"
			aria-hidden="true" data-backdrop="static" data-keyboard="true"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" style="width:1000px">
				<div class="modal-content">
					<div class="modal-header" id="xxx"></div>
					<div class="modal-body" id="xxx1"></div>
				</div>
			</div>
		</div>
		<!-- //彈出式視窗		 -->

	</div>
	<!-- 	套版用 -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
</body>
</html>
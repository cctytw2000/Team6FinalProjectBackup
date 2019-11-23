<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>消息內容</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 套版用 -->
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

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<!-- //套版用 -->
</head>
<body>
<body
	style="background-image: url(<c:url value='/Images/pattern.png' />)">
	<jsp:include page="header/homeHeader.jsp" />

	<!-- Latest news section -->
	<div class="latest-news-section">
		<div class="ln-title">熱門消息</div>
		<div class="news-ticker">
			<div class="news-ticker-contant">
				<c:forEach var="news" items="${sessionScope.newses }" begin="0"
					end="4">
					<div class="nt-item">
						<span class="strategy">${news.newsType.newsTypeName }</span><a
							onclick="countView(${news.newsId })"
							href="newsDetail?newsId=${news.newsId }">${news.title }</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- Latest news section end -->

	<div class="container"
		style="height:auto;background-image: url(<c:url value='/Images/pattern.png' />)">
		<div style="text-align: center">
			<div style="padding-top: 50px; padding-bottom: 10px">
				<h3 style="color: white">${news.title}</h3>
			</div>
			<div>
				<button type="button" class="btn btn-info pull-left">${news.newsType.newsTypeName}</button>
				<h5 style="color: pink; text-align: right">(Gamily專題記者:${news.member.username}報導)
					${news.publicationDate}</h5>
			</div>
			<hr style="border-bottom: 2px dashed #F8F8FF; margin-top: 30px">
			<div>
				<img width='400' height='400'
					src="<c:url value='/getNewsPicture/${news.newsId}' />">
			</div>
			<div>
				<p id="article"
					style="color: white; font-size: large; text-align: left">${news.article}</p>
			</div>
			<div id="gameDetail" style="text-align: left; display: none">
				<hr style="border-bottom: 2px dashed #F8F8FF">
				<h5 style="margin-bottom: 10px; color: GreenYellow">遊戲資訊</h5>
				<p id="gameId" style="display: none">${news.game.gameId}</p>
				<p style="color: GreenYellow">遊戲類型:${news.game.gameType.gameTypeName}</p>
				<p style="color: GreenYellow">遊戲名稱:${news.game.gameName}</p>
				<p id="gamePublicationDate" style="color: GreenYellow">遊戲發售日:${news.game.publicationDate}</p>
				<p style="color: GreenYellow">遊戲發行商:${news.game.publisher}</p>
				<p style="color: GreenYellow">遊戲平台:${news.game.platform}</p>
			</div>
			<div id="activityDetail" style="text-align: left; display: none">
				<hr style="border-bottom: 2px dashed #F8F8FF">
				<h5 style="margin-bottom: 10px; color: GreenYellow">活動資訊</h5>
				<p id="activityId" style="display: none">${news.activity.activityId}</p>
				<p style="color: GreenYellow">活動類型:${news.activity.activityType.activityTypeName}</p>
				<p style="color: GreenYellow">活動名稱:${news.activity.activityName}</p>
				<p id="startingDate_time" style="color: GreenYellow">活動日期:${news.activity.startingDate_time}</p>
				<p id="startingTime_date" style="color: GreenYellow">活動時間:${news.activity.startingTime_date}</p>
				<p id="startingDate" style="color: GreenYellow">活動起始日:${news.activity.startingDate}</p>
				<p id="endingDate" style="color: GreenYellow">活動結束日:${news.activity.endingDate}</p>
				<p style="color: GreenYellow">活動地點:${news.activity.location}</p>
			</div>
		</div>
		<hr style="border-bottom: 2px dashed #F8F8FF">
		
<!-- 		顯示評論 -->
		<c:forEach var="messages" items="${messagesList }" varStatus="i">
			<div id="memoForNews" class="media border p-3" style="width:600px;">
				<div class="media-body">
					<h4 style="color: #BBFFEE">${messages.member.username}
						<small style="margin-left:5%"><i>Posted on ${messages.publicationDate.replace(".0","")}</i></small>
					</h4>
					<p id="${messages.messageId }"style="color:#FFFFBB;margin-top:10px">${messages.memo }</p>
				</div>
			</div>
		</c:forEach>
		
<!-- 		輸入評論 -->
		<div class="form-group" style="margin:20px" id="add">
			<div class="input-group " style="width: 500px;">
				<input type="hidden" id="newsId" name="newsId"
					value="${news.newsId }"> <input type="hidden"
					id="member_id" value="${sessionScope.mem.member_id }">
				<textarea class="form-control" rows="1" id="addMemo" name="memo"
					style="resize: none;" placeholder="請輸入評論..."></textarea>
				<span class="input-group-btn">
					<button class="btn btn-success" onclick="addMemo()">發表</button>
				</span>
			</div>
		</div>
	</div>

	<!--背景底色 -->
	<section class="footer-top-section" style="height: 100%;">
		<div style="height: 378px" class="container"></div>
	</section>
	<!--//背景底色 -->

	<!--====== Javascripts & Jquery 套版用======-->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
	<!-- 自訂js設定檔  -->
	<script src="${pageContext.request.contextPath}/JS/newsDetail.js"></script>

</body>
</html>
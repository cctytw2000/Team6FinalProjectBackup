<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>消息後台</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
</head>
<body>
	<jsp:include page="header/manageHeader.jsp" />

	<div class="container mt-2" style="padding: 10px 10%">
		<h1 align="center">消息管理</h1>

		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#newsTypeHeader">消息類別</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#gameTypeHeader">遊戲類別</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#activityTypeHeader">活動類別</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#gameHeader">遊戲</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#activityHeaderOne">活動(一日)</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#activityHeaderMore">活動(多日)</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#newsHeaderShow">發佈消息</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#newsHeaderHide">隱藏消息</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#hotNewsTop5">截至今日的熱門消息前5</a></li>
		</ul>

		<div class="tab-content">

			<!-- 消息類別 -->
			<div id="newsTypeHeader" class="container tab-pane active">
				<nav class="navbar navbar-expand-sm ">
					<form class="form-inline" action="newsBack/addNewsType">
						新增消息類別:<input class="form-control mr-sm-2" type="text"
							name="newsTypeName"> <input class="btn btn-success"
							type="submit" value="送出">
					</form>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>消息類別編號
						<th>消息類別名稱
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="newsType"
								items="${newsTypeList }">
								<tr>
									<td>${newsType.newsTypeId }
									<td>${newsType.newsTypeName }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateNewsType('${newsType.newsTypeId }', '${newsType.newsTypeName }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteNewsType(${newsType.newsTypeId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //消息類別 -->

			<!-- 遊戲類別 -->
			<div id="gameTypeHeader" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<form class="form-inline" action="newsBack/addGameType">
						新增遊戲類別:<input class="form-control mr-sm-2" type="text"
							name="gameTypeName"> <input class="btn btn-success"
							type="submit" value="送出">
					</form>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>遊戲類別編號
						<th>遊戲類別名稱
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="gameType"
								items="${gameTypeList }">
								<tr>
									<td>${gameType.gameTypeId }
									<td>${gameType.gameTypeName }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateGameType('${gameType.gameTypeId }', '${gameType.gameTypeName }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteGameType(${gameType.gameTypeId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //遊戲類別 -->

			<!-- 活動類別 -->
			<div id="activityTypeHeader" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<form class="form-inline" action="newsBack/addActivityType">
						新增活動類別:<input class="form-control mr-sm-2" type="text"
							name="activityTypeName"> <input class="btn btn-success"
							type="submit" value="送出">
					</form>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>活動類別編號
						<th>活動類別名稱
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="activityType"
								items="${activityTypeList }">

								<tr>
									<td>${activityType.activityTypeId }
									<td>${activityType.activityTypeName }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateActivityType('${activityType.activityTypeId }', '${activityType.activityTypeName }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteActivityType(${activityType.activityTypeId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //活動類別 -->

			<!-- 遊戲 -->
			<div id="gameHeader" class="container tab-pane col">
				<nav class="navbar navbar-expand-sm ">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/newsBack/addGame"
						role="button">新增遊戲</a>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>遊戲編號
						<th>遊戲名稱
						<th>遊戲類別
						<th>遊戲發售日
						<th>遊戲發行商
						<th>遊戲平台
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="game"
								items="${gameList }">
								<tr>
									<td>${game.gameId }
									<td>${game.gameName }
									<td>${game.gameType.gameTypeName }
									<td>${game.publicationDate }
									<td>${game.publisher }
									<td>${game.platform }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateGame('${game.gameId }', '${game.gameName }', 
											'${game.gameType.gameTypeId }',' ${game.gameType.gameTypeName }', 
											'${game.publicationDate }', '${game.publisher }', '${game.platform }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteGame(${game.gameId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //遊戲 -->

			<!-- 一日活動 -->
			<div id="activityHeaderOne" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/newsBack/addActivity"
						role="button">新增活動</a>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>活動編號
						<th>活動名稱
						<th>活動類別
						<th>活動日期
						<th>活動時間
						<th>活動地點
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="activityOne"
								items="${activityOneList }">
								<tr>
									<td>${activityOne.activityId }
									<td>${activityOne.activityName }
									<td>${activityOne.activityType.activityTypeName }
									<td>${activityOne.startingDate_time }
									<td>${activityOne.startingTime_date }
									<td>${activityOne.location }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateActivityOne('${activityOne.activityId }', '${activityOne.activityName }',
											'${activityOne.activityType.activityTypeId }',' ${activityOne.activityType.activityTypeName }',
											'${activityOne.startingDate_time }', '${activityOne.startingTime_date }',
											'${activityOne.location }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteActivityOne(${activityOne.activityId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //一日活動 -->

			<!-- 多日活動 -->
			<div id="activityHeaderMore" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/newsBack/addActivity"
						role="button">新增活動</a>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>活動編號
						<th>活動名稱
						<th>活動類別
						<th>活動起始日
						<th>活動結束日
						<th>活動地點
						<th>更新 <!-- 						<th>刪除  --> <c:forEach var="activityMore"
								items="${activityMoreList }">
								<tr>
									<td>${activityMore.activityId }
									<td>${activityMore.activityName }
									<td>${activityMore.activityType.activityTypeName }
									<td>${activityMore.startingDate }
									<td>${activityMore.endingDate }
									<td>${activityMore.location }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="updateActivityMore('${activityMore.activityId }', '${activityMore.activityName }',
											'${activityMore.activityType.activityTypeId }',' ${activityMore.activityType.activityTypeName }',
											'${activityMore.startingDate }','${activityMore.endingDate }',
											'${activityMore.location }')">更新</button>
										<!-- 									<td><button type="button" class="btn btn-warning" -->
										<!-- 											data-toggle="modal" data-target="#ooo" --> <%-- 											onclick="deleteActivityMore(${activityMore.activityId })">刪除</button> --%>
							</c:forEach>
				</table>
			</div>
			<!-- //多日活動 -->

			<!-- 發佈消息 -->
			<div id="newsHeaderShow" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/newsBack/addNews"
						role="button">新增消息</a>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>消息編號
						<th>消息名稱
						<th>消息類別
						<th>發佈人
						<th>發佈日期
						<th>ip位置
						<th>隱藏 <c:forEach var="newsShow" items="${newsShowList }">

								<tr>
									<td>${newsShow.newsId }
									<td><a href="updateNews?newsId=${newsShow.newsId }">${newsShow.title }</a>
									<td>${newsShow.newsType.newsTypeName }
									<td>${newsShow.member.username }
									<td>${newsShow.publicationDate }
									<td>${newsShow.ipAddress }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="deleteNewsShow(${newsShow.newsId })">隱藏</button>
							</c:forEach>
				</table>
			</div>
			<!-- //發佈消息 -->
			<!-- 隱藏消息 -->
			<div id="newsHeaderHide" class="container tab-pane">
				<nav class="navbar navbar-expand-sm ">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/newsBack/addNews"
						role="button">新增消息</a>
				</nav>
				<table border="1" style="text-align: center; width: 100%">
					<tr>
						<th>消息編號
						<th>消息名稱
						<th>消息類別
						<th>發佈人
						<th>發佈日期
						<th>ip位置
						<th>發佈 <c:forEach var="newsHide" items="${newsHideList }">

								<tr>
									<td>${newsHide.newsId }
									<td><a href="updateNews?newsId=${newsHide.newsId }">${newsHide.title }</a>
									<td>${newsHide.newsType.newsTypeName }
									<td>${newsHide.member.username }
									<td>${newsHide.publicationDate }
									<td>${newsHide.ipAddress }
									<td><button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#ooo"
											onclick="reopenNews(${newsHide.newsId })">發佈</button>
							</c:forEach>
				</table>
			</div>
			<!-- //隱藏消息 -->

			<div id="hotNewsTop5" class="container tab-pane fade"
				style="width: 900px">
				<div class="chart-container"
					style="position: relative; height: 30vh; width: 50vw">
					<canvas id="chart"></canvas>
				</div>
			</div>



			<!-- 彈出式視窗		 -->
			<div id="ooo" class="modal fade" tabindex="-1" role="dialog"
				aria-hidden="true" data-backdrop="static" data-keyboard="true"
				aria-labelledby="exampleModalLabel">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header" id="xxx"></div>
						<div class="modal-body" id="xxx1"></div>
					</div>
				</div>
			</div>
			<!-- //彈出式視窗		 -->
		</div>
	</div>


	<!-- 	套版用 -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
	<!-- 自訂js設定檔  -->
	<script src="${pageContext.request.contextPath}/JS/newsBack.js"></script>
</body>
</html>
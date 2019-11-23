<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>最新消息</title>
<!-- 輪播 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- //輪播 -->

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

<style>
.btn-group {
	padding-left: 20px;
	padding-bottom: 20px;
}
</style>

</head>

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
						<span class="strategy">${news.newsType.newsTypeName }</span> <a
							onclick="countView(${news.newsId })"
							href="newsDetail?newsId=${news.newsId }">${news.title }</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- Latest news section end -->

	<div class="container-fluid ">
		<div class="row">

			<div id="newsWhite" class="col-2">
				<div class="form-group" style="margin-top: 20px">
					<div>
						<input type="text" class="form-control input-sm" name="keyWord" id="keyWord"
							placeholder="請輸入關鍵字..." onkeydown="searchByKeyWord(event)" >
						<p>
<!-- 						<div> -->
<!-- 						 	<button class="btn btn-success" onclick="searchByKeyWord()">搜尋</button> -->
<!-- 						</div> -->
					</div>
				</div>

				<div class="dropdown dropright">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown"
						style="margin-top: 10px; margin-left: 10px; margin-bottom: 20px">依熱門程度排序</button>
					<div id="viewsSort" class="dropdown-menu">
						<a class="dropdown-item" id="low" onclick="viewsSort(this.id)">由低到高</a>
						<a class="dropdown-item" id="High" onclick="viewsSort(this.id)">由高到低</a>
					</div>
				</div>

				<div class="dropdown dropright">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown"
						style="margin-left: 10px; margin-bottom: 20px">依新聞分類排序</button>
					<div id="newsTypeSort" onload="newsTypeSortOnLoad()" class='dropdown-menu'></div>
				</div>

				<div class="dropdown dropright">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown"
						style="margin-left: 10px; margin-bottom: 20px">依發佈時間排序</button>
					<div id="viewsSort" class="dropdown-menu">
						<a class="dropdown-item" id="old" onclick="timeSort(this.id)">由舊到新</a>
						<a class="dropdown-item" id="new" onclick="timeSort(this.id)">由新到舊</a>
					</div>
				</div>

			</div>

			<div id="newsCenter" class="col-6">
				<table
					style="border: none; text-align: left; color: white; width: 100%; table-layout: fixed; word-wrap: break-word;"
					id="newsOrderByTime">
				</table>
			</div>
			<div id="newsAD" class="col-4">
				<%-- 				<img style="padding-top: 7px;padding-left: 15%" src="${pageContext.request.contextPath}/Images/AD/廣告11.PNG"> --%>
				<div style="padding-top: 7px; padding-left: 15%" id="circleContent"
					class="carousel slide" data-ride="carousel" data-interval="3000">
					<ol class="carousel-indicators">
						<li data-target="#circleContent" data-slide-to="0" class="active"></li>
						<c:forEach varStatus="i" begin="1" end="4">
							<li data-target="#circleContent" data-slide-to="${i.index }+1"></li>
						</c:forEach>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<a href="#"><img
								src="${pageContext.request.contextPath}/Images/AD/AD1.PNG"></a>
						</div>
						<c:forEach varStatus="i" begin="1" end="4">
							<div class="carousel-item">
								<a href="#"><img
									src="${pageContext.request.contextPath}/Images/AD/AD${(i.index)+1 }.PNG"></a>
							</div>
						</c:forEach>
					</div>
				</div>
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
	<script src="${pageContext.request.contextPath}/JS/newsPage.js"></script>


</body>
</html>
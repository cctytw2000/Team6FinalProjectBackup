<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- moviepersonal.jsp -->
<html>

<head>
<meta charset="UTF-8">

<title>後台影片管理</title>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script>
	$(function() {
		$(".flip").click(function() {
			$(".panel").slideToggle("slow");
			$(".xs1").toggle();
			$(".xs2").toggle();
		});
	});
</script>
<title>title</title>
<style>
.label1 {
	color: white;
}

.sex {
	color: white;
}

.flip {
	background: #E09697;
	cursor: pointer;
	font-family: 'Arial';
}

.panel {
	/* 	margin: 0px; */
	/* 	text-align: center; */
	background: #e5eecc;
	display: none;
	font-family: 'Arial';
}

.footer_div {
	height: 30px;
}
</style>
</head>

<body
	style="height: 100%;background-image: url(<c:url value='/Images/pattern.png'  />)">
	<!-- 底色設定 循環至....... -->

	<jsp:include page="header/manageHeader.jsp" />

	<!-- 1footer-top-section -->
	<section class="footer-top-section" style="height: 100%;">
		<!-- div center -->
		<div style="text-align: center" class="container">

			<!-- Recent game   影片區-->
			<div class="section-title">
				<div class="row">
					<!-- <div class="cata new"> this is a pink TAG </div> -->
					<h2>影片區</h2>
				</div>
				<div class="row">
					<c:forEach var="allmovies" items="${allmovies}">
						<div class="col-lg-4 col-md-6">
							<div class="recent-game-item">
								<div class="rgi-thumb-video set-bg">
									<!--標籤 -->
									<div class="cata racing">racing</div>
									<video width="320" height="240" class="set-video"
										controls="controls"
										poster=<c:url value='/Images/video-Bg.jpg'  />>
										<source
											src="<c:url value='/Movie/${allmovies.location_Test}'/>"
											type="video/mp4">
									</video>
								</div>
								<div class="rgi-content">
									<!--標題 -->
									<h5>${allmovies.name }</h5>
									<!--路徑 -->
									<h6>${allmovies.location_Test }</h6>
									<!--內文 -->
									<p>${allmovies.movie_content}</p>
									<!-- Time -->
									<p>${allmovies.time}</p>
									<a href="moviepersonal/viewUpdateMovie?movie_ID=${allmovies.movie_ID }"  class="commentUpdate" >Update</a>
									<!-- href="moviepersonal/updateMovie" -->
									
									<%-- onclick="window.location.href='moviepersonal/updateMovie?movie_ID=${allmovies.movie_ID }'" --%>
									<a href="moviepersonal/deleteMovie?movie_ID=${allmovies.movie_ID }" class="commentDelete">Delete</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
			<!-- Recent game   End 影片區-->

			<!-- New Video 新增表單   ROW  -->
			<div class="row">

				<!-- New Video panel 新增內容 -->
				<div class="col-md-6">


					<!-- tournament-item -->
					<div class="tournament-item mb-4 mb-lg-0">

						<!-- ti-content -->
						<div class="ti-content">

							<!-- 	ti-text-form -->
							<div class="ti-text-form" id="addMovie">

								<!-- action 寫入目標 controller func 注意  action={設定為 (jsp name) +/+ (controller 原定func name)}=Mapping name -->
								<form style="background-color: white" method="POST"
									action="moviepersonal/addMovie" enctype="multipart/form-data">
									<p>
										影片標題: <input name="movie_name" type="text" size="50px" />
									<p>
										內文描述:
										<textarea name="movie_content"
											style="width: 400px; height: 200px;"></textarea>
									<p>
										選則檔案: <input type="file" name="video_file"><br />
									<p>
										
									<p>
										<input type="submit" value="送出"><br />
								</form>
							</div>
							<!-- 	ti-text-form -->

						</div>
						<!-- ti-content -->

					</div>
					<!-- 	tournament-item End  -->

				</div>
				<!-- New Video panel 新增內容 End  -->

			</div>
			<!-- New Video 新增表單 End  ROW -->

		</div>
		<!-- div center End-->

		<div class="footer_div"></div>

	</section>
	<!-- 1footer-top-section End-->








	<%--     <div style="height:auto;background-image: url(<c:url value='/Images/pattern.png'  />)"> --%>
	<!--     </div> -->
	<!-- 底色設定 End -->

	<!--    Footer Section  -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
	<%-- 	<jsp:include page="footer/homeFooter.jsp" /> --%>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- movieindex.jsp -->
<html>

<head>
<meta charset="UTF-8">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<title>title</title>

</head>

<body
	style="height: 100%;background-image: url(<c:url value='/Images/pattern.png'  />)">

	<jsp:include page="header/homeHeader.jsp" />

	<!-- 	Page Info Section -->
	<!--     <section class="container-fluid"> -->
	<!--             <div > -->
	<!-- 	            <video playsinline="playsinline" autoplay="autoplay"  loop="loop"> -->
	<!-- 	                <source src="https://storage.googleapis.com/coverr-main/mp4/Mt_Baker.mp4" type="video/mp4"> -->
	<!-- 	            </video> -->
	<!-- 	            想顯示在展示影片上的文字 -->
	<!-- 	            <div class="container h-100"> -->
	<!-- 	              <div class="d-flex h-100 text-center align-items-center"> -->
	<!-- 	                 <div class="w-100 text-white"> -->
	<!-- 	                   <h1 class="display-3">Video Header</h1> -->
	<!-- 	                   <p class="lead mb-0">With HTML5 Video and Bootstrap 4</p> -->
	<!-- 	                 </div> -->
	<!-- 	              </div> -->
	<!-- 	            </div> -->
	<!-- 			</div>   -->
	<!--     </section> -->
	<!-- 	Page Info Section End -->



	<section style="background-color: black" style="width:100%;height:100%">
		<video style="height: 100%; width: 100%" playsinline="playsinline"
			autoplay="autoplay" muted="muted" loop="loop">
			<source
				src='https://storage.googleapis.com/coverr-main/mp4/Mt_Baker.mp4'
				type="video/mp4">
		</video>
	</section>



	<!-- Recent game section  影片區-->
	<section class="recent-game-section spad set-bg"
		data-setbg="Images/recent-game-bg.png">
		<div class="container">
			<div class="section-title">
				<div class="cata new">new</div>
				<h2>Recent Games</h2>
			</div>
			<div class="row">
				<c:forEach var="movie" items="${movies}">
					<div class="col-lg-4 col-md-6">
						<div class="recent-game-item">
							<!-- 標籤 -->

							<div class="rgi-thumb set-bg">
								<div class="cata racing">racing</div>
								<video width="320" height="240" class="set-video"
									poster="${pageContext.request.contextPath}/Images/video-Bg.jpg" playsinline="playsinline"
									controls="controls">
									<source src="<c:url value='/Movie/${movie.location_Test}'/>"
										type="video/mp4">


								</video>
							</div>
							<div style="margin-top: 50px;" class="rgi-content">
								<!-- 							標題 -->
								<h6>${movie.location_Test }</h6>
								<h5>${movie.name }</h5>
								<!-- 							內文 -->
								<p>${movie.movie_content}.</p>
								<!-- 							案讚數 -->
								<a href="#" class="comment">Like</a>

<!-- 								<div class="rgi-extra"> -->
<!-- 									<div class="rgi-heart"> -->
<!-- 										<img src="Images/icons/heart.png" alt="heart.png"> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>

						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Recent game section  End-->




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
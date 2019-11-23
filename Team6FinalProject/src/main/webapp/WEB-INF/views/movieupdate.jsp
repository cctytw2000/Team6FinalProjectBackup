<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- movieupdate.jsp -->
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

	<!-- Recent game section  影片區-->
	<section class="recent-game-section spad set-bg"
		data-setbg="Images/recent-game-bg.png">
		<div class="container">

			<div class="section-title">
				<div class="cata new">new</div>
				<h2>Video Detail</h2>
			</div>

			<!-- New Video 修改表單 End  ROW -->
			<div class="row">

				<c:forEach var="movieInfo" items="${movieInfo}">
					<!-- New Video panel 修改內容 -->
					<div class="col-md-6">

						<!-- tournament-item -->
						<div class="tournament-item mb-4 mb-lg-0">

							<!-- ti-content -->
							<div class="ti-content" >

								<!-- ti-text-form -->
								<div class="ti-text-form" >
								<!-- id="updateMovie" -->
									<form style="background-color: white" method="POST"
										action="updateMovie"  enctype="multipart/form-data">
										<!-- 	method="POST" 對應 controller 的@RequestMapping( method = RequestMethod.POST) -->
										<!--    action="movieupdate/update" 對應 controller 的@RequestMapping(  value = "/moviepersonal/addMovie" ) -->
										
										<input type="hidden" name="movie_ID" value="${movieInfo.movie_ID }" />
										<input type="hidden" name="member_id" value="${movieInfo.getMember().getMember_id() }" />
										<p>
											影片標題: <input name="movie_name" type="text" size="50px"
												value="${movieInfo.name }" />
										<p>
											內文描述:
											<textarea name="movie_content"
												style="width: 400px; height: 200px;"
												>${movieInfo.movie_content}</textarea>
										<p>
											選則檔案: <input type="file" name="video_file" value="${movieInfo.location_Test }" />${movieInfo.location_Test }<br />
										<p>
											<input type="submit" value="送出"><br />
									</form>
								</div>

							</div>
							<!-- ti-content -->

						</div>
						<!-- 	tournament-item End  -->

					</div>
					<!-- New Video panel 修改內容 End-->

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
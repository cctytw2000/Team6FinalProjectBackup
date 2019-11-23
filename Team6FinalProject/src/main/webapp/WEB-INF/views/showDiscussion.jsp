<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>遊戲討論區</title>
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
</head>

<body>
	<jsp:include page="header/homeHeader.jsp" />
	<div align="center">
		<!-- Page section -->
		<section class="page-section recent-game-page spad">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<div>
							<table style="width: 100%">
								<!-- 								<tr> -->
								<!-- 									<th>看板名稱</th> -->
								<!-- 									<th style="text-align: right">篇數</th> -->
								<!-- 								</tr> -->
								<c:forEach var='boardType' items="${boardTypeList}">

									<tr class="row" style="margin-bottom:15px;">

										<td class="col-md-5"><a href="<spring:url value='board-Rich?id=${boardType.boardId}'/>"><img width="300" height="90" alt="${boardType.boardName}"
											src="<c:url value='/getBoardImage/${boardType.boardId}' />"></a>
											 </td>
										<td class="col-md-7"><a style="text-decoration: none;"
											href="<spring:url value='board-Rich?id=${boardType.boardId}'/>">${boardType.boardName}</a>
											<p>
												<c:forEach var='discussion' begin="0" end="0"
													items="${boardType.discussion}">

													<a style="text-decoration: none;"
														href="<spring:url value='article?id=${discussion.articleId}'/>">【${discussion.subjectType.subjectName}】${discussion.subject}</a>
													<p>
<!-- 													<div style="padding-left:15px"> -->
<%-- 														<span><i id="eye" class="fas fa-eye"></i> ${boardType.boardViews}</span>&nbsp;&nbsp;<span><i --%>
<%-- 															class="fas fa-list-alt"></i> ${boardType.discussion.size()}</span> --%>
<!-- 													</div> -->

												</c:forEach>
												<div style="padding-left:15px">
														<span><i id="eye" class="fas fa-eye"></i> ${boardType.boardViews}</span>&nbsp;&nbsp;<span><i
															class="fas fa-list-alt"></i> ${boardType.discussion.size()}</span>
													</div>
												</td>
										<%-- 										<td class="col-md-1">${boardType.discussion.size()}</td> --%>

									</tr>

								</c:forEach>
							</table>
						</div>

					</div>


					<!-- sidebar -->
					<div class="col-md-4 sidebar pt-5 pt-lg-0">
						<!-- widget -->

						<!-- widget -->
						<div class="widget-item">
							<h4 class="widget-title">最新發表!!</h4>
							<div class="latest-blog">
							
							<c:forEach var="discussion" items="${sessionScope.articleLatest3}" varStatus="i"
								begin="0" end="2">
							
							
								<div class="lb-item">
									<div class="lb-thumb set-bg"></div>
									<div class="lb-content">
										<div class="lb-date">發表時間  ${discussion.postTimeStamp}</div>
										<a href="article?id=${discussion.articleId}"><p>【${discussion.subjectType.subjectName}】 ${discussion.subject}</p></a>
										<div><img width="50" height="50" src="<c:url value='/memberImages/${discussion.member.account}_${discussion.member.member_id}/${discussion.member.username}${discussion.member.member_id}${discussion.member.headshot}' />">By ${discussion.member.memberdetail.nickname}</div>
									</div>
								</div>
								
								</c:forEach>
								
								
							</div>
						</div>
						<!-- widget -->
						<div class="widget-item">
							<div class="feature-item set-bg" data-setbg="img/features/1.jpg">
								<span class="cata new">new</span>
								<div class="fi-content text-white">
									<h5>
										<a href="#">Suspendisse ut justo tem por, rutrum</a>
									</h5>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									</p>
									<a href="#" class="fi-comment">3 Comments</a>
								</div>
							</div>
						</div>
						<!-- widget -->
						<div class="widget-item">
							<div class="review-item">
								<div class="review-cover set-bg" data-setbg="img/review/1.jpg">
									<div class="score yellow">9.3</div>
								</div>
								<div class="review-text">
									<h5>Assasin’’s Creed</h5>
									<p>Lorem ipsum dolor sit amet, consectetur adipisc ing
										ipsum dolor sit ame.</p>
								</div>
							</div>
						</div>
					</div>








				</div>

			</div>
	</div>
	</section>
	<!-- Page section end -->

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
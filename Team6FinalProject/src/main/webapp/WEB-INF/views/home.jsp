<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- home.jsp -->
<html>

<head>
<link href="./favicon.ico" rel="shortcut icon">
<meta charset="UTF-8">
<title>Gamily</title>

<!-- 	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script> -->
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


<script src="https://kit.fontawesome.com/685268963f.js"></script>
<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/JS/login.js"></script>
<script src="${pageContext.request.contextPath}/JS/RegisteredMember.js"></script>
<script
	src="${pageContext.request.contextPath}/JS/FBGoogleRegistered.js"></script>
<script src="${pageContext.request.contextPath}/JS/FBGoogleLogin.js"></script>


</head>

<body>
	<jsp:include page="header/homeHeader.jsp" />


	<!-- Hero section -->
	<!-- 	<section class="hero-section"> -->

	<section style="background-color: black" style="width:100%;height:100%">
		<!-- homeMovie movie_Id == 1  -->
		<video style="height: 100%; width: 100%" playsinline="playsinline"
			autoplay="autoplay" muted="muted" loop="loop">
			<source src='<c:url value='/Movie/${homeMovie.movie.movieName}' />'
				type="video/mp4">
		</video>
	</section>




	<!-- 	</section> -->
	<!-- Hero section end -->
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

	<!-- Footer top section -->
	<section class="footer-top-section">
		<div class="container">
			<div class="footer-top-bg">
				<img src="Images/footer-top-bg.png" alt="">
			</div>
			<div class="row">
				<div class="col-lg-4">
					<div class="footer-logo text-white">
						<img src="Images/newLogoFooter.png" alt="">
						<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum
							dolor sit ame.</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="footer-widget mb-5 mb-md-0">
						<h4 class="fw-title">
							人氣排行 <a style="font-size: 10px" href="${pageContext.request.contextPath}/discussion">看更多...</a>
						</h4>
						<div class="latest-blog">
						
							<c:forEach var="boardtype" items="${sessionScope.boardTop5}" varStatus="i"
								begin="0" end="7">
							<div class="lb-item">
								<div class="lb-thumb set-bg"
									data-setbg="<c:url value='/getBoardImage/${boardtype.boardId}' />"></div>
									
								<div class="lb-content">
									<div class="lb-date">${boardtype.boardName}</div>
									<p>累積人氣：${boardtype.boardViews}</p>
									<a href="<spring:url value='board-Rich?id=${boardtype.boardId}'/>" class="lb-author">討論區</a>
								</div>
							</div>
							
							</c:forEach>
							
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="footer-widget">
						<h4 class="fw-title">熱門文章</h4>
						<div class="top-comment">

								<c:forEach var="discussion" items="${sessionScope.articleTop6}" varStatus="i"
								begin="0" end="5">
									<div class="tc-date"><a href="article?id=${discussion.articleId}"><p style="color:white;">【${discussion.subjectType.subjectName}】 ${discussion.subject}</p></a></div>
								
								<div class="tc-item">
								<div class="tc-thumb set-bg" data-setbg="<c:url value='/memberImages/${discussion.member.account}_${discussion.member.member_id}/${discussion.member.username}${discussion.member.member_id}${discussion.member.headshot}' />"></div>
								
								
								
								<div class="tc-content">
									<p>
										<div style="color:white"> ${discussion.member.memberdetail.nickname}</div> <span style="color:white">於</span> <a href="<spring:url value='board-Rich?id=${discussion.boardType.boardId}'/>" style="color:#FF44AA">${discussion.boardType.boardName}看板</a>
									</p>
									<div class="tc-date">發文日期：${discussion.postTimeStamp}</div>
									postTimeStamp
								</div>
							</div>
				
								</c:forEach>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer top section end -->

	<!-- Feature section -->
	<section class="feature-section spad">
		<div class="container">
			<div class="section-title">
				<button type="button" class="btn btn-danger"
					onclick="window.location.href='newsPage'">最新消息</button>
				<h2>熱門新聞</h2>
			</div>
			<div class="row">
				<c:forEach var="news" items="${sessionScope.newses }" varStatus="i"
					begin="0" end="3">
					<div class='col-lg-3 col-md-6 p-0'>
						<div class='feature-item set-bg'
							data-setbg="<c:url value='/getNewsPicture/${news.newsId}' />">
							<span class='cata strategy'>${news.newsType.newsTypeName}</span><br>
							<h5 class='cata text-white' style="margin-left:0">
									<a style="font-size:20px" onclick='countView(${news.newsId})'
										href='newsDetail?newsId=${news.newsId }'>${news.title }</a>
								</h5>
							<div class='fi-content text-white'>
								
								<span id="${i.index}">${news.article}</span> <span>...<a
									onclick='countView(${news.newsId})'
									href='newsDetail?newsId=${news.newsId }'>繼續閱讀</a>
								</span>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Feature section end -->


	<!-- Recent game section  -->
	<section class="recent-game-section spad set-bg"
		data-setbg="Images/recent-game-bg.png">
		<div class="container">
			<div class="section-title">
				<div class="cata new">New</div>
				<h2>New Viedio</h2>
			</div>
			<div class="row">
			
			<c:forEach var="newMovie" items="${newMovies}" begin="0" end="2">
					<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg">
							<video id="${newMovie.movie_ID}" onclick="homeupdateviews('${newMovie.movie_ID}')" width="320" height="240" class="set-video"
									poster="${pageContext.request.contextPath}/Images/video-Bg.jpg"
									playsinline="playsinline" controls="controls">
									<source src="<c:url value='/memberMovies/${newMovie.member.account}${newMovie.member.member_id}/${newMovie.movie_ID}${newMovie.location_Test}'/>"
										type="video/mp4">
								</video>
								
<!-- 								<script type="text/javascript"> 
// 										確認source 構成
// 										console.log('/memberMovies/');
// 										console.log('${newMovie.member.account} == '${newMovie.member.account});
// 										console.log('${newMovie.member.member_id} == '${newMovie.member.member_id});
// 										console.log('   /   ');
// 										console.log('${newMovie.movie_ID} == '${newMovie.movie_ID});
// 										console.log('${newMovie.location_Test} == '${newMovie.location_Test});
									</script> -->
									
							<div class="cata new">new</div>
						</div>
						<div class="rgi-content">
							<h5>${newMovie.name }</h5>
							<p>${newMovie.movie_content}</p>
							<a href="#" class="comment">觀看次數：${newMovie.click_Sum}</a>
							<div class="rgi-extra">
								<div class="rgi-star">
									<img src="Images/icons/star.png" alt="">
								</div>
								<div class="rgi-heart">
									<img src="Images/icons/heart.png" alt="">
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
		
				
				
				
<!-- 				
				<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg"
							data-setbg="Images/recent-game/2.jpg">
							<div class="cata racing">racing</div>
						</div>
						<div class="rgi-content">
							<h5>Susce pulvinar metus nulla, vel facilisis sem</h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum
								dolor sit amet, consectetur elit.</p>
							<a href="#" class="comment">3 Comments</a>
							<div class="rgi-extra">
								<div class="rgi-star">
									<img src="Images/icons/star.png" alt="">
								</div>
								<div class="rgi-heart">
									<img src="Images/icons/heart.png" alt="">
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				
				<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg"
							data-setbg="Images/recent-game/3.jpg">
							<div class="cata adventure">Adventure</div>
						</div>
						<div class="rgi-content">
							<h5>Suspendisse ut justo tem por, rutrum</h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum
								dolor sit amet, consectetur elit.</p>
							<a href="#" class="comment">3 Comments</a>
							<div class="rgi-extra">
								<div class="rgi-star">
									<img src="Images/icons/star.png" alt="">
								</div>
								<div class="rgi-heart">
									<img src="Images/icons/heart.png" alt="">
								</div>
							</div>
						</div>
					</div>
				</div> -->
			</div>
		</div>
	</section>
	<!-- Recent game section end -->

	<!-- Review section -->
	<section class="review-section spad set-bg" data-setbg="#">
		<div class="container">
			<div class="section-title">
				<button type="button" class="btn btn-danger"
					onclick="window.location.href='findProductsByPage'">遊戲商城</button>
				<h2>最新商品</h2>
			</div>
			<div class="row">
				<c:forEach var="p" items="${sessionScope.productsTop8 }">
					<div class="col-lg-3 col-md-6" style="margin-top: 15px;">
						<div class="review-item">
							<div class="review-cover set-bg"
								data-setbg="<c:url value='/getPicture/${p.game_id}' />">
								
								<div class="score yellow" style="margin-top: 0px">$${p.price}</div>
							</div>
							
							<div class="review-text">
								<h5>
									<a href="<spring:url value='product?game_id=${p.game_id }'/>">${p.name }</a>
								</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Review section end -->

	<jsp:include page="footer/homeFooter.jsp" />
	<!-- 自訂js設定檔  -->
	<script src="${pageContext.request.contextPath}/JS/home.js"></script>


</body>


</html>
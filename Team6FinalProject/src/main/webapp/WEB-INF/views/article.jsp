<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>遊戲討論區</title>
<style>
body {
	background-image: url("Images/community-bg.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</style>
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


<script type="text/javascript">
	$(document).ready(function() {
		$("#clickMeLogin").click(function() {
			$("#loginButton").trigger('click');

		});

	});
</script>

<style type="text/css">
body {
	background-image: url("Images/community-bg.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</style>
</head>

<body>
	<jsp:include page="header/homeHeader.jsp" />
	<div align="center" style="height: 100%">
		<h2>
			<a
				href="<spring:url value='board-Rich?id=${discussion.boardType.boardId}'/>">${discussion.boardType.boardName}</a>
		</h2>
		<section>

			<div class="container">
				<h2 style="text-align: left">【${discussion.subjectType.subjectName}】
					${discussion.subject}</h2>
				<ul class="community-post-list">
					<li style="text-align: left">
						<div class="community-post">
							<div class="author-avator set-bg" style="width: 10%;">
								<img style="margin-right: 10px; padding-right: 20px"
									width="85px" height="70px"
									src="<c:url value='/memberImages/${discussion.member.account}_${discussion.member.member_id}/${discussion.member.username}${discussion.member.member_id}${discussion.member.headshot}' />">
							</div>

							<div class="post-content">

								<h5>${discussion.member.memberdetail.nickname}<span
										style="font-size: 8px">${discussion.member.account.split("@")[0]}</span>
								</h5>
								<div class="post-date">${discussion.postTimeStamp}</div>
								<p>${discussion.articleBody}</p>
							</div>

							<span style="color: white; float: right">閱覽次數：${discussion.views}</span>
						</div>
					</li>


					<c:forEach var="rp" items="${reply}" varStatus="s">
					
					
					
					
					
					
					
					
						<li style="text-align: left">
						<div class="community-post">
							<div class="author-avator set-bg" style="width: 10%;">
								<img style="margin-right: 10px; padding-right: 20px"
									width="85px" height="70px"
									src="<c:url value='/memberImages/${rp.member.account}_${rp.member.member_id}/${rp.member.username}${rp.member.member_id}${rp.member.headshot}' />">
							</div>

							<div class="post-content">

								<h5>${rp.member.memberdetail.nickname}<span
										style="font-size: 8px">${rp.member.account.split("@")[0]}</span>
								</h5>
								<div class="post-date"> ${rp.postTimeStamp}</div>
								<p>${rp.replyBody}</p>
							</div>

						</div>
					</li>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
<!-- 						<li> -->
<!-- 							<h5> -->
<%-- 								回覆者 ${rp.member.memberdetail.nickname}<span --%>
<!-- 									style="font-size: 8px"> -->
<%-- 									${rp.member.account.split("@")[0]}</span> --%>
<!-- 							</h5> -->


<!-- 							<div class="post-date"> -->
<%-- 								<p>時間 ${rp.postTimeStamp}</p> --%>
<!-- 							</div> -->
<%-- 							<p>${rp.replyBody}</p> --%>


<!-- 						</li> -->


					</c:forEach>




					<c:choose>
						<c:when test="${sessionScope.mem != Null }">
							<li>
								<div align="center">
									<form method='POST'
										action="${pageContext.request.contextPath}/addReply"
										enctype="multipart/form-data">

										<input type="hidden" name="articleId"
											value="${discussion.articleId}" /> <input type="hidden"
											name="author"
											value="${sessionScope.mem.memberdetail.nickname}" />

										<table>
											<tr>
												<td></td>
												<td>
													<div class="post-content">
														<h5 style="color: white">
															<img style="margin-right: 10px; padding-right: 20px"
																width="85px" height="70px"
																src="<c:url value='/memberImages/${mem.account}_${mem.member_id}/
										${mem.username}${mem.member_id}${mem.headshot}' />">${sessionScope.mem.memberdetail.nickname}
															<span style="font-size: 8px">${sessionScope.mem.account.split("@")[0]}</span>
														</h5>
													</div>
												</td>
											</tr>
											<tr>
												<td></td>
												<td><textarea rows="5" name="body" cols="100"></textarea></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="submit" value="送出">
													<button type="button" onclick="GoBack()">取消</button></td>
											</tr>
										</table>
									</form>
								</div>
							</li>


						</c:when>
						<c:otherwise>
							<div align="center">
								<button id="clickMeLogin" class="btn btn-primary" type="button">點我登入</button>
							</div>
						</c:otherwise>
					</c:choose>


				</ul>






			</div>
		</section>
	</div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論看板</title>
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
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<!-- 	//套版用 -->
</head>
<body>
	<jsp:include page="header/homeHeader.jsp" />
	
	<div align="center">
	<h1>看板名稱:${boardType.boardName}</h1>
	
	<a style="text-decoration:none;" href="<spring:url value='addArticle?id=${boardType.boardId}&name=${boardType.boardName}'/>">發表文章</a>
	<a style="text-decoration:none;" href="<spring:url value='board-Rich?id=${boardType.boardId}'/>">縮圖版</a>
<table>
	<tr><th>標題</th><th>作者</th><th>人氣</th><th>回文</th><th>發表時間</th></tr>
	<c:forEach var='DiscussionList' items="${DiscussionList}">
		<tr>
			<td><a style="text-decoration:none;" href="<spring:url value='article?id=${DiscussionList.articleId}'/>">【${DiscussionList.subjectType.subjectName}】  ${DiscussionList.subject}</a></td>
			<td><a style="text-decoration:none;" href="<spring:url value='member?id=${DiscussionList.member.member_id}'/>">${DiscussionList.member.memberdetail.nickname}</a></td>
			<td>${DiscussionList.views}</td>
			<td>${DiscussionList.reply.size()}</td>	
			<td>${DiscussionList.postTimeStamp}</td>	
		</tr>
	</c:forEach>
</table>
</div>
		<!-- 	套版用 -->
		<script
			src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
		<script src="${pageContext.request.contextPath}/JS/main.js"></script>
		<!-- 自訂js設定檔  -->
		<script src="${pageContext.request.contextPath}/JS/newsBack.js"></script>
</body>
</html> 
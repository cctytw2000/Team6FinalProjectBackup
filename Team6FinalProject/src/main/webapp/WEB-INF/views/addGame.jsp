<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>新增遊戲</title>
</head>
<body>
	<jsp:include page="header/manageHeader.jsp" />
	<div class="container mt-3">
		<h1>新增遊戲資料</h1>
		<form:form
			action="${pageContext.request.contextPath}/newsBack/addGame1"
			method="POST" modelAttribute="game">
			<p>
				遊戲類別:
				<form:select path="gameType_">
					<form:option value="-1">請挑選</form:option>
					<form:options items="${gameTypeMap }"></form:options>
				</form:select>
			<p>
				遊戲名稱:
				<form:input path="gameName" type="text" />
			<p>
				發行日期:
				<form:input id="datepicker" name="publicationDate"
					autocomplete="off" path="publicationDate" type="text" value="" />
			<p>
				發行商名稱:
				<form:input path="publisher" type="text" />
			<p>
				發行平台:
				<form:input path="platform" type="text" />
			<p>
				<input type="submit" value="送出">
				<button type="button" onclick="GoBack()">取消</button>
		</form:form>
	</div>

	<!-- 	套版用 -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>

	<!-- 	datepicker plugin設定檔 -->
	<link rel="stylesheet"
		href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
	<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/addGame.js"></script>
</body>
</html>
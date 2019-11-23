<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區後台</title>
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
	<jsp:include page="header/manageHeader.jsp" />

	<div class="container mt-3">
		<h1 align="center">新增討論看板</h1>
		<div align="center">
	<form:form method='POST' modelAttribute="boardType" enctype="multipart/form-data">
		<form:input path="boardName" type="text" size="50px" />
		<form:input path="bImage" type='file'/>
		<p>
			<input type="submit" value="送出">
			<button type="button" onclick="GoBack()">取消</button>
	</form:form>
			</div>
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
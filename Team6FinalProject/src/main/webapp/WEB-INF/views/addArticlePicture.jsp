<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function GoBack() {
		history.go(-1)
	}
</script>
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
<script src="${pageContext.request.contextPath}/JS/membersBack.js"></script>
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<!-- 	//套版用 -->
<title>新增消息圖片</title>
</head>
<body>
	<jsp:include page="header/manageHeader.jsp" />
	<div class="container mt-3">
		<h1>新增消息資料</h1>
		<form
			action="${pageContext.request.contextPath}/newsBack/addArticlePicture"
			method="POST" enctype="multipart/form-data">
			選擇圖片:<input id="newsImage" type="file" name="newsImage" />
			<div class="row mb-2"></div>
			<%-- 	選擇次要圖片:<form:input path="picture" type="file" /> --%>
			<!-- 		<p> -->
			<%-- 	選擇次要圖片:<form:input path="picture" type="file" /> --%>
			<!-- 		<p> -->
			<%-- 	選擇主要圖片:<form:input path="picture" type="file" /> --%>
			<!-- 		<p> -->
			<input type="submit" value="送出">
			<button type="button" onclick="GoBack()">回上一步</button>
		</form>
	</div>
	<!-- 	套版用 -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
</body>
</html>
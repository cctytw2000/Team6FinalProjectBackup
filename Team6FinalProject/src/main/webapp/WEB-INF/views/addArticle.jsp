<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>討論區</title>
<script>
	function GoBack() {
		history.go(-1)
	}
</script>
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

	<div class="container mt-3">
		<h1 align="center">張貼文章</h1>
		<div align="center">
			<form method='POST'
				action="${pageContext.request.contextPath}/addArticle"
				enctype="multipart/form-data">

				<input type="hidden" name="boardId" value="${boardId}" /> <input
					type="hidden" name="author"
					value="${sessionScope.mem.memberdetail.nickname}" />
					
				<table>
					<tr>
						<td>作者</td>
						<td><b>${sessionScope.mem.memberdetail.nickname}</b></td>
					</tr>
					<tr>
						<td>種類</td>
						<td><select name="subjectTypeId">
						<c:forEach var="subjectType" items="${subjectType}">
						
								<option value="${subjectType.subjectTypeId}">${subjectType.subjectName}</option>
						
						
						</c:forEach></select>
						</td>
					</tr>
					<tr>
						<td>主題</td> 
						<td><input type="text" name="subject" size="30px" /></td>
					</tr>

					<tr>
						<td>
						<td><textarea rows="20" name="body" cols="100"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="送出">
							<button type="button" onclick="GoBack()">取消</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 	套版用 -->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
	<!-- 自訂js設定檔  -->
	<script src="${pageContext.request.contextPath}/JS/newsBack.js"></script>
</body>

</html>
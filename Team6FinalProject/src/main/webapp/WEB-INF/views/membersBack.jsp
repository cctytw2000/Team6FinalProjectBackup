<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員後台</title>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<script src="https://kit.fontawesome.com/685268963f.js"></script>
</head>
<body style="background-image: url(<c:url value='/Images/pattern.png' />)">

    <jsp:include page="header/manageHeader.jsp" />

	<section class="" style="height: 100%;">
		<div align="center"
			style=" width: 60%; margin: 15px auto">
			<h1 style="color:white">會員後台</h1>
			<table border="1" border="1" style="color:white;text-align: center; width: 100%">


				<tr>
					<th>會員編號</th>
					<th>會員帳號</th>
					<th>帳號類型</th>
					<th>會員姓名</th>
					<th>帳號創建日期</th>
					<th>會員狀態</th>
					<th>更改狀態</th>
				</tr>
				<tbody id="memberInfo"></tbody>
<%-- 				<c:forEach var="member" items="${Memners}"> --%>
<!-- 					<tr> -->

<!-- 						<td><a -->
<%-- 							href="<spring:url value='member?id=${member.member_id }'/>">${member.member_id }</a> --%>
<%-- 						<td>${member.account }</td> --%>
<%-- 						<td>${member.type }</td> --%>
<%-- 						<td>${member.username }</td> --%>
<%-- 						<td>${member.registeredtime.replace(".0","")}</td> --%>


<%-- 						<c:choose> --%>
<%-- 							<c:when test="${member.isactive == 0}"> --%>
<!-- 								<td style="color: red">封鎖</td> -->
<%-- 								<td><button onclick="openActive('${member.member_id}','${member.type}')" type="button">開通</button></td> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<td style="color: green">開通</td> -->
<%-- 								<td><button onclick="closeActive('${member.member_id}','${member.type}')" type="button">封鎖</button></td> --%>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 				</c:forEach> --%>
				
			</table>
			
			        <div class="chart-container" style="position: relative; height:20vh; width:40vw">
                <canvas id="chart"></canvas>
            </div>
		</div>
	</section>
    <!--====== Javascripts & Jquery ======-->
    <script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/main.js"></script>
<%-- 	<jsp:include page="footer/footer.jsp" /> --%>
</body>
</html>
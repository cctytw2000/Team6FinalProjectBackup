<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" -->
<!-- 	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
<title>會員資訊</title>






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



<script src="${pageContext.request.contextPath}/JS/memberBack.js"></script>





</head>

<body
	style="background-image: url(<c:url value='/Images/pattern.png' />)">
	<jsp:include page="header/manageHeader.jsp" />


	<div style="height: 100%;">
		<section class="container">
			<div class="row" style="padding: 50px 15%">

				<div class="container mt-3">
					<h2>會員後台</h2>
					<br>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#home">帳號資料</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#menu1">會員資料</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#menu2" onclick="loginInfo('${member.member_id}');">會員登入登出資訊</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div id="home" class="container tab-pane active">
							<br>
							<h5 style="color: white; font-size: 23px">
								帳號ID:${member.member_id}

								<form action="${pageContext.request.contextPath}/memberBack/change" method="POST">
									<table id="accountInfo" border="1" style="width: 100%; text-align: center">
										<tbody>
											<tr>
												<td>帳號</td>
												<td>${member.account}</td>
											</tr>
											<tr>
												<td>會員姓名</td>
												<td>${member.username}</td>

											</tr>
											<tr>
												<td>會員註冊日期</td>
												<td>${member.registeredtime.replace(".0","")}</td>
											</tr>
											<tr id="level">
												<td>會員身分</td>
												<td>${member.memberlevel.levelName}<button id="openupdate" type="button" onclick="openUpdate('${member.memberlevel.levelName}')">編輯</button></td>

											</tr>
										</tbody>
									</table>
									<input type="hidden" name="memberId" value="${member.member_id}">

								</form>

							</h5>
						</div>
						<div id="menu1" class="container tab-pane fade">
							<br>
							<h5 style="color: white; font-size: 23px">
								帳號ID:${member.member_id}
								
								<table id="accountInfo" border="1" style="width: 100%; text-align: center">
										<tbody>
											<tr>
												<td>身分證字號:</td>
												<td>${member.memberdetail.idnumber}</td>
											</tr>
											<tr>
												<td>性別</td>
												<c:choose>
												<c:when test="${member.memberdetail.sex == 'male'}">
												<td>男</td>
												</c:when>
												<c:otherwise>
												<td>女</td>
												</c:otherwise>
												</c:choose>
												

											</tr>
											<tr>
												<td>電話</td>
												<td>${member.memberdetail.tel.substring(0,4)}-${member.memberdetail.tel.substring(4,7)}-${member.memberdetail.tel.substring(7)}</td>
											</tr>
											<tr>
												<td>地址</td>
												<td>${member.memberdetail.address}</td>

											</tr>	
											<tr>
												<td>生日</td>
												<td>${member.memberdetail.birth}</td>

											</tr>
										</tbody>
									</table>
								
								
								
								
								
								
								
								
								
<!-- 								<div style="margin-top: 30px"> -->
<!-- 									<p style="color: pink">身分證字號: -->
<%-- 										${member.memberdetail.idnumber}</p> --%>
<%-- 									<p style="color: pink">性別: ${member.memberdetail.sex}</p> --%>
<%-- 									<p style="color: pink">電話: ${member.memberdetail.tel}</p> --%>
<%-- 									<p style="color: pink">地址: ${member.memberdetail.address}</p> --%>
<%-- 									<p style="color: pink">生日:${member.memberdetail.birth}</p> --%>

<!-- 								</div> -->
							</h5>
						</div>

						<div id="menu2" class="container tab-pane fade">
							<br>

							<h5 style="color: white; font-size: 23px">
								帳號ID:${member.member_id}




								<div style="margin-top: 30px">
									<table border="1" style="width: 100%">
									<thead>
										<tr>
											<th>請求IP</th>
											<th>請求類型</th>
											<th>時間</th>
											<th>帳號類型</th>
											<th>請求狀態</th>
										</tr>
										</thead>
										<tbody id="infoBody">

										</tbody>




									</table>
									<ul id="pageBottom" style="width: 40%; margin: 15px auto;"
										class="pagination">
									</ul>
								</div>
							</h5>
						</div>
					</div>
				</div>





			</div>


		</section>



	</div>




	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
	<%-- 	<jsp:include page="footer/homeFooter.jsp" /> --%>

</body>

</html>
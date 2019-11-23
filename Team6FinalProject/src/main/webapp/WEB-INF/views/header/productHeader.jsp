<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/bootstrap.min.css' type="text/css" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/font-awesome.min.css' type="text/css" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/owl.carousel.css' type="text/css" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/style.css' type="text/css" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/animate.css' type="text/css" />


<script src="https://kit.fontawesome.com/685268963f.js"></script>







<!-- Page Preloder -->
<div id="preloder">
	<div class="loader"></div>
</div>

<!-- Header section -->
<header class="header-section">
	<div class="container">
		<!-- logo -->
		<a class="site-logo" href="${pageContext.request.contextPath}">
			<!-- 			<img src="Images/logo.png" alt=""> -->
				<img src="<c:url value='/Images/newLogo.png' />" />
		</a>
		<c:choose>
			<c:when test="${sessionScope.account != Null}">
				<div class="user-panel">
					<span style="font-size: 18px" class="welcome">${sessionScope.username}
						您好</span>

				</div>
			</c:when>
			<c:otherwise>
				<div class="user-panel">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#login">登入</button>
					/
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#Register">註冊</button>
				</div>
			</c:otherwise>

		</c:choose>


		<!-- responsive -->
		<div class="nav-switch">
			<i class="fa fa-bars"></i>
		</div>
		<c:choose>
			<c:when test="${sessionScope.account != Null}">
				<nav class="navbar navbar-expand-lg navbar-dark">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link"
									href="${pageContext.request.contextPath}">首頁<span
										class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="#">最新消息</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">遊戲討論區</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">影片區</a></li>
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#"
										id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">商城管理</a>
										
										<div style="" class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" href="${pageContext.request.contextPath}/productsBack">商品管理</a>
												<a class="dropdown-item" href="${pageContext.request.contextPath}/#">訂單管理</a>
										</div>
								
							</li>
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#"
									id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> 會員中心 </a>

									
								<c:choose>
									<c:when test="${sessionScope.type != 'General'}">
										<div style="" class="dropdown-menu" aria-labelledby="navbarDropdown">
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/logout">登出</a>
										</div>
									</c:when>
									<c:otherwise>
										<div style="" class="dropdown-menu" aria-labelledby="navbarDropdown">
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a>
											<a class="dropdown-item" href="member/CheangePassword.jsp">修改密碼</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/logout">登出</a>
										</div>
									</c:otherwise>
								</c:choose>





								<div style="" class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a> <a
										class="dropdown-item" href="#">修改密碼</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="./LogOutMember.do">登出</a>
								</div>
							</li>

							<li class="nav-item"><a class="nav-link" href="manager">後台</a></li>

						</ul>
					</div>
				</nav>
			</c:when>
			<c:otherwise>
				<nav class="navbar navbar-expand-lg navbar-dark">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link" href="#">首頁<span
										class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="#">最新消息</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">遊戲討論區</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">影片區</a></li>
							<li class="nav-item"><a class="nav-link" href="products">商城</a></li>
						</ul>
					</div>
				</nav>
			</c:otherwise>
		</c:choose>
	</div>
</header>
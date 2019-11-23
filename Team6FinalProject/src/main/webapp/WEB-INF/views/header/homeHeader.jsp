<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

<%-- 	<script src="${pageContext.request.contextPath}/JS/login.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/JS/RegisteredMember.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/JS/FBGoogleRegistered.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/JS/FBGoogleLogin.js"></script> --%>



<script src="${pageContext.request.contextPath}/JS/RegisteredMember.js"></script>
<script
	src="${pageContext.request.contextPath}/JS/FBGoogleRegistered.js"></script>
<script src="${pageContext.request.contextPath}/JS/FBGoogleLogin.js"></script>


<!-- Page Preloder -->
<div id="preloder">
	<div class="loader"></div>
</div>

<!-- Header section -->
<header class="header-section">
	<div class="container">
		<!-- logo -->
		<a style="padding-top: 0" class="site-logo"
			href="${pageContext.request.contextPath}"> <!-- 			<img src="Images/logo.png" alt=""> -->
			<img src="<c:url value='/Images/newLogo.png' />" />
		</a>
		<c:choose>
			<c:when test="${sessionScope.mem.username != Null}">
				<div class="user-panel">
					<span style="font-size: 18px" class="welcome"> <c:choose>
							<c:when test="${sessionScope.mem.headshot != Null}">

								<img style="margin-right: 10px;" width="26px" height="26px"
									src="<c:url value='/memberImag
						es/${sessionScope.mem.account}_${sessionScope.mem.member_id}/${sessionScope.mem.username}${sessionScope.mem.member_id}${sessionScope.mem.headshot}' />">

							</c:when>
							<c:otherwise>

								<img width="26px" height="26px"
									src="<c:url value='/Images/noimage.jpg' />">

							</c:otherwise>

						</c:choose> ${sessionScope.mem.username} 您好
					</span>

				</div>
			</c:when>
			<c:otherwise>
				<div class="user-panel">
					<button id="loginButton" type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#login">登入</button>
					/
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#Register">註冊</button>
				</div>
			</c:otherwise>

		</c:choose>
		<div class="modal fade" id="Register" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLongTitle" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">註冊</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">


						<form action="${pageContext.request.contextPath}/member/register"
							method='POST' class='form-horizontal'
							enctype="multipart/form-data">

							<fieldset>


								<div class="form-group">
									<label for="account">帳號:</label> <input id="account"
										name="account" type='text' class="form-control"
										style="display: inline" autocomplete="off" /><i
										style="margin: -15px" id="check"
										class="fas fa-clipboard-check"></i><span
										style="margin-left: 30px" id="account_msg"></span>
									<h6 style="color: gray; font-size: 12px">
										(1.不可空白，2.至少六個字包含英文字母，數字，特殊字元(!@#$%^&*))</h6>


								</div>
								<div class="form-group">
									<label for="password">密碼:</label> <input id="password"
										name="password" type='password' class="form-control"
										style="display: inline" /><i style="margin: -20px"
										onclick="showHide();" id="eye" class="fas fa-eye"></i><span
										style="margin-left: 30px" id="msg_password"></span>



								</div>



								<div class="form-group">
									<label for="username">姓名:</label> <input id="username"
										name="username" type='text' class="form-control"
										style="display: inline" /><span id="msg"
										style="margin: -15px"></span>

									<h6 style="color: gray; font-size: 12px">(1.不可空白，2.至少兩個字以上，3.必須為中文字)</h6>
								</div>




								<input id="memberimg" name="memberimg" type='file'
									onchange="readURL(this)" /> <img
									id="preview_progressbarTW_img" width="212" height="250"
									src="<c:url value='/Images/noimage.jpg' />">

								<p>新增一張大頭貼</p>








								<div style="width: 90%; margin-left: 30px">
									<button type="button" style="background-color: #0066FF"
										class="btn btn-facebook" onclick="FBRestistered();">
										<i class="fab fa-facebook-f fa-lg"></i> Facebook註冊
									</button>
									<button type="button" style="background-color: #FF3333"
										class="btn btn-google" onclick="GoogleRestistered();">
										<i class="fab fa-google-plus-g fa-lg"></i> Google註冊
									</button>
								</div>

								<script async defer src="https://apis.google.com/js/api.js"
									onload="this.onload=function(){};HandleGoogleApiLibrary()"
									onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>

								<div id="submit" class="modal-footer" style="text-align: center">
									<button class="btn btn-primary" type="submit">註冊</button>
									<button class="btn btn-secondary" type="reset">清除</button>
								</div>

							</fieldset>
						</form>



					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="login" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLongTitle" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">登入</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="${pageContext.request.contextPath}/member/login"
							method='POST' class='form-horizontal'>
							<fieldset>
								<div class="form-group">
									<label for="usr">帳號:</label> <input id="loginaccount"
										name="loginaccount" type='text' class="form-control" />




								</div>
								<div class="form-group">
									<label for="pwd">密碼:</label> <input id="loginpassword"
										name="loginpassword" type='password' class="form-control" /><span
										id="passowrd_msg"></span>
								</div>
								<div style="width: 90%; margin-left: 30px">
									<button name="FaceBook" type="button"
										style="background-color: #0066FF" class="btn btn-facebook"
										onclick="FBLogin();">
										<i class="fab fa-facebook-f fa-lg"></i> Facebook登入
									</button>
									<button name="Google" type="button"
										style="background-color: #FF3333" class="btn btn-google"
										onclick="GoogleLogin();">
										<i class="fab fa-google-plus-g fa-lg"></i> Google登入
									</button>
								</div>
								<script async defer src="https://apis.google.com/js/api.js"
									onload="this.onload=function(){};HandleGoogleApiLibrary()"
									onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>



								<div id="submit" class="modal-footer" style="text-align: center">
									<input name="login" class="btn btn-primary" type="submit"
										value="登入"> <input class="btn btn-secondary"
										name="login" type="submit" value="忘記密碼">
								</div>


							</fieldset>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-- responsive -->
		<div class="nav-switch">
			<i class="fa fa-bars"></i>
		</div>
		<c:choose>
			<c:when test="${sessionScope.mem.username != Null}">
				<nav class="navbar navbar-expand-lg navbar-dark">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.request.contextPath}">首頁<span
									class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/newsPage">最新消息</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/discussion">遊戲討論區</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/movies">影片區</a></li>
							<!-- 								<li class="nav-item"><a class="nav-link" href="products">商城</a></li> -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">商城</a>

								<div style="" class="dropdown-menu"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/findProductsByPage">所有商品</a>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/showCart">購物車</a> <a
										class="dropdown-item"
										href="${pageContext.request.contextPath}/showOrder">我的訂單</a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> 會員中心 </a> <c:choose>
									<c:when test="${sessionScope.mem.type != 'General'}">

										<div style="" class="dropdown-menu"
											aria-labelledby="navbarDropdown">
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/PhotoList">照片清單</a>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/movies">影片清單</a>


											<div class="dropdown-divider"></div>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/logout">登出</a>
										</div>


									</c:when>
									<c:otherwise>


										<div style="" class="dropdown-menu"
											aria-labelledby="navbarDropdown">
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/PhotoList">照片清單</a>
													<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/movies">影片清單</a>
<!-- 											<a class="dropdown-item" -->
<%-- 												href="${pageContext.request.contextPath}/member/sendChangePassWordMail" >修改密碼</a> --%>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/member/logout">登出</a>
										</div>


									</c:otherwise>

								</c:choose>





								<div style="" class="dropdown-menu"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/member/memberdetail">會員資料</a>
									<a class="dropdown-item" href="#">修改密碼</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="./LogOutMember.do">登出</a>

								</div></li>










							<c:choose>
								<c:when
									test="${sessionScope.mem.memberlevel.levelName == 'admin'}">

									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/manager">後台</a></li>
								</c:when>
								<c:otherwise>
								</c:otherwise>

							</c:choose>

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
							<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/newsPage">最新消息</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/discussion">遊戲討論區</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/movies">影片區</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/findProductsByPage">商城</a></li>
						</ul>
					</div>
				</nav>
			</c:otherwise>
		</c:choose>
	</div>
</header>

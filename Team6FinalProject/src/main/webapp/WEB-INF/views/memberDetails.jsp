<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>會員資料</title>
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


<script src="${pageContext.request.contextPath}/JS/memberDetail.js"></script>

<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
<script src="https://kit.fontawesome.com/685268963f.js"></script>




</head>

<body>
	<jsp:include page="header/homeHeader.jsp" />


	<section class="footer-top-section"
		style="height: auto; padding-top: 15px">
		<div
			style="width: 30%; height: 100%; background-color: white; border-radius: 15px 15px;"
			class="container">

<!-- margin: 15px 25%; -->


			<c:choose>
				<c:when test="${sessionScope.mem.headshot != Null}">


					<img data-toggle="modal" data-target="#${sessionScope.mem.username}${memberheadshot.id}" style="cursor: pointer;margin: 15px 25%;" id="previewHeadShot" width="212" height="250"
						src="<c:url value='/memberImag
						es/${sessionScope.mem.account}_${sessionScope.mem.member_id}/${sessionScope.mem.username}${sessionScope.mem.member_id}${sessionScope.mem.headshot}' />">


      <div class="modal fade" id="${sessionScope.mem.username}${memberheadshot.id}"
                                        tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                        aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">瀏覽我的大頭貼</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <img style="width:50%;margin:5px 100px" height="250"
                                                        src="<c:url value='/memberImag
						es/${sessionScope.mem.account}_${sessionScope.mem.member_id}/${sessionScope.mem.username}${sessionScope.mem.member_id}${sessionScope.mem.headshot}' />"">









                                                </div>
                                                <div class="modal-footer">

                                                    <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>












<%-- 					<button onclick="headshot('${sessionScope.mem.member_id}')" --%>
<!-- 						type="button">換一張大頭貼吧</button> -->
<%-- 					<form id="Changeheadshot" --%>
<%-- 						action="${pageContext.request.contextPath}/member/Changeheadshot" --%>
<%-- 						method="post" enctype="multipart/form-data"></form> --%>
				</c:when>
				<c:otherwise>

					<img style="margin: 15px 25%;" id="previewHeadShot" width="212" height="250"
						src="<c:url value='/Images/noimage.jpg' />">
<%-- 					<button onclick="headshot('${sessionScope.mem.member_id}')" --%>
<!-- 						type="button">新增一張大頭貼</button> -->
<%-- 					<form id="Changeheadshot" --%>
<%-- 						action="${pageContext.request.contextPath}/member/Changeheadshot" --%>
<%-- 						method="post" enctype="multipart/form-data"></form> --%>
				</c:otherwise>

			</c:choose>







				<p style="text-align: center; font-size: 30px; color: black">個人資料</p>
				<br>
				<div style="width: 100%; margin: 30px auto;">
		
					<div>
						<label>姓名：</label>
						<p>${MemberDetial.username}</p>
					</div>
			<form
				action="${pageContext.request.contextPath}/member/ChangeNickname"
				method="post">
							<input type="hidden" name="memberID"
						value="${MemberDetial.memberdetail.memberID}">
					<div id="nickname">
						<label>暱稱：</label> <br> <input
							style="border-width: 0; background-color: white" type="text"
							name="nickname" placeholder="來個新名字吧(ゝ∀･)" disabled="disabled"
							readonly="readonly" value="${MemberDetial.memberdetail.nickname}"></input>
						<button id="openupdate" type="button" onclick="openUpdate('${MemberDetial.memberdetail.nickname}')">編輯</button>
					</div>
			</form>

					<div>
						<label>信箱：</label>
						<p>${MemberDetial.account}</p>
					</div>

	<div>
						<label>密碼：</label>
						<p>
						<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
修改密碼
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">修改密碼</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      		<form
				action="${pageContext.request.contextPath}/member/sendChangePassWordPage"
				method="post">
      <div class="modal-body">
    	
				<div
					style="width: 70%; height: 70%;">
					<div class="form-group">
						<label for="account">帳號:</label> <input type="email"
							class="form-control" style="width:100%; margin: 0" id="account"
							name="account" aria-describedby="emailHelp"
							placeholder="Enter email"> <small id="emailHelp"
							class="form-text text-muted">請輸入當初您在註冊時所輸入的帳號</small>
					</div>

<!-- 					<button name="insert" type="submit" class="btn btn-primary">寄信</button> -->
				</div>
			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" name="insert" class="btn btn-primary">寄信</button>
      </div>
      </form>
    </div>
  </div>
</div>
						
						
						
						
						
						</p>
					</div>





					<div>
						<label>電話：</label>
						<p id="showTel">${MemberDetial.memberdetail.tel.substring(0,4)}-***-${MemberDetial.memberdetail.tel.substring(7)}</p>
						<button id="tel" type="button" onclick="showall('${MemberDetial.memberdetail.tel.substring(0,4)}-${MemberDetial.memberdetail.tel.substring(4,7)}-${MemberDetial.memberdetail.tel.substring(7)}','${MemberDetial.memberdetail.tel.substring(0,4)}-***-${MemberDetial.memberdetail.tel.substring(7)}')">查看</button>

					</div>




					<div>
						<label>生日：</label>
						<p>${MemberDetial.memberdetail.birth}</p>
					</div>
<!-- 					<div> -->
<!-- 						<label>身分證字號：</label> -->
<%-- 						<p>${MemberDetial.memberdetail.idnumber}</p> --%>
<!-- 					</div> -->

				</div>

		</div>

	</section>



	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>


</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購買成功</title>
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

</head>
<body>
<jsp:include page="header/manageHeader.jsp" />


<section class="footer-top-section" style="height: 100%;">
   <div style="height: 378px" class="container">

            <div style="width:70%;height:30%;margin:10% auto;text-align: center;">
                <h1 style="color:white;font-size:50px">購買成功</h1>
                <h3 style="line-height: 2.5em;color:white">
           			 謝謝惠顧
                </h3>
                <button type="button" class="btn btn-outline-warning"
										onclick="window.location.href='showOrder'">返回訂單列表</button>
            </div>
        </div>

    </section>





    <jsp:include page="footer/footer.jsp" />
<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script	src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
</body>
</html>
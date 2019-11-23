<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>後台</title>
<%--     <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}"> --%>


    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/bootstrap.min.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/font-awesome.min.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/owl.carousel.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/style.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/animate.css' type="text/css" />

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kit.fontawesome.com/685268963f.js"></script>





    <style>
        .label1 {
            color: white;
        }

        .sex {
            color: white;
        }
    </style>
</head>





<body style="background-image: url(<c:url value='/Images/pattern.png' />)">


    <jsp:include page="header/manageHeader.jsp" />



    <section class="footer-top-section" style="height: 100%;">
        <div style="text-align:center" class="container">



	<h1>後台管理</h1>
	<hr>

        </div>

    </section>





	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>










</body>

</html>






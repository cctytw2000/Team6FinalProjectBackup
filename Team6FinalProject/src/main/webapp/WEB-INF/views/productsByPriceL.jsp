<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" -->
<!-- 	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<title>所有商品</title>

<style>
/* footer { */
/* 	/* 設定footer的高度 */ */
/* 	height: 40px; */
/* 	box-sizing: border-box; */
/* 	/* 設定footer絕對位置在底部 */ */
/* 	position: absolute; */
/* 	bottom: 0; */
/* 	/* 展開footer寬度 */ */
/* 	width: 100%; */
/* } */
</style>
</head>

<body style="background-image: url(<c:url value='/Images/pattern.png' />)">

	<jsp:include page="header/homeHeader.jsp" />


	<!-- Latest news section -->
	<div class="latest-news-section">
		<div class="ln-title">重要消息</div>
		<div class="news-ticker">
			<div class="news-ticker-contant">

				<c:forEach var="product" items="${sessionScope.products }">

					<div class="nt-item">
						<span class="new">${product.category.category }</span><a
							href="<spring:url value='product?game_id=${product.game_id }'/>">${product.name }</a><span>
							只要NT ${product.price }元</span>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
	<!-- Latest news section end -->


	<!-- 		style="height: 1px; border: none; color: #333; background-color: #333;"> -->

	<div
		style="height:auto;background-image: url(<c:url value='/Images/pattern.png' />)">

		<nav class="navbar navbar-expand-sm " style="float: right">
			<form class="form-inline" action="getProductByKeyWord">
				<input class="form-control mr-sm-2" type="text" placeholder="請輸入關鍵字"
					name="keyWord">
				<button class="btn btn-success" type="submit">Search</button>
			</form>
		</nav>


		<div class="container" style="margin: 0px">
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle"
					data-toggle="dropdown" style="margin-top: 10px">商品分類</button>
				<div class="dropdown-menu">
					<c:forEach var="c" items="${categories }">
						<a class="dropdown-item"
							href="queryCategory?category_id=${c.category_id }">${c.category }</a>
					</c:forEach>
				</div>

			</div>
		</div>


		<div class="container" style="margin: 10px 0px">
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle"
					data-toggle="dropdown">價格排序</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="queryProductByLow">由低到高</a> 
					<a class="dropdown-item" href="queryProductByHigh">由高到低</a>
				</div>
			</div>
		</div>


		<section class="container">
			<c:choose>
				<c:when test="${empty pages.list }">
					<div align="center">
						<h3 style="color:white;">很抱歉搜尋不到您要的商品</h3>
						<img src="<c:url value='/Images/noproduct.png' />" />
					</div>
				</c:when>
			<c:otherwise>
			<div class="row">
				<c:forEach var="product" items="${pages.list }">
					<!-- 				<div class="col-sm-6 col-md-3" style="width: 360px; height: 360px"> -->
					<!-- 					<div class="thumbnail" style="width: 300px; height: 340px"> -->
					<!-- 					<div style="padding-left:25%"> -->
					<%-- 						<img width='150px' height='150px' src="<c:url value='/getPicture/${product.game_id}' />" /> --%>
					<!-- 						</div> -->
					<!-- 						<div class="caption"> -->
					<%-- 							<p align="center"style="max-width:280px;margin-top:10px"><a href="<spring:url value='product?game_id=${product.game_id }'/>" style="color:white">${product.name }</a></p> --%>
					<%-- 							<p align="center" style="color:pink">NT ${product.price }元</p> --%>
					<%-- 							<p align="center" style="color:pink">${product.category.category }</p> --%>
					<!-- 						</div> -->
					<!-- 					</div> -->
					<!-- 				</div> -->
					<div class="col-sm-3 " style="width: 400px; height: 500px;">
						<img class="card-img-top"
							src="<c:url value='/getPicture/${product.game_id}' />"
							alt="Card image" style="width: 90%">
						<div class="card-body" style="padding-left:0px">
							<h6 class="card-title">
								<a
									href="<spring:url value='product?game_id=${product.game_id }'/>"
									style="color: white">${product.name }</a>
							</h6>
							<p class="card-text" style="color: #FFFFBB">NT ${product.price }元</p>
							<a href="addToCart?game_id=${product.game_id }&count=1"
								class="btn btn-primary">加入購物車</a>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<ul class="pagination justify-content-center" style="background-color: none">
				<c:choose>
					<c:when test="${pages.currentPage == 1}">
						<li class="page-item disabled">
					</c:when>
					<c:otherwise>
						<li class="page-item">
					</c:otherwise>
				</c:choose>
					<a class="page-link" href="<spring:url value='queryProductByLow?currentPage=${pages.currentPage - 1}&rows=4'/>">Previous</a>
				</li>
				
					<c:forEach begin="1" end="${pages.totalPage }" var="i">
						
						<c:choose>
						<c:when  test="${pages.currentPage == i}">
							
							<li class="page-item active"><a class="page-link" href="<spring:url value='queryProductByLow?currentPage=${i}&rows=4'/>">${i}</a></li>
						</c:when>							
						
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="<spring:url value='queryProductByLow?currentPage=${i}&rows=4'/>">${i}</a></li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
				<c:choose>
						<c:when test="${pages.currentPage == pages.totalPage}">
							<li class="page-item disabled">
						</c:when>
						<c:otherwise>
							<li class="page-item">
						</c:otherwise>
				</c:choose>
					<a class="page-link" href="<spring:url value='queryProductByLow?currentPage=${pages.currentPage + 1}&rows=4'/>">Next</a>
				</li>
			</ul>
			
			</c:otherwise>
			</c:choose>

			

		</section>
	</div>
    <!--====== Javascripts & Jquery ======-->
    <script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/main.js"></script>
<%-- 	<jsp:include page="footer/homeFooter.jsp" /> --%>
</body>

</html>
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
<title>商品資料</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>


<script src="${pageContext.request.contextPath}/JS/product.js"></script>
<script type="text/javascript">

</script>
<style>
#edit{
cursor:pointer;

}
#edit:hover{
color:red

}
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

	<div
		style="height:auto;background-image: url(<c:url value='/Images/pattern.png' />)">
		<section class="container">
			<div class="row" style="padding: 50px 15%">
				<img width='200' height='200' style="float:left"
					src="<c:url value='/getPicture/${product.game_id}'/>" />
				<h5 style="color: white; font-size: 23px">${product.name }
					<div style="padding-left: 15px; margin-top: 30px">
						<p style="color: pink">商品分類: ${product.category.category}
						<p style="color: pink">發行商: ${product.publisher}
						<p style="font-size: 30px; color: white">
							售價:NT${product.price }元<span
								style="padding-left: 50px; color: white">庫存:${product.stock}</span>
					</div>
				</h5>

				<div>

					<p>
					<h4 style="color: pink">
						商品簡介:
						<p style="color: pink">${product.game_desc }
					</h4>
					<div style="float: right">
						<button type="button" class="btn btn-warning"
							onclick="window.location.href='findProductsByPage'">返回</button>
						<c:if test="${product.stock < 1}">
							<button disabled="disabled" class="btn btn-warning">商品售完</button>
						</c:if>
						<c:if test="${product.stock >= 1}">
						<a href='addToCart?game_id=${product.game_id }&count=1'
							class='btn btn-warning btn-large'> <span
							class='glyphicon-shopping-cart glyphicon'></span>加入購物車
						</a>
						</c:if>
					</div>
					</p>
				</div>

<div id="commentInfo">
<%-- 				<c:forEach var="c" items="${comments }"> --%>
<!-- 					<div class="media border p-3" style="width: 600px;"> -->

<!-- 						<div class="media-body"> -->
<%-- 							<h4 style="color: #BBFFEE">${c.member_name } --%>
<%-- 								<small style="margin-left:5%"><i>Posted on ${c.time.replace(".0","")}</i></small> --%>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${sessionScope.mem.username == c.member_name}"> --%>
<%-- 										<small><i id="edit" onclick="update('${c.comment_id}','${c.comment }','${product.game_id }')">編輯</i></small> --%>
<%-- 									</c:when> --%>
<%-- 								</c:choose> --%>
<!-- 							</h4> -->
<%-- 							<p id="${c.comment_id}" style="color: #FFFFBB;margin-top:10px">${c.comment }</p> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 				</c:forEach> --%>
</div>				
				<div class="form-group">
					<nav class="navbar navbar-expand-sm " style="padding-left: 0px">
						
							<input type="hidden" id="game_id" name="game_id" value="${product.game_id }">
							<input type="hidden" id="loginusername" value="${sessionScope.mem.username }">
							<textarea class="form-control" rows="1" id="addComment"
								name="comment" style="width: 600px" placeholder="請輸入評論..."></textarea>
							<button class="btn btn-success" onclick="addComment()">Comment</button>
						
					</nav>
				</div>


			</div>

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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
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
<script src="${pageContext.request.contextPath}/JS/productBack.js"></script>
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<title>商品資料</title>

<style>
#remove {
	cursor: pointer;
}

#remove:hover {
	color: red
}
</style>
</head>

<body
	style="background-image: url(<c:url value='/Images/pattern.png' />)">
	<jsp:include page="header/manageHeader.jsp" />



	<div style="height: auto;">
		<section class="container">
			<div class="row" style="padding: 50px 15%">
				<img width='200' height='200'
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
							onclick="window.location.href='<spring:url value='/productsBack'/>'">返回</button>
						<%-- 						<a href='products/update?game_id=${product.game_id }' class='btn btn-warning btn-large'> --%>
						<!-- 							<span class='glyphicon-shopping-cart glyphicon'></span>更改商品資訊 -->
						<!-- 						</a> -->
						<button type="button" class="btn btn-warning" data-toggle="modal"
							data-target="#exampleModal">更新商品資訊</button>
					</div>
					</p>
				</div>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">更新商品資訊</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form method="POST" action="updateProduct"
									enctype="multipart/form-data">
									<input type="hidden" name="game_id" value="${product.game_id }">
									<p>
										商品分類:<select name="category_id">
											<option value="${product.category.category_id }">${product.category.category }</option>
											<c:forEach var="c" items="${categoryMap}">
												<option value="${c.key }">${c.value }
											</c:forEach>
										</select>
									</p>
									<p>
										商品名稱: <input name="name" type="text" size="50px"
											value="${product.name }" />
									<p>
										廠商名稱: <input name="publisher" type="text" size="50px"
											value="${product.publisher}" />
									<p>
										商品價格: <input name="price" type="text"
											value="${product.price }" />
									<p>
										庫存數量: <input name="stock" type="text"
											value="${product.stock }" />
									<p>
										商品描述:
										<textarea name="game_desc"
											style="width: 400px; height: 200px;">${product.game_desc }</textarea>
									<p>
										選擇圖片: <input name="productImage" type="file" />
									<p style="float: right">
										<input type="submit" class="btn btn-success" value="更新">
								</form>
							</div>

						</div>
					</div>
				</div>

				<div id="commentInfo">
					<%--     				<c:forEach var="c" items="${comments }"> --%>
					<!--     					<div class="media border p-3" style="width:600px"> -->

					<!--     						<div class="media-body"> -->
					<%--      				 			<h4 style="color: #BBFFEE">${c.member_name }  --%>
					<%--      				 				<small style="margin-left:5%"><i>Posted on ${c.time.replace(".0","")}</i></small> --%>
					<%--      				 				<small><i id="remove" onclick="window.location.href='<spring:url value='/removeComment?game_id=${product.game_id}&comment_id=${c.comment_id}'/>'">刪除</i></small> --%>
					<!-- 								</h4> -->
					<%--       							<p id="${c.comment_id}" style="color: #FFFFBB;margin-top:10px">${c.comment }</p>       --%>
					<!--     						</div> -->
					<!--   						</div> -->
					<%--     				</c:forEach> --%>
				</div>


				<div class="form-group">
					<nav class="navbar navbar-expand-sm " style="padding-left: 0px">
						<form class="form-inline" action="addComment">
							<input type="hidden" id="game_id" name="game_id"
								value="${product.game_id }"> <input type="hidden"
								id="loginusername" value="${sessionScope.mem.username }">
							<textarea class="form-control" rows="1" id="comment"
								name="comment" style="width: 600px" placeholder="請輸入評論..."></textarea>
							<button class="btn btn-success" type="submit">Comment</button>
						</form>
					</nav>
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
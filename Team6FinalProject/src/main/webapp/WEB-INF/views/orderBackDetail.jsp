<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>訂單資料</title>
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
	<section class="" style="height: 100%;">
	<div class="container mt-3" style="width: 60%; margin: 15px auto">
	<div class="container tab-pane active" style="width:900px">	
	<h2 align="center">訂單編號${order.order_id}</h2>
	<table border="1" style="text-align: center; width: 100% ">
				<tr>
						<th>訂單時間</th>
						<th>訂單總金額</th>
						<th>狀態</th>						
				</tr>	
				<tr>
						<td>${order.ordertime.replace(".0","")}</td>
						<td>${order.total}元</td>						
					<c:choose>
    						<c:when test="${order.state==1}">
      						 <td style="color:red">未付款</td>
    						</c:when>
   						   <c:when test="${order.state==4}">
       						 <td>已付款</td>
   						   </c:when>    
					</c:choose>												
				</tr>					
	</table>
	<table border="1" style="text-align: center; width: 100% ">
				<tr>
						<th>商品編號</th>
						<th>商品名稱</th>
						<th>數量</th>
						<th>金額</th>
				</tr>		
				<c:forEach var="orderItem" items="${order.orderItems}">
					<tr>
						<td>${orderItem.product.game_id}</td>
						<td>${orderItem.product.name}</td>
						<td>${orderItem.count}個</td>
						<td>${orderItem.subtotal}元</td>
					</tr>
				</c:forEach>				
	</table>	
	</div>
	</div>
	</section>
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script	src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
</body>

</html>
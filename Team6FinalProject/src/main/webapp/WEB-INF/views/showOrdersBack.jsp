<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>所有訂單</title>
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

<div class="container mt-3" style="padding: 50px 10%">
	<h1 align="center">訂單管理</h1>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#showorders">訂單總覽</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#memberorder">會員訂單</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#productCancel">會員訂單</a></li>
	</ul>
<div class="tab-content">
	<div id="showorders" class="container tab-pane active" style="width:900px">
		<table  id="table1" border="1" style="text-align: center ">
				<tr><th>訂單編號<th>顧客名稱<th>email<th>商品名稱<th>商品小計			
				<c:choose>				
				<c:when test="${not empty orders}">
					<c:forEach var="order" items="${orders}">
					<tr><td>${order.order.order_id}
						<td>${order.order.member.username}
						<td>${order.order.member.account}
						<td>${order.product.name}						
						<td>${order.subtotal}元							
					</c:forEach>
				</c:when>
				<c:otherwise>
					查無訂單資料
				</c:otherwise>
				</c:choose>
		</table>
	</div>	
	<div id="memberorder" class="container tab-pane fade" style="width:900px">
		會員:<select id="member_id">
			<c:choose>	
				<c:when test="${not empty members}">
					<c:forEach var="m" items="${members}">
						<option value="${m.member_id}">${m.member_id} ${m.username} ${m.account} 
					</c:forEach>
				</c:when>
				<c:otherwise>
					查無會員資料
				</c:otherwise>
			</c:choose>
		</select>
		<p>		
		<div style="text-align: center">			
			<table  id="tbody1" border="1" style="text-align: center;float:left;width:50%">
			</table>
			<table  id="tbody2" border="1" style="text-align: center;float:right;width:50%">
			</table>			
		</div>			
		<script>		
			$(document).ready(function(){
				memberOrderajax();
				$("select#member_id").change(function() {					
					memberOrderajax();		
				});					
			});	
			function memberOrderajax(){
				$.ajax({
					url: "memberOrder/"+$("select#member_id").val()+".json",
					type: "GET",
					dataType: "json",						
					success: function (data) {							
						console.log(data.list);
						if(data.list.length!=0){
							creatTable1(data.list);
						}else{
							$("table#tbody1").html("查無訂單資料");							
						}
						$("table#tbody2").html();
					},
					error:function () {
		                alert("系統錯誤(ajax)");
		            }
				});
			}
			function creatTable1(data){				
				 var tableData="<tr><th>訂單編號<th>訂單時間<th>訂單金額<th>狀態";				
				 for(var i=0;i<data.length;i++){
					 tableData+="</tr>";					 
					 tableData+="<td>"+"<a onclick='OrderDetailajax("+data[i].order_id+")'>"+data[i].order_id+"</a>"+"</td>";
					 tableData+="<td>"+data[i].ordertime.replace(".0","")+"</td>";
					 tableData+="<td>"+data[i].total+"元</td>";
					 var state="";
					 switch(data[i].state){
						 case 1:							
							tableData+="<td style='color:red'>未付款</td>";
						 	break;
						 case 4:						
							tableData+="<td>已付款</td>";
							break;
					 }					 
					 tableData+="</tr>";
				 }				 			
				 $("table#tbody1").html(tableData);
			}
			function OrderDetailajax(orderid){
				$.ajax({
					url: "orderDeail/"+orderid+".json",
					type: "GET",
					dataType: "json",						
					success: function (data) {	
						console.log("t2");
						console.log(data.order);
						/*if(data.list.length!=0){
							creatTable2(data.list);
						}else{							
							$("table#tbody2").html("查無訂單資料");
						}*/
					},
					error:function () {
		                alert("系統錯誤(ajax)");
		            }
				});
			}
		</script>
			
	</div>
	<div id="productCancel" class="container tab-pane fade">
	</div>
</div>
</div>
   
<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script	src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>		
</body>
</html>
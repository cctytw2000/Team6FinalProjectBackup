<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>訂單後台</title>
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
<style>
.modal-dialog {
  min-height: calc(100vh - 60px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  overflow: auto;
  @media(max-width: 768px) {
    min-height: calc(100vh - 20px);
  }
}
</style>

<script src="https://kit.fontawesome.com/685268963f.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/ordersBack.js"></script>
</head>
<body>
	<jsp:include page="header/manageHeader.jsp" />
	<section class="" style="height: 100%;">
	<div class="container mt-3" style="width: 60%; margin: 15px auto">
	<h1 align="center">訂單管理</h1>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#showorders">訂單總覽</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#orderchart">會員訂單統計</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#dailySalesAmount">每日銷售金額</a></li>
	</ul>
	<div class="tab-content">
	<div id="showorders" class="container tab-pane active" style="width:900px">
				<nav class="navbar navbar-expand-sm ">	
					<div class="form-inline">				
						<label for="member_id">會員編號:</label>						
						<input class="form-control mr-sm-2" type="text"	id="member_id">
						<span id="err_msg"></span>
						<span style="margin-left: 30px">	
							<input type="checkbox" id="money_1" name="money_1" value="1" checked><label for="money_1" style="display:inline; color:red;">未付款</label>	 
							<input type="checkbox" id="money_4" name="money_4" value="4" checked><label for="money_4" style="display:inline; color:green;">已付款</label> 								
						</span>
					</div>
				</nav>
			<div id="accordion">
				<div class="card">
					<div class="card-header">
						<table border="1" style="text-align: center; width: 100%">
							<tr>
								<th>訂單編號</th>
								<th>顧客名稱</th>
								<th>email</th>					
								<th>訂單時間</th>
								<th>訂單金額</th>
								<th>狀態</th>						
								<th>取消</th>										
							</tr>	
							<tbody id="ordersInfo"></tbody>
						</table>
					</div>					
				</div>
			</div>
		<div  id="totalspan" style="width: 30%; margin: 15px auto;text-align: center"></div>
		<ul id="pageBottom" style="margin: 0 auto;" class="pagination justify-content-center">
		</ul>
	</div>
	<div id="orderchart" class="container tab-pane fade" style="width:900px">
		<div class="chart-container" style="position: relative; height:30vh; width:50vw">
                <canvas id="chart"></canvas>
    	</div>
	</div>	
	<div id="dailySalesAmount" class="container tab-pane fade" style="width:900px">
		<div class="chart-container" style="position: relative; height:30vh; width:50vw">
                <canvas id="chart1"></canvas>
    	</div>
	</div>
	</div>
	</div>
	</section>		
	<!-- 彈出式視窗		 -->
			<div id="mwin" class="modal fade" tabindex="-1" role="dialog"
				aria-hidden="true" data-backdrop="static" data-keyboard="true"
				aria-labelledby="exampleModalLabel">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header" id="mheader"></div>
						<div class="modal-body" id="mbody"></div>
					</div>
				</div>
			</div>
			<div id="memberwin" class="modal fade" tabindex="-1" role="dialog"
				aria-hidden="true" data-backdrop="static" data-keyboard="true"
				aria-labelledby="exampleModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">					
					<table border="1" style="text-align: center; width: 100%">
						<tr>
						<th>會員編號</th>
						<th>會員姓名</th>
						<th>email</th>																
					</tr>	
					<tbody id="membertable"></tbody>
					</table>
					</div>
				</div>
			</div>
	<!-- //彈出式視窗		 -->
	<!--====== Javascripts & Jquery ======-->
	<script src="${pageContext.request.contextPath}/JS/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/owl.carousel.min.js"></script>
	<script	src="${pageContext.request.contextPath}/JS/jquery.marquee.min.js"></script>
	<script src="${pageContext.request.contextPath}/JS/main.js"></script>
</body>
</html>
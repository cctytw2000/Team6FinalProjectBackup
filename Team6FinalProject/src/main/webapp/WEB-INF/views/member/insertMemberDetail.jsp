<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../JS/InsertMemberImfo.js"></script>
<script src="../JS/address.js"></script>


<link rel="stylesheet" href="../CSS/bootstrap.min.css" />
    <link rel="stylesheet" href="../CSS/font-awesome.min.css" />
    <link rel="stylesheet" href="../CSS/owl.carousel.css" />
    <link rel="stylesheet" href="../CSS/style.css" />
    <link rel="stylesheet" href="../CSS/animate.css" />

<link rel="stylesheet" href="../CSS/RegisteredMember.css">
<script src="https://kit.fontawesome.com/685268963f.js"></script>
<style>
.label1{
color : white ;
}
.sex {
color : white ;
}
</style>
</head>





<body>

    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header section -->
    <header style="overflow: hidden;" class="header-section">
        <div class="container">
            <!-- logo -->
            <a class="site-logo" href="../home.jsp">
                <img src="../Images/logo.png" alt="">
            </a>

            <!-- responsive -->
            <div class="nav-switch">
                <i class="fa fa-bars"></i>
            </div>
            <!-- site menu -->
            <nav class="navbar navbar-expand-lg navbar-dark">
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul class="navbar-nav mr-auto">
                                        <li class="nav-item active">
                                            <a class="nav-link" href="#">首頁<span class="sr-only">(current)</span></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">最新消息</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">遊戲討論區</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">影片區</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">商城</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
        </div>
    </header>
    <!-- Header section end -->

    <!-- Latest news section -->
    <div class="latest-news-section">
        <div class="ln-title">重要消息</div>
        <div class="news-ticker">
            <div class="news-ticker-contant">
                <div class="nt-item"><span class="new">新聞</span> <a href="#">台灣獲得 LOL S2 冠軍</a></div>
                <div class="nt-item"><span class="strategy">重要訊息</span> <a href="#">LOL 即將倒閉</a></div>
                <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 練笑話 準決賽</a> </div>
                <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 講幹話 準決賽</a> </div>
                <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 講屁話 準決賽</a> </div>
            </div>
        </div>
    </div>
    <!-- Latest news section end -->
    
    
    <!-- Hero section -->
<!--     <section class="hero-section"> -->
<!--         <div class="hero-slider owl-carousel"> -->
<!--             <div class="hs-item set-bg" data-setbg="../Images/slider-1.jpg"> -->
<!--                 <div class="hs-text"> -->
<!--                     <div class="container"> -->
<!--                         <h2>The Best <span>Games</span> Out There</h2> -->
<!--                         <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada <br> lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. <br>Suspendisse cursus faucibus finibus.</p> -->
<!--                         <a href="#" class="site-btn">Read More</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="hs-item set-bg" data-setbg="../Images/slider-2.jpg"> -->
<!--                 <div class="hs-text"> -->
<!--                     <div class="container"> -->
<!--                         <h2>The Best <span>Games</span> Out There</h2> -->
<!--                         <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada <br> lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. <br>Suspendisse cursus faucibus finibus.</p> -->
<!--                         <a href="#" class="site-btn">Read More</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </section> -->
    <!-- Hero section end -->




    <section class="footer-top-section">
        <div class="container">
            <div class="footer-top-bg">
                <img src="../Images/footer-top-bg.png" alt="">
            </div>



	<form action="../InsertMemberImfo.do" method="post">
		<fieldset style="margin-left:40%">
					<c:choose>
						<c:when test="${param.id != Null && param.token != Null}">
			
			<input type="hidden" name="memberID" value="${param.id}"> 
			<input type="hidden" name="token" value="${param.token}">
						</c:when>
						<c:otherwise>
					<input type="hidden" name="memberID" value="${param.id}"> 
						</c:otherwise>

					</c:choose>
			<div class="div1">
				<label class='label1'>身分證字號:</label><input id="idnumber" type="text"
					name="idnumber"><span id="idnumber_password"></span>
			</div>

			<div class="div1">
				<label class='label1'>地址:</label> <select style="margin-left:15px" id="city">
					<option value=""></option>
				</select> <select id="Township">
					<option value=""></option>
				</select><input type="text" size="30" name="alladdress" id="alladdress"
					value=""><span id="address_password"></span>

			</div>


			<div class="div1">
				<label class='label1'>性別:</label><input type="radio" id="sex"
					name="sex" value="male" required="required"><span class="sex">男</span><input 
					id="sex" type="radio" name="sex" value="female"><span class="sex">女</span><span
					id="sex_password" required="required"></span>
			</div>
			<div class="div1">
				<label class='label1'>生日:</label><input id="date" type="date"
					name="birth"><span id="date_password"></span>
			</div>
			<div id="submit" class="div1" style="text-align: center">
				<button type="submit" disabled>新增</button>

			</div>
		</fieldset>
		</form>
</div>

</section>
  






    <!-- Footer section -->
    <footer class="footer-section">
        <div class="container">
            <ul class="footer-menu">
                <li><a href="../home.jsp">Home</a></li>
                <li><a href="review.html">Games</a></li>
                <li><a href="categories.html">Blog</a></li>
                <li><a href="community.html">Forums</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
            <p class="copyright">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                Copyright &copy;
                <script>
                    document.write(new Date().getFullYear());
                </script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </p>
        </div>
    </footer>
    <!-- Footer section end -->


    <!--====== Javascripts & Jquery ======-->
    <script src="../JS/jquery-3.2.1.min.js"></script>
    <script src="../JS/bootstrap.min.js"></script>
    <script src="../JS/owl.carousel.min.js"></script>
    <script src="../JS/jquery.marquee.min.js"></script>
    <script src="../JS/main.js"></script>












</body>
</html>
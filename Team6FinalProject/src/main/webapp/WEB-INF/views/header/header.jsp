<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header section -->
    <header class="header-section">
        <div class="container">
            <!-- logo -->
            <a class="site-logo" href="${pageContext.request.contextPath}">
				<img src="<c:url value='/Images/newLogo.png' />" />
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
<!--     <div class="latest-news-section"> -->
<!--         <div class="ln-title">重要消息</div> -->
<!--         <div class="news-ticker"> -->
<!--             <div class="news-ticker-contant"> -->
<!--                 <div class="nt-item"><span class="new">新聞</span> <a href="#">台灣獲得 LOL S2 冠軍</a></div> -->
<!--                 <div class="nt-item"><span class="strategy">重要訊息</span> <a href="#">LOL 即將倒閉</a></div> -->
<!--                 <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 練笑話 準決賽</a> </div> -->
<!--                 <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 講幹話 準決賽</a> </div> -->
<!--                 <div class="nt-item"><span class="racing">賽事</span><a href="#">台灣 講屁話 準決賽</a> </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
    <!-- Latest news section end -->





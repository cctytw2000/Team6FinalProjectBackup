<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kit.fontawesome.com/685268963f.js"></script>




    <script src="${pageContext.request.contextPath}/JS/address.js"></script>
    <script src="${pageContext.request.contextPath}/JS/InsertMemberImfo.js"></script>


    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/bootstrap.min.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/font-awesome.min.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/owl.carousel.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/style.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/animate.css' type="text/css" />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/CSS/RegisteredMember.css' type="text/css" />






    <style>
        .label1 {
            color: white;
        }

        .sex {
            color: white;
        }
    </style>
</head>





<body>

    <jsp:include page="header/header.jsp" />





    <section class="footer-top-section">
        <div class="container">
            <div class="footer-top-bg">
                <img src="<c:url value='/Images/footer-top-bg.png' />" />
            </div>


            <form:form action="${pageContext.request.contextPath}/member/insertMemberInformation" method='POST'
                modelAttribute="MemberDetail" enctype="multipart/form-data">

                <fieldset style="margin-left:40%">


                    <!-- 		md.setUsername(username); -->
                    <!-- 		md.setId(memberid); -->
                    <!-- 		md.setType(type); -->



                    <c:choose>
                        <c:when
                            test="${MemberDetail.id != Null && MemberDetail.token && MemberDetail.username == null }">
                            <form:hidden path="id" />
                            <form:hidden path="token" />
                        </c:when>
                        <c:otherwise>

                            <form:hidden path="id" />
                            <form:hidden path="type" />
                            <form:hidden path="username" />
                        </c:otherwise>

                    </c:choose>









                    <div class="div1">
                        <label class='label1'>暱稱:</label>
                        <form:input id="nickname" name="nickname" path="nickname" type='text' /><span
                            id="nickname_password"></span>


                    </div>






                    <div class="div1">
                        <label class='label1'>行動電話:</label>
                        <form:input id="tel" name="tel" path="tel" type='text' /><span id="tel_password"></span>


                    </div>








                    <div class="div1">
                        <label class='label1'>身分證字號:</label>
                        <form:input id="idnumber" name="idnumber" path="idnumber" type='text' /><span
                            id="idnumber_password"></span>


                    </div>

                    <div class="div1">
                        <label class='label1'>地址:</label> <select style="margin-left:15px" id="city">
                            <option value=""></option>
                        </select> <select id="Township">
                            <option value=""></option>
                        </select>

                        <form:input id="alladdress" size="30" name="alladdress" path="address" type='text' /><span
                            id="address_password"></span>

                    </div>


                    <div class="div1">
                        <form:radiobutton id="sex" name="sex" path="sex" value="male" required="required" /><span
                            class="sex">男</span>

                        <form:radiobutton id="sex" name="sex" path="sex" value="female" required="required" /><span
                            class="sex">女</span>
                    </div>
                    <div class="div1">
                        <label class='label1'>生日:</label>
                        <form:input id="date" name="birth" path="birth" type='date' /><span id="date_password"></span>
                    </div>
                    <div id="submit" class="div1" style="text-align: center">
                        <button type="submit" disabled>新增</button>

                    </div>
                </fieldset>
            </form:form>
        </div>

    </section>




    <jsp:include page="footer/footer.jsp" />












</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>新增商品</title>
</head>

<body>
	<h1>新增商品資料</h1>
	<form:form method="POST" modelAttribute="product" enctype="multipart/form-data">
		商品分類:<form:select path="category_">
			<form:option value="-1">請挑選</form:option>
			<form:options items="${categoryMap}"></form:options>
		</form:select>
		<p>
			商品名稱:
			<form:input path="name" type="text" size="50px" />
			<p>
				廠商名稱:
				<form:input path="publisher" type="text" size="50px" />
				<p>
					商品價格:
					<form:input path="price" type="text" />
					<p>
						庫存數量:
						<form:input path="stock" type="text" />
						<p>
							商品描述:
							<form:textarea path="game_desc" style="width:400px;height:200px;" />
							<p>
								是否上架:
								<form:radiobutton path="is_remove" value="0" id="0" /><label for="0">是</label>
								<form:radiobutton path="is_remove" value="1" id="1" /><label for="1">否</label>
								<p>
									選擇圖片:
									<form:input path="productImage" type="file" />
									<p>
										<input type="submit" value="送出">
	</form:form>

</body>

</html>
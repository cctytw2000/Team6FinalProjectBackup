<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>更新商品</title>
</head>

<body>
	<h1>更新商品資料</h1>
	<form:form method="POST" modelAttribute="product" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品分類:
				<td>
					<form:select path="category_">
						<form:option value="${product.category.category_id }" label="${product.category.category }" />
						<form:options items="${categoryMap }"></form:options>
					</form:select>
			<tr>
				<td>商品名稱:
				<td>
					<form:input path="name" type="text" size="50px" />
			<tr>
				<td>廠商名稱:
				<td>
					<form:input path="publisher" type="text" size="50px" />
			<tr>
				<td>商品價格:
				<td>
					<form:input path="price" type="text" />
			<tr>
				<td>庫存數量:
				<td>
					<form:input path="stock" type="text" />
			<tr>
				<td>商品描述:
				<td>
					<form:textarea path="game_desc" style="width:400px;height:200px;" />
			<tr>
				<td>選擇圖片:
				<td>
					<form:input path="productImage" type="file" />
			<tr>
				<td>是否移除:
				<td>
					<form:radiobutton path="is_remove" value="1" id="1" /><label for="1">是</label>
					<form:radiobutton path="is_remove" value="0" id="0" /><label for="0">否</label>
			<tr>
				<td><input type="submit" value="送出">
		</table>
	</form:form>
</body>

</html>
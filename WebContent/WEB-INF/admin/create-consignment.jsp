<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
<link rel="stylesheet" href="style/warehouse-update.css" type="text/css" />
<meta charset="ISO-8859-1">
<title>WareHouse Update</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section class="section">
		<div class="div_table">
			<form action="AddConsignment" method="post">
				<table class="table">
					<tr>
						<td class="title" colspan="2">Create New Consignment</td>
					</tr>
					<tr>
						<td><label for="WareHouse">FromWareHouseId </label></td>
						<td><select name="fromWareHouse" id="WareHouse">
								<c:forEach items="${wareHouse}" var="wareHouse">
									<option value="${wareHouse.getId()}">${wareHouse.getId()}-${wareHouse.getLocation()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label for="WareHouse">ToWareHouseId </label></td>
						<td><select name="toWareHouse" id="WareHouse">
								<c:forEach items="${wareHouse}" var="wareHouse">
									<option value="${wareHouse.getId()}">${wareHouse.getId()}-${wareHouse.getLocation()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label for="status">Status</label></td>
						<td><input type="text" name="consignmentStatus" id="status"
							value="Booked" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" class="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp" %>

</body>
</html>
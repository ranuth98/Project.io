<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ware House Details</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/warehouse_admin.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
	    <%@ include file="header.jsp" %>
        <section class="section">
        <div class="padding">
	<div class="div_title">
		<span><c:if
				test="${addStatus == true}">
				<h2 class="status">Added Successfully</h2>
			</c:if> <c:if test="${updateStatus == true}">
				<h2 class="status">Updated Successfully</h2>
			</c:if><c:if test="${failStatus == true}">
                <h2 class="status">Failed!!!!!!!</h2>
            </c:if>
            </span> <a href="UpdateWareHouse?wareHouseId=0" class="add">Add
				New WareHouse</a></div>
	</div>
	<div class="div_table">


		<table class="table">


			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Location</th>
				<th>Manager</th>
				<th>Contact</th>
				<th>Capacity</th>
				<th></th>
			</tr>
			<c:forEach items="${wareHouse}" var="wareHouse">
				<tr>
					<td>${wareHouse.getId()}</td>
					<td>${wareHouse.getName()}</td>
					<td>${wareHouse.getLocation()}</td>
					<td>${wareHouse.employee.getFirstName()}</td>
					<td>${wareHouse.employee.getContactNumber()}</td>
					<td>${wareHouse.getCapacity()}</td>
					<td><a href="UpdateWareHouse?wareHouseId=${wareHouse.getId()}"
						class="update">Update</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
    </section>
    <%@ include file="footer.jsp" %>
</body>
</html>
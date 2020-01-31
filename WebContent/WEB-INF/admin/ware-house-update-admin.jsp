<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WareHouse Update</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
<link rel="stylesheet" href="style/warehouse-update.css" type="text/css" />
</head>
<body>
	<%@ include file="header.jsp"%>
    <section class="section">
	<div class="flexbody">
		<c:set value="${wareHouseList}" var="wareHouse"></c:set>
		<c:set value="${from}" var="from"></c:set>
		<div class="div_table">
        <c:if test="${from == 'r'}"><p>The Manager has been assigned to this wareHouse.Kindly update in order to delete.</p></c:if>
			<form method="get" action="AddWareHouse">
				<table class="table">
					<tr>
						<td class="title" colspan="2"><c:choose>
								<c:when test="${wareHouse.getId() >= 1}"> Update Ware House</c:when>
								<c:otherwise>Add Ware House</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td><label for="name">Ware House Name </label></td>
						<td><input type="text" name="name"
							value="${wareHouse.getName()}" required="required" id="name"></td>
					</tr>
					<tr>
						<td><label for="location">Location</label></td>
						<td><input type="text" name="location"
							value="${wareHouse.getLocation()}" required="required"
							id="location"></td>
					</tr>
					<tr>
						<td><label for="managerId">Manager ID </label></td>
						<td><select id="managerId" name="managerId">
								<option value="${wareHouse.getEmployee().getEmployeeId()}">${wareHouse.getEmployee().getEmployeeId()}-${wareHouse.getEmployee().getFirstName()}</option>
								<c:forEach items="${manager}" var="manager">
									<option value="${manager.getEmployeeId()}">${manager.getEmployeeId()}-${manager.getFirstName()}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label for="capacity">Capacity</label></td>
						<td><input type="number" name="capacity"
							value="${wareHouse.getCapacity()}" required="required"
							id="capacity"></td>
					</tr>
					<tr>
						<td><input type="hidden" value="${wareHouse.getId()}"
							name="wareHouseId"></td>

						<c:choose>
							<c:when test="${from == 'r'}">
								<td><input type="hidden" value="r" name="from"></td>
							</c:when>
							<c:otherwise>
								<td><input type="hidden" value="n" name="from"></td>
							</c:otherwise>
						</c:choose>
						<td><input type="submit" value="Submit" class="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
    </section>
	<%@ include file="footer.jsp"%>
</body>
</html>
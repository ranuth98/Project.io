<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Parcel</title>
<meta name='viewport'
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/parcel_update.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
	   <%@ include file="header.jsp" %>
       <section class="section">
       <div class="formdiv">
	<form action="ParcelSave" method="post" class="parcelform">
		<c:set value="${parcel}" var="parcel"></c:set>
		<c:choose>
			<c:when test="${add}">
				<p class="formname">Add Parcel Details</p>
			</c:when>
			<c:otherwise>
				<p class="formname">Update Parcel Details</p>
			</c:otherwise>
		</c:choose>
		<table class="parcelformtable">
			<tr>
				<td></td>
				<td><input type="hidden" name="parcelId"
					value="${parcel.getId()}" /></td>
			</tr>
			<tr>
				<td><label for="parcelName" class="label">Parcel Type</label></td>
				<td><input type="text" name="parcelName" id="parcelName"
					required="required" class="input" value="${parcel.getParcelName()}"/>
						</td>
			</tr>
			<tr>
				<td><label for="price" class="label">Price / Kg in Rs.</label></td>
				<td><input type="number" name="price" id="price"
					value="${parcel.getPrice()}" required="required" class="input" step=".01"/></td>
			</tr>
			<tr>
				<td><a href="ParcelTypeView" class="viewparcel">View Parcel</a></td>
				<td><input type="submit" name="save" value="Save" class="save" /></td>
			</tr>
		</table>
		<c:if test="${success}">
			<p class="success">Parcel Details Saved Successfully</p>
		</c:if>
		<c:if test="${success == false}">
			<p class="success">Parcel type already exist</p>
		</c:if>
	</form></div>
    </section>
	   <%@ include file="footer.jsp" %>
</body>
</html>
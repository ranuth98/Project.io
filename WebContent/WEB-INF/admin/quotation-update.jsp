<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Quotation</title>
<meta name='viewport'
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/quotation_update.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
   <%@ include file="header.jsp" %>
   <section class="section">
   <div class="quotationdiv">
	<form action="QuotationSave" method="post" class="quotationform">
		<c:set value="${quotation}" var="quotation"></c:set>
		<c:choose>
			<c:when test="${add}">
				<p class="formname">Add Quotation Details</p>
			</c:when>
			<c:otherwise>
				<p class="formname">Update Quotation Details</p>
			</c:otherwise>
		</c:choose>
		<table class="quotationformtable">
			<tr>
				<td></td>
				<td><input type="hidden" name="quotationId"
					value="${quotation.getId()}" /></td>
			</tr>
			<tr>
				<td><label for="distance" class="label">Distance in Km</label></td>
				<td><input type="number" name="distance" id="distance"
					value="${quotation.getDistance()}" required class="input" /></td>
			</tr>
			<tr>
				<td><label for="cost" class="label">Courier Charges Rs.</label></td>
				<td><input type="number" name="cost" id="cost"
					value="${quotation.getPrice()}" required class="input" step="0.01"/></td>
			</tr>
			<tr>
				<td><a href="QuotationView" class="viewquotation">View Quotation</a></td>
				<td><input type="submit" name="save" value="Save" class="save" /></td>
			</tr>
		</table>
		<c:if test="${success}">
			<p class="success">Quotation Details Saved Successfully</p>
		</c:if>
		<c:if test="${success == false}">
			<p class="success">Distance range already exist</p>
		</c:if>
	</form></div>
    </section>
   <%@ include file="footer.jsp" %>
</body>
</html>
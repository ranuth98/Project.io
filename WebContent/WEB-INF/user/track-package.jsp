<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courier Tracking</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/track_package.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
    <section class="section">
    <div class="fullBox">
		<form  method="post">
        <div class="box">
			<label for="packageId" class="pack">Package Id</label> 
            <input type="number" name="packageId" id="packageId" required value="${packages.getPackageId()}" /> </div>
            <div class="trackingdiv"><input type="image" class="tracking" src="images/tracking.png" formaction="PackageStatus" name="tracking">
		</div ></form></div>
	<div class="status">
    <c:if test="${track}">
		<div>
			<p>Incorrect Package Id details</p>
		</div>
    </c:if>
	<c:if test="${status == 'booked'}">
		<div>
			<p>Your package is Booked at ${location}</p>
		</div>
	</c:if>
    
	<c:if test="${status == 'intransit'}">
		<div>
			<p>Your package is forwarded to ${location}</p>
		</div>
	</c:if>
	<c:if test="${status == 'indelivery'}">
		<div>
			<p>Your package is out for delivery</p>
		</div>
	</c:if>
	<c:if test="${status == 'delivered'}">
		<div>
			<p>Your package is successfully delivered</p>
		</div>
	</c:if>
    </div>
    </section>
	<%@ include file="footer.jsp" %>
</body>
</html>

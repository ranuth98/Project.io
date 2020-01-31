<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Profile</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
<link rel="stylesheet" href="style/user_profile.css" type="text/css" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<section class="section">
		<c:set value="${user}" var="user"></c:set>
		<div class="profile_div">
			<div class="row">
				<div class="label">User Name</div>
				<div class="data">${user.getUserName()}</div>
			</div>
			<div class="row">
				<div class="label">User Id</div>
				<div class="data">${user.getId()}</div>
			</div>
			<div class="row">
				<div class="label">First Name</div>
				<div class="data">${user.getFirstName()}</div>
			</div>
			<div class="row">
				<div class="label">Last Name</div>
				<div class="data">${user.getLastName()}</div>
			</div>
			<div class="row">
				<div class="label">Gender</div>
				<div class="data">${user.getGender()}</div>
			</div>
			<div class="row">
				<div class="label">Email</div>
				<div class="data">${user.getEmail()}</div>
			</div>
			<div class="row">
				<div class="label">Contact Number</div>
				<div class="data">${user.getContactNumber()}</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>
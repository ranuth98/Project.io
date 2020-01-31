<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courier Tracking</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/login_style.css" type="text/css" />
<script type="text/javascript" src="js/index_login.js"></script>
<style type="text/css">

</style>
</head>
<body class="loginbody">
	<%@ include file="index-header.jsp"%>
	
	<section class="section">
	
	

	<c:set value="${loginType}" var="role"></c:set>

	<form action="LoginAuthentication" method="post" class="loginform"
		onsubmit="return loginValidate()">
<div class="login_div">
		<table class="logintable">
        <tr>
        <c:set value="${registrationStatus}" var="registrationStatus"></c:set>
    <c:if test="${logout_status}">
        <h2 class="logout_status">Logout Successfully!</h2>
    </c:if>

    <c:if test="${registrationStatus}">
        <span class="logout_status">Your Registration is Successful!</span>
        <br />
    </c:if>
    
        </tr>
			<tr>
				<c:if test="${role == 'a'}">
					<p class="label">Admin Login</p>
				</c:if>

				<c:if test="${role == 's'}">
					<p class="label">Staff Login</p>
				</c:if>

				<c:if test="${role == 'u'}">
					<p class="label">User Login</p>
				</c:if>
			</tr>
			<tr>
				<td><label class="label" for="username">User Name</label></td>
				<td><input type="text" name="username" id="username"
					placeholder="username" class="input" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="role" value="${role}"></td>
			</tr>
			<tr>
				<td><label class="label" for="password">Password</label></td>
				<td><input type="password" name="password" id="password"
					placeholder="password" class="input" /></td>
			</tr>
		</table>

		<c:if test="${loginStatus == true}">
			<span class="loginStatus">Invalid UserName/Password</span>
			<br>
		</c:if>
		<c:if test="${waitingStatus == true}">
			<span class="loginStatus">Your registration is pending</span>
			<br>
		</c:if>
		<c:if test="${deniedStatus == true}">
			<span class="loginStatus">Your registration is denied</span>
			<br>
		</c:if>

		<span class="forgotpassword">forgot password ?</span> <input
			type="submit" name="login" value="Login" class="button" />
		<p class="registration">
			<label>Registration:</label>
			<c:if test="${role == 'a'}">
			 <a href="Registration?role=a"
				class="admin">New Admin</a>
			</c:if>
			<c:if test="${role == 's'}">
			 <a href="Registration?role=s"
				class="admin">New Staff</a>
			</c:if>
			<c:if test="${role == 'u'}">
			 <a href="Registration?role=u"
				class="admin">New User</a>
			</c:if>
		</p>
		</div>
	</form>
	
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>
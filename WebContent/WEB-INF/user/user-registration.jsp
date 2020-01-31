<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>User Registration</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/user_registration.css"
	type="text/css" />
<script src="js\user_register.js"></script>
</head>
<body>
	<%@ include file="index-header.jsp" %>
	
	<section class="section">
	<form action="UserRegistration" method="post"
		onsubmit="return validate(this)" id="userForm">

		<table>

			<tr>
				<td class="p3" colspan="2"><b>User Registration Form</b></td>
			</tr>
			<c:if test="${userNameAvailableStatus == true}">
				<tr>
					<td style="color: red">User Name already exists</td>
				</tr>
			</c:if>
			<tr>
				<td><b><label for="firstName">First name </label></b></td>
				<td><input type="text" name="firstName" required="required"
					id="firstName"></td>
			</tr>
			<tr>
				<td><b><label for="lastName">Last name</label></b></td>
				<td><input type="text" name="lastName" id="lastName"></td>
			</tr>
			<tr>
				<td><b><label for="gender">Gender</label></b></td>
				<td><select class="gender" id="gender" name="gender">
						<option value="0">--Select--</option>
						<option value="male" class="p1">Male</option>
						<option value="female" class="p1">Female</option>

				</select></td>
			</tr>
			<tr>
				<td><b><label for="email">E-mail</label></b></td>
				<td><input type="email" name="email" id="email"></td>
			</tr>
			<tr>
				<td><b><label for="contact">Contact Number</label></b></td>
				<td><input type="tel" name="contact" maxlength="10"
					required="required" pattern="[0-9]{10}" id="contact"></td>
			</tr>
			<tr>
				<td><b><label for="userId">User Name</label></b></td>
				<td><input type="text" name="userId" required="required" id="userId"></td>
			</tr>
			<tr>
				<td><b><label for="enterPassword">Password</label></b></td>
				<td><input type="password" name="enterPassword"
					required="required" id="enterPassword"></td>
			</tr>
			<tr>
				<td><b><label for="confirmPassword">Confirm
							Password</label></b></td>
				<td><input type="password" name="confirmPassword"
					required="required" id="confirmPassword"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit" class="submit"></td>
			</tr>
		</table>
  		 <input type="hidden" name="active" value="w">
         <input type="hidden" name="loginType" value="u">
	</form>
	
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Registration Page</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/user_registration.css"
	type="text/css" />
<script type="text/javascript" src="js/admin_register.js"></script>
</head>
<body>
	 <%@ include file="index-header.jsp" %>
	 <section class="section">
	<c:set value="${loginType}" var="loginType"/>
	<form id="adminForm" action="EmployeeRegistration" method="post"
		onsubmit="return adminValidate()" class="registerform">
		<table class="admin_reg_table">
			<c:if test="${loginType == 'a'}">
				<tr>
					<td class="admin_form_header" colspan="2">Admin Registration
						Form</td>
				</tr>
			</c:if>
			<c:if test="${loginType == 's'}">
				<tr>
					<td class="admin_form_header" colspan="2">Staff Registration
						Form</td>
				</tr>
			</c:if>
			<c:if test="${userNameAvailableStatus == true}">
					<tr>
					<td style="color: red">User Name already exist / rejected!!!</td>
					</tr>
			</c:if>

			<tr>
				<td class="admin_fields"><label for="firstName">First
						Name</label></td>
				<td class="admin_fields"><input type="text" name="firstName" id="firstName"
					required="required" /></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="lastName">Last
						Name</label></td>
				<td><input type="text" id="lastName" name="lastName" required="required" /></td>
			</tr>
            
			<tr>
				<td class="admin_fields"><label for="gender">Gender</label></td>
				<td><select id="gender" name="gender">
						<option value="0">--Select--</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
				</select></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="email">Email</label></td>
				<td><input id="email" type="email" name="email" /></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="contactNumber">Contact
						Number</label></td>
				<td><input type="tel" name="contactNumber" pattern="[0-9]{10}"
					maxlength="10" required="required" id="contactNumber"/></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="userId">User Name</label></td>
				<td><input type="text" name="userId" required="required" id="userId"/></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="password">Password</label></td>
				<td><input type="password" name="password" required="required" id="password"/></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="confirmPassword">Confirm
						Password</label></td>
				<td><input type="password" name="confirmPassword" id="confirmPassword"
					required="required" /></td>
			</tr>
            <c:if test="${loginType == 'a'}">
            <tr>
                <td class="admin_fields"><label for="designation">Designation</label></td>
                <td><select name="designation" id="designation">
                        <option value="0">--Select--</option>
                        <option value="Manager">Manager</option>
                        <option value="SeniorManager">Senior Manager</option>
                </select></td>
            </tr>
            </c:if>
            <c:if test="${loginType == 's'}">
			<tr>
				<td class="admin_fields"><label for="designation">Designation</label></td>
				<td><select name="designation" id="designation">
						<option value="0">--Select--</option>
						<option value="Staff">Staff</option>
						<option value="Delivery Boy">Delivery Boy</option>
				</select></td>
			</tr>
            </c:if>
            <tr>
				<td class="admin_fields"><label for="correspondenceAddress" >Correspondence
						Address</label></td>
				<td><textarea class="address" name="correspondenceAddress" id="correspondenceAddress"
                    maxlength="100" required="required" rows="3">  </textarea></td>
			</tr>
			<tr>
				<td class="admin_fields"><label for="permanentAddress">Permanent
						Address</label></td>
				<td><textarea class="address" id="permanentAddress" name="permanentAddress" maxlength="100"  rows="3"></textarea></td>
			</tr>
			
			<tr>
				<td><input type="hidden" name="loginType" value="${loginType}" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="active" value="w" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"
					class="admin_field_submit" /></td>
			</tr>
		</table>
	</form>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>
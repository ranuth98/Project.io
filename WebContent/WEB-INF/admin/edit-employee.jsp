<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/edit_employee_page.css"
	type="text/css" />
<script type="text/javascript" src="js/admin_register.js"></script>
</head>

<body>
	 <%@ include file="header.jsp" %>
	<c:set value="${employee}" var="employee"></c:set>

	<section>
		<form id="employeeForm" action="UpdateStaff" method="get"
			onsubmit="return editEmployeeValidate()" class="registerform">

			<h2>Edit Employee Details</h2>

			<div class=main_div>

				<div class="admin_fields">
					<label for="firstName">First Name</label><input type="text"
						name="firstName" required="required"
						value="${employee.getFirstName()}" style="width: 300px" />
				</div>

				<div class="admin_fields">
					<label for="lastName">Last Name</label> <input type="text"
						name="lastName" required="required"
						value="${employee.getLastName()}" style="width: 300px" />
				</div>



				<table class="edit_table">
					<tr>
						<td class=""><label for="contactNumber">Contact
								Number</label></td>
						<td class=""><label for="designation">Designation</label></td>
						<td><label for="salary">Salary</label>
					</tr>

					<tr>
						<td><input type="tel" name="contactNumber"
							value="${employee.getContactNumber()}" pattern="[0-9]{10}"
							maxlength="10" required="required" size="30" /></td>
						<td><select name="designation" style="width: 200px">
								<option value="Staff">${employee.getDesignation()}</option>
								<option value="Manager">Manager</option>
								<option value="Delivery Boy">Delivery Boy</option>
						</select></td>
						<td><input type="number" name="salary" style="width: 200px"
							value="${employee.getSalary()}"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="employeeId"
							value="${employee.getEmployeeId()}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" class="submit" /></td>
					</tr>
				</table>
			</div>
		</form>

	</section>
<%@ include file="footer.jsp" %>
</body>
</html>
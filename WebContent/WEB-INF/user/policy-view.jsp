<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Policy</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/policy_view.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
 <%@ include file="header.jsp" %>
 <section class="section">
	<div class="flex-container">
		<textarea rows="10" cols="80" name="policy" class="textarea"
			readonly="readonly" class="textarea"><c:out
				value="${policy.getPolicyName()}">${policy.getPolicyName()}</c:out>
		</textarea>
	</div>
    </section>
	 <%@ include file="footer.jsp" %>
</body>
</html>
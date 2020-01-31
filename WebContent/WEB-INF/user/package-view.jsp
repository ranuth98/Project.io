<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Package</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/package_view.css" type="text/css" />
</head>
<body>
<header>
<%@include file="header.jsp" %>
</header>
<section class="section">
	<div>
		<div class="table_header">
			<div>Package Id</div>
			<div>Parcel Type</div>
			<div>Booked Date</div>
			<div>Package Weight</div>
			<div>Sender Address</div>
			<div>Receiver Address</div>
			<div>Cost</div>
            <div>Status</div>
		</div>
		<div class="full">
			<c:forEach items="${packageList}" var="packageList">
				<div class="row">
					<div>${packageList.getPackageId()}</div>
					<div>${packageList.getParcelType().getParcelName()}</div>
					<div>
						<f:formatDate value="${packageList.getBookDate()}"
							pattern="dd/MM/yyyy" />
					</div>
					<div>${packageList.getPackageWeight()}</div>
					<div>${packageList.getSenderAddress()}</div>
					<div>${packageList.getReceiverAddress()}</div>
					<div>${packageList.getCost()}</div>
                    <div>${packageList.getStatus()}</div>
                    <div><a href="GenerateBill?id=${packageList.getPackageId()}"  class="bill">View Bill</a></div>
				</div>
			</c:forEach>
		</div>
	</div>
    </section>
    <footer>
    <%@ include file="footer.jsp" %>
    </footer>
</body>
</html>
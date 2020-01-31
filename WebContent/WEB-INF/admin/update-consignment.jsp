<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consignment Packages</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
<link rel="stylesheet" href="style/update_consignment.css" type="text/css" />
</head>
<body>
<%@ include file="header.jsp" %>
<section class="section">
	<div class="consignment">
		<c:set value="${consignment}" var="consignment"></c:set>
		<form method="get">
			<table>
				<tr class="data">
					<td>Consignment Id</td>
					<td>From Location</td>
					<td>To Location</td>
					<td>Status</td>
					<td></td>
                    <td></td>
				</tr>
				<tr >
					<td><input type="number" name="consignmentId" readonly
						value="${consignment.getConsignmentId()}" class="input" /></td>
					<td><input type="text" name="fromLocation" readonly
						value="${consignment.getFromWareHouse().getLocation()}"
						class="input" /></td>
					<td><input type="text" name="toLocation" readonly
						value="${consignment.getToWareHouse().getLocation()}"
						class="input" /></td>
					<td><select name="consignmentStatus"><option
								value="${consignment.getConsignmentStatus()}">${consignment.getConsignmentStatus()}</option>
							<option value="In Transit">In Transit</option>
							<option value="Booked">Booked</option>
							<option value="Delivered">Delivered</option>
					</select></td>
					<td><input type="submit" name="submit" value="Submit" formaction="UpdateConsignment"/></td>
                   <td><input type="submit" name="submit" value="View Consignment" formaction="ViewConsignment"/></td>
				</tr>

			</table>
		</form>
	</div>
	<c:if test="${booked}">
		<div class="booked">
			<p class="p">Packages Ready To Add into Consignment</p>
			<div class="table_header">
				<div>Package ID</div>
				<div>Sender Address</div>
				<div>Receiver Address</div>
				<div>Status</div>
				<div></div>
			</div>
			<c:forEach items="${bookedList}" var="packages">
				<div class="row">
					<div>${packages.getPackageId()}</div>
					<div>${packages.getSenderAddress()}</div>
					<div>${packages.getReceiverAddress()}</div>
					<div>${packages.getStatus()}</div>
					<div>
						<a href="AddToThisConsignment?packageId=${packages.getPackageId()}&consignmentId=${consignment.getConsignmentId()}" class="link">Add
							To Consignment</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${intransit == 'yes'}">
		<div class="intransit">
			<p class="p">Packages inside the Consignment</p>
			<div class="table_header">
				<div>Package ID</div>
				<div>Sender Address</div>
				<div>Receiver Address</div>
				<div>Status</div>
			</div>
			<c:forEach items="${inTransitList}" var="consignmentList">
				<div class="row">
					<div>${consignmentList.getPackageObj().getPackageId()}</div>
					<div>${consignmentList.getPackageObj().getSenderAddress()}</div>
					<div>${consignmentList.getPackageObj().getReceiverAddress()}</div>
					<div>${consignmentList.getPackageObj().getStatus()}</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${intransit}">
		<div class="intransit">
			<p class="p">Packages inside the Consignment</p>
			<div class="table_header">
				<div>Package ID</div>
				<div>Sender Address</div>
				<div>Receiver Address</div>
				<div>Status</div>
			</div>
			<c:forEach items="${inTransitList}" var="consignmentList">
				<div class="row">
					<div>${consignmentList.getPackageObj().getPackageId()}</div>
					<div>${consignmentList.getPackageObj().getSenderAddress()}</div>
					<div>${consignmentList.getPackageObj().getReceiverAddress()}</div>
					<div>${consignmentList.getPackageObj().getStatus()}</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${delivered}">
		<div class="delivered">
			<p class="p">Packages inside the delivered Consignment</p>
			<div class="table_header">
				<div>Package ID</div>
				<div>Sender Address</div>
				<div>Receiver Address</div>
				<div>Status</div>
                <div>Package Updation</div>
				<div></div>
			</div>
			<c:forEach items="${deliveredList}" var="consignmentList">
			<form action="PackageUpdateInConsignment" method="get">
				<div class="row">
					<div>${consignmentList.getPackageObj().getPackageId()}</div>
					<div>${consignmentList.getPackageObj().getSenderAddress()}</div>
					<div>${consignmentList.getPackageObj().getReceiverAddress()}</div>
					<div>${consignmentList.getPackageObj().getStatus()}</div>
					<div><select name="newConsignmentId">
					<option value="In Delivery">In Delivery</option>
					<c:forEach items="${newConsignments}" var="consignment">
							<option value="${consignment.getConsignmentId()}">${consignment.getConsignmentId()} - ${consignment.getFromWareHouse().getLocation()} - ${consignment.getToWareHouse().getLocation()}</option>
					</c:forEach>
					</select>
						<input type="hidden" value="${consignmentList.getPackageObj().getPackageId()}" name="packageId"/>
						<input type="hidden" value="${consignmentList.getConsignmentListId()}" name="oldConsignmentListId"/>
					</div>
					<div><input type="submit" value="Update"></div>
				</div>
				</form>
			</c:forEach>
		</div>
	</c:if>
    </section>
    <%@ include file="footer.jsp" %>
</body>
</html>

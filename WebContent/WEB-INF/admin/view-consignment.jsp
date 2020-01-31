<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consignment Details</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
<link rel="stylesheet" href="style/view_consignment.css" type="text/css" />

</head>
<body>
   <%@ include file="header.jsp" %>
   <section class="section">
    <div class="div_title">
       <span><a href="CreateConsignment"
            class="add">Create Consignments </a></span>
    </div>
    <div class="div_title">
        <form action = "ViewConsignment">
<div class="warehouse">
            <span><label for="wareHouseOption">Warehouse :</label> <select
                name="wareHouse">
               <option value ="${wareHouseIdOption}" selected="selected">${wareHouseLocationOption}</option>
                <option value="All">All</option><c:forEach items="${wareHouse}" var="wareHouse">
                    <option value="${wareHouse.getId()}" >${wareHouse.getLocation()}</option></c:forEach>

            </select> </span> <span> <label for="source">From/To :</label> <select name="source">
                           <option value ="${selectedFromToOption}" selected="selected">${selectedFromToOption}</option>          
                    <option value="All">All</option>
                    <option value="from">from</option>
                    <option value="to">to</option>
                   
            </select>
            </span>
            <input type="submit" value="search"></div>
        </form>

    </div>
    <div class="div_table">
        <table class="table">


            <tr>
                <th>Consignment Id</th>
                <th>From Ware House</th>
                <th>To Ware House</th>
                <th>Status</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${consignmentList}" var="consignment">
                <tr>
                    <td>${consignment.getConsignmentId()}</td>
                    <td>${consignment.getFromWareHouse().getLocation()}</td>
                    <td>${consignment.getToWareHouse().getLocation()}</td>
                    <td>${consignment.getConsignmentStatus()}</td>
                    <td><a href="UpdateConsignment?consignmentId=${consignment.getConsignmentId()}"
                        class="update">Update</a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</section>
    <%@ include file="footer.jsp" %>
</body>
</html>
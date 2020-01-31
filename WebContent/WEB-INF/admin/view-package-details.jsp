<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Package Registration</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/view_package.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
  <%@ include file="header.jsp" %>
  <section class="section">
    <div class="search">
    <form method="get">
          <input style="margin-left: 5px;" type="submit" value="AllPackages" formaction="ViewPackageDetails">
    </form> 
    <form method="get">
    <input type="number" name="search" required="required" class="searchBox" id="search" placeholder="Enter Package Id">
     <input type="submit" value="Search" formaction="SearchPackage">
    </form>
   
    </div>
    <c:if test="${package_status}">
        <h2 class="status">Package Details Updated</h2>
    </c:if>
  
    <div>
        <div class="table_header">
            <div>Package Id</div>
            <div>User Name</div>
            <div>Book Date</div>
            <div>Sender Address</div>
            <div>Receiver Address</div>
            <div>Location</div>
            <div>Status</div>
            <div>Cost</div>
        </div>
        <div class="full">
        <c:choose>
        <c:when test="${search}">
         <c:set value="${packages}" var="packageDetails"></c:set>
         <c:if test="${empty packages}"><h2 style="font-size: 25px;color: red;text-align: center;">package id not available</h2></c:if>
         <div class="row">
                    <div>${packageDetails.getPackageId()}</div>
                    <div>${packageDetails.getUserName().getUserName()}</div>
                    <div><f:formatDate value="${packageDetails.getBookDate()}" pattern="dd/MM/yyyy"/>  </div>
                    <div>${packageDetails.getSenderAddress()}</div>
                    <div>${packageDetails.getReceiverAddress()}</div>
                    <div>${packageDetails.getWarehouseLocation().getLocation()}</div>
                    <div>${packageDetails.getStatus()}</div>
                    <div>${packageDetails.getCost()}</div>
                    <c:if test="${not empty packages}">
                    <div><a href="PackageUpdate?id=${packageDetails.getPackageId()}" class="update">Update</a>
                    
                    </div>
                    </c:if>
                    </div>
        </c:when>
        <c:otherwise>
                    <c:forEach items="${packageDetails}" var="packageDetails">
                    <c:choose>
                    <c:when test="${fn:contains(packageDetails.getStatus(),'Delivered')}"></c:when>
                    <c:otherwise>
                       <div class="row">
                    <div>${packageDetails.getPackageId()}</div>
                    <div>${packageDetails.getUserName().getUserName()}</div>
                   <div><f:formatDate value="${packageDetails.getBookDate()}" pattern="dd/MM/yyyy"/>  </div>
                    <div>${packageDetails.getSenderAddress()}</div>
                    <div>${packageDetails.getReceiverAddress()}</div>
                    <div>${packageDetails.getWarehouseLocation().getLocation()}</div>
                    <div>${packageDetails.getStatus()}</div>
                    <div>${packageDetails.getCost()}</div>
                    <div>
                        <a href="PackageUpdate?id=${packageDetails.getPackageId()}" class="update">Update</a>
                    </div>

                    </div>
                    </c:otherwise>
                    </c:choose>
             
            </c:forEach>
        </c:otherwise>
        </c:choose>

        </div>
    </div>
    </section>
<%@ include file="footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Package Update</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/package_update.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body class="body">
    <header>
        <%@include file="header.jsp"%>
    </header>
    <section class="section">
    <div class="padding">
        <div class="form">
                  <form action="PackageSave" method="post">
                <c:set value="${packages}" var="packages"></c:set>
                <div class="row">
                    <div>
                        <label for="packageId">Package Id</label>
                    </div>
                    <div>
                        <input type="text" name="packageId" id="packageId" class="field" required
                            readonly="readonly" value="${packages.getPackageId()}" />
                    </div>
                </div>
                <div class="row">
                    <div>
                        <label for="location">Location</label>
                    </div>
                    <div>
                        <select name="location" id="location" class="field">
                            <option value="${packages.getWarehouseLocation().getLocation()}">${packages.getWarehouseLocation().getLocation()}</option>
                            <c:forEach items="${locationList}" var="location">
                                <option value="${location.getLocation()}">${location.getLocation()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div>
                        <label for="status">Status</label>
                    </div>
                    <div>
                        <select name="status" id="status" required class="field">
                            <option value="${packages.getStatus()}">${packages.getStatus()}</option>
                            <option value="In Transit">In Transit</option>
                            <option value="In Delivery">In Delivery</option>
                            <option value="Delivered">Delivered</option>
                        </select>
                    </div>
                    </div>
                    <div class="row">
                        <input type="submit" name="save" value="Save" class="button" />
                    </div>
            </form>
        </div></div>
    </section>
    <footer>
        <%@include file="footer.jsp"%>
    </footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
    isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/staff_home_page.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
    <%@ include file="header.jsp"%>
    <section class="section">
        <div class="name">
            <c:set var="employeeSession" value="${employeeSession}"></c:set>
            <div class="firstName">
                <h2>Welcome ${employeeSession.getFirstName()}!!!</h2>
                <img src="images/smile.jfif" height="25px" width="30px" class="smile">
            </div>
        </div>

        <div class="flex-container">
            <div class="gallery">
                <a href="PackageRegistration"><img class="image" src="images/UpdatePackage.jpg"
                    alt="Update Package image"></a> <a href="PackageRegistration" class="desc">Package
                    Registration</a>
            </div>

            <div class="gallery">
                <a href="ViewPackageDetails"><img class="image" src="images/package-view.jpg"
                    alt="View Package Details"></a> <a href="ViewPackageDetails" class="desc">View
                    Package Details</a>
            </div>
            <div class="gallery">
                <a href="ViewConsignment"><img class="image" src="images/consignupdate.jpg"
                    alt="Consign Update image"></a> <a href="ViewConsignment" class="desc">Consignment</a>
            </div>

            <div class="gallery">
                <a href="ViewPolicy"><img class="image" src="images/view-policy.png"
                    alt="View Policy image"></a> <a href="ViewPolicy" class="desc">View Policy</a>
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/user_home_page.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<section class="section">
<div class="in_section">
<div class="name">
    <c:set var="userSession" value="${userSession}"></c:set>
    <div class="firstName"><h2>Welcome ${userSession.getFirstName()}!!!</h2>
    <img src="images/smile.jfif" height="25px" width="30px" class="smile"></div>
    </div>
<div class="flex-container">
<div class="gallery">
  <a href="PackageView">
    <img class="image" src="images/courier-package.jpg" alt="package deatils" width="600" height="400"></a>
  <a href="PackageView" class="desc">Package Details</a>
   </div>
<div class="gallery">
  <a href="UserProfile">
    <img class="image" src="images/view-edit-your-details.PNG" alt="view your details " width="600" height="400"></a>
  <a href="UserProfile" class="desc">View Profile</a>
</div>
<div class="gallery">
  <a href="TrackPackage">
    <img class="image" src="images/track-package-status.png" alt="Track package Status" width="600" height="400"></a>
  <a href="TrackPackage" class="desc">Track package Status</a>
  </div>
  
  <div class="gallery">
  <a href="ViewPolicy">
    <img class="image" src="images/view-policy.png" alt="View Policy" width="600" height="400"></a>
  <a href="ViewPolicy" class="desc">View Policy</a>
  </div>
  </div>
  </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
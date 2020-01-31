<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" href="style/index_header.css" type="text/css" />
</head>
<body>
	 <header class="header">
		<figure>
			<img src="images/logo.jpg" alt="logo image" class="logo_img" />
		</figure>
		<p class="web_name">Courier Tracking System </p>
            <c:set var="employeeSession" value="${employeeSession}"></c:set>
    		<c:if test="${fn:contains(employeeSession.getLoginType(),'a')}">
            <a href="LogOut?role=a" class="about">LogOut</a><a href="AdminHome" class="about">Home</a> 
            </c:if>
            <c:set var="employeeSession" value="${employeeSession}"></c:set>
            <c:if test="${fn:contains(employeeSession.getLoginType(),'s')}">
             <a href="LogOut?role=s" class="about">LogOut</a><a href="StaffHome" class="about">Home</a>
            </c:if>
            <c:set var="userSession" value="${userSession}"></c:set>
            <c:if test="${fn:contains(userSession.getActive(),'y')}">
            <a href="LogOut?role=u" class="about">LogOut</a> <a href="UserHome" class="about">Home</a>
            </c:if>
      
    </header>
</body>
</html>
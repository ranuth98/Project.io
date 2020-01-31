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
           
            <a href="LogOut?role=a" class="about">LogOut</a><a href="SuperAdminHome" class="about">Home</a> 
      
    </header>
</body>
</html>
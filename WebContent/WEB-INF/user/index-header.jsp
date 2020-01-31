<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index header</title>
<link rel="stylesheet" href="style/index_header.css" type="text/css" />
</head>
<body>
       <header class="header">
		<figure>
			<img src="images/logo.jpg" alt="logo image" class="logo_img" />
		</figure>
		<p class="web_name">Courier Tracking System </p>
		
		<a href="Login?role=u" class="about">User</a>
		<a href="Login?role=s" class="about"> Staff<span class="pipe">|</span> </a>
		<a href="Login?role=a" class="about"> Admin <span class="pipe">|</span></a>
		<a href="AboutUs" class="about"> About Us<span class="pipe">|</span></a>
		<a href="Index" class="home"> Home<span class="pipe">|</span></a>
	</header>
</body>
</html>
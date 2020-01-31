<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/admin_home_page.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
    <%@ include file="super-admin-header.jsp"%>
    <section class="section">
        <div class="name">
            <div class="firstName">
                <h2>Welcome Super Admin!!!</h2>
                <img src="images/smile.jfif" height="25px" width="30px" class="smile">
            </div>
        </div>
        <div class="gallery">
            <a href="ViewAdminRegistrationRequest"><img class="image"
                src="images/ApproveStaff2.png" alt="Approve Staff image"></a> <a
                href="ViewAdminRegistrationRequest" class="desc">Approve Admin</a>
        </div>
    </section>
    <%@ include file="footer.jsp"%>
</body>
</html>
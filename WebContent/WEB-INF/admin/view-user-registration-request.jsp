<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View User Registration</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/view_user_registration_request.css" type="text/css" />
<script type="text/javascript" src="js/registration_salary_validate.js"></script>
</head>
<body>
   <%@ include file="header.jsp" %>
    <section class="section">
    <div class="padding">
        <c:if test="${approval_status == true}">
            <span class="status">Approved successfully</span>
        </c:if>
        <c:if test="${denied_status == true}">
            <span class="status">Rejected successfully</span>
        </c:if></div>
        <div class="main">
            <div class="main_table">

                <div class="table_header" style="position: sticky; top: 0">
                    <div class="table_head">User Id</div>
                    <div class="table_head">First Name</div>
                    <div class="table_head">Last Name</div>
                    <div class="table_head">Gender</div>
                    <div class="table_head">Contact</div>
                </div>

                <div class="table">
                 <c:if test="${empty userList}">
                            <p class="pending_status">No Pending Request!!!</p>
                        </c:if>
                    <c:forEach var="userList" items="${userList}">
                       
                        <div class="table_row">
                            <div class="table_row">${userList.getId()}</div>
                            <div class="table_row">${userList.getFirstName()}</div>
                            <div class="table_row">${userList.getLastName()}</div>
                            <div class="table_row">${userList.getGender()}</div>
                            <div class="table_row">${userList.getContactNumber()}</div>
                            <div class="table_row">
                                <a href="UserApproval?id=${userList.getId()}"> <img
                                    src="images/approve.jfif" class="image">
                                </a>
                            </div>
                            <div class="table_row">
                                <a href="UserReject?id=${userList.getId()}" class="image" onclick="return confirmDelete()" > <img
                                    src="images/denied.png" class="image">
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp" %>
</body>
</html>
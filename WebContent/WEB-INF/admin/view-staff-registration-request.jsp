<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration request</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/view_staff_registration_request.css" type="text/css" />
<script type="text/javascript" src="js/registration_salary_validate.js"></script>

</head>
<body>
    <%@ include file="header.jsp" %>
    <section class="section">
    <div class="padding">
        <c:if test="${approveStatus == true}">
            <h2 class="status">Approved Successfully</h2>
        </c:if>

        <c:if test="${removeStatus == true}">
            <h2 class="status">Rejected Successfully</h2>
        </c:if></div>
        <div class="main">
            <div class="main-table">

                <div class="table_header" style="position: sticky; top: 0">
                    <div class="table_head">First Name</div>
                    <div class="table_head">Last Name</div>
                    <div class="table_head">Gender</div>
                    <div class="table_head">Contact</div>
                    <div class="table_head">Designation</div>
                    <div class="table_head">Salary</div>
                </div>

                <div class="table">
                    <c:if test="${empty employeeList}">
                        <p class="pending_status">No Pending Request!!!</p>
                    </c:if>
                    <c:forEach var="employeeList" items="${employeeList}">

                        <form action="StaffApproval" name="approve-admin"
                            onsubmit="return salaryValidate()" method="get">

                            <div class="table_row">
                                <div class="table_row">${employeeList.getFirstName()}</div>
                                <div class="table_row">${employeeList.getLastName()}</div>
                                <div class="table_row">${employeeList.getGender()}</div>
                                <div class="table_row">${employeeList.getContactNumber()}</div>
                                <div class="table_row">${employeeList.getDesignation()}</div>
                                <div class="table_row">
                                    <input type="tel" maxlength="10" name="salary" class="salary" />
                                </div>
                                <div class="table_row">
                                    <input type="image" src="images/approve.jfif" class="check_image">
                                </div>
                                <input type="hidden" name="employeeId" value="${employeeList.getEmployeeId()}" />
                                <div class="table_row">
                                    <a href="StaffReject?employeeId=${employeeList.getEmployeeId()}" class="check_image" onclick="return confirmDelete()">
                                        <img src="images/denied.png" class="check_image">
                                    </a>
                                </div>
                            </div>
                        </form>
                    </c:forEach>

                </div>
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp" %>
</body>
</html>
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
    <%@ include file="header.jsp"%>
     <section class="section">
    <div class="name">
    <c:set var="employeeSession" value="${employeeSession}"></c:set>
    <div class="firstName"><h2>Welcome ${employeeSession.getFirstName()}!!!</h2>
    <img src="images/smile.jfif" height="25px" width="30px" class="smile"></div>
    </div>
   
        <div class="flex-container">

            <div class="gallery">
                <a href="ViewStaffRegistrationRequest"><img class="image"
                    src="images/ApproveStaff2.png" alt="Approve Staff image"></a> <a
                    href="ViewStaffRegistrationRequest" class="desc">Approve Staff</a>
            </div>

            <div class="gallery">
                <a href="ViewUserRegistrationRequest"><img class="image"
                    src="images/APPROVEUSER2.PNG" alt="Approve User image"></a> <a
                    href="ViewUserRegistrationRequest" class="desc">Approve User</a>
            </div>
            
             <div class="gallery">
                <a href="ShowStaffList"><img class="image"
                    src="images/update_staff_details.jfif" alt="Update Staff image"></a> <a
                    href="ShowStaffList" class="desc">Modify Staff Details</a>
            </div>
            
            <div class="gallery">
                <a href="ShowWareHouseDetails?status=0"><img class="image" src="images/addWarehouse.png"
                    alt="Warehouse image"></a> <a href="ShowWareHouseDetails?status=0" class="desc">Warehouse</a>
            </div>

           

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
                <a href="ParcelTypeView"><img class="image" src="images/update-parcel-type.png"
                    alt="Update parcel image"></a> <a href="ParcelTypeView" class="desc">Update
                    Parcel</a>
            </div>

            <div class="gallery">
                <a href="QuotationView"><img class="image" src="images/quotation.png"
                    alt="Quotation"></a> <a href="QuotationView" class="desc">Quotation
                    updation</a>
            </div>

            <div class="gallery">
                <a href="ViewConsignment"><img class="image" src="images/consignupdate.jpg"
                    alt="Consign Update image"></a> <a href="ViewConsignment" class="desc">Consignment</a>
            </div>

            <div class="gallery">
                <a href="EditPolicy"><img class="image" src="images/view-policy.png"
                    alt="View Policy image"></a> <a href="EditPolicy" class="desc">Update Policy</a>
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp"%>
</body>
</html>

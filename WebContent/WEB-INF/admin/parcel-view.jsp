<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Parcel</title>
<meta name='viewport'
    content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/parcel_update.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
      <%@ include file="header.jsp" %>
      <section class="section">
    <div class="sectiondiv">
        <div>
            <p>
                <a href="ParcelUpdate?parcelId=0" class="addparcel">Add New Parcel</a>
            </p>
        </div>
        <c:choose>
            <c:when test="${empty parcel}">
                <p class="noparcel">No Parcel Types Available!</p>
            </c:when>
            <c:otherwise>
                <div class="maintable">
                    <div class="tableheader">
                        <div>Parcel Type</div>
                        <div>Price Per Kg</div>
                    </div>


                    <div class="rowdata">
                        <c:forEach items="${parcel}" var="parcel">
                            <div class="row">
                                <div>${parcel.getParcelName()}</div>
                                <div>Rs. ${parcel.getPrice()}</div>

                                <div>
                                    <a href="ParcelUpdate?parcelId=${parcel.getId()}" class="update">Update</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>
            </c:otherwise>
        </c:choose>
    </div>
    </section>
      <%@ include file="footer.jsp" %>
</body>
</html>
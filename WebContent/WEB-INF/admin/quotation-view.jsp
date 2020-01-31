<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Quotation</title>
<meta name='viewport'
    content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/quotation_update.css" type="text/css" />
<script type="text/javascript" src="js/back_button_disable.js"></script>
</head>
<body>
   <%@ include file="header.jsp" %>
   <section class="section">
    <div class="sectiondiv">
        <div>
            <p>
                <a href="QuotationUpdate?quotationId=0" class="addquotation">Add New Quotation</a>
            </p>
        </div>
        <c:choose>
            <c:when test="${empty quotation}">
                <p class="noquotation">No Quotation Available!</p>
            </c:when>
            <c:otherwise>
                <div class="maintable">
                    <div class="tableheader">
                        <div>Distance Range</div>
                        <div>Courier Charges</div>
                    </div>
                    <div class="rowdata">
                        <c:forEach items="${quotation}" var="quotation" varStatus="loop">
                            <div class="row">
                                <c:choose>
                                    <c:when test="${loop.index == 0}">
                                        <div>1 - ${quotation.getDistance()} Kms</div>
                                        <c:set var="minDistance" value="${quotation.getDistance()}" />
                                    </c:when>
                                    <c:otherwise>
                                        <div>${minDistance }-${quotation.getDistance()} Kms</div>
                                        <c:set var="minDistance" value="${quotation.getDistance()}" />
                                    </c:otherwise>
                                </c:choose>
                                <div>Rs. ${quotation.getPrice()}</div>
                                <div>
                                    <a href="QuotationUpdate?quotationId=${quotation.getId()}"
                                        class="update">Update</a>
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
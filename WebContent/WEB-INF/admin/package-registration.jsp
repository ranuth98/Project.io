<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Package Registration</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" href="style/package_registration.css" type="text/css" />
</head>
<body>
<%@include file="package-header.jsp" %>
      <section class="section">
       
       <c:choose>
       <c:when test="${create_status}">  
       <div class="generate">
      <h2  class="status">Package Registration Successful</h2>
      <h2 class="bill"><a href="GenerateBill?id=${pa_id}" class="billLink" >Generate Bill....</a> </h2></div>
       </c:when>
       <c:otherwise>
       <div class="generate">
       <c:if test="${distance_status}">
       <h2 class="status">Distance Range Not Available Please Check Quotation
       </h2></c:if>
        <c:if test="${user_name_status}">
       <h2 class="status">UserName Not Available
       </h2></c:if></div>
       <div class="formclass">
    <form  method="post" class="form">
    <c:set value="${packages}" var="packages"></c:set>
        <table>
            <tr>
                <td><label for="userName">User Name</label></td>
                <td><input type="text" required="required"  class="field" name="userName" id="userName" value="${packages.getUserName().getUserName()}"></td>
            </tr>
            <c:set value="${parcelType}" var="parcelType"></c:set>
              <tr>
              <td> <label for="parcelType">ParcelType</label></td>
                <td><select name="parcelType" id="parcelType"
                                  required="required" class="field" >
                                         <option value="${packages.getParcelType().getParcelName() }">${packages.getParcelType().getParcelName() }</option>
                                         <c:forEach items="${parcelType}" var="parcelType">
                                         <option value="${parcelType.getParcelName()}">${parcelType.getParcelName()}</option>
                                         </c:forEach>
                                         
             </select></td>

            </tr>
           
            <tr>
                <td><label for="bookDate">Book Date</label></td>

                <td>
                <f:formatDate value="${packages.getBookDate()}" var="date" pattern="dd/MM/yyyy"></f:formatDate>
                   <input type="text"  required="required"   class="field" name="bookDate" id="bookDate" placeholder="dd/mm/yyyy" value="${date}"></td>
            </tr>

            <tr>
                <td><label for="packageWeight">Package Weight (Kg)</label></td>
                <td><input type="number"  required="required"   class="field" name="packageWeight" step=".1" id="packageWeight" value="${packages.getPackageWeight()}"></td>
            </tr>
             <tr>
                <td><label for="distance">Distance (km)</label></td>
                <td><input type="number"  required="required"  class="field" name="distance" id="distance" value="${packages.getDistance()}"></td>
            </tr>
              <tr>
                <td><label for="senderAddress">Sender Address</label></td>
                <td><textarea   required="required"  class="field" rows="4" cols="40" name="senderAddress" id="senderAddress" ><c:out value="${packages.getSenderAddress()}"/> </textarea></td>
            </tr>
             <tr>
                <td><label for="receiverAddress">Receiver Address</label></td>
                <td><textarea  required="required" class="field"  rows="4" cols="40" name="receiverAddress" id="receiverAddress" ><c:out value="${packages.getReceiverAddress()}"/></textarea></td>
            </tr>
            <c:set value="${warehouse}" var="warehouse"></c:set>
             <tr>
                <td><label for="location">Location</label></td>
                <td><select class="field" name="location">
                <option value="${packages.getWarehouseLocation().getLocation()}">${packages.getWarehouseLocation().getLocation()}</option>
                <c:forEach items="${warehouse}" var="warehouse">
                <option>${warehouse.getLocation()}</option>
                </c:forEach>
                </select></td>
            </tr>
                
            <tr>
            <td><label for="status">Status</label></td>
            <td><input type="text" name="status" id="status" value="Booked" readonly="readonly" class="field"></td>
            </tr>
            <tr>
            <td></td>
            <td><input type="image" class="calculate" src="images/calculate.jpg" formaction="CalculateBill" name="calculate">
           <span class="text"> Calculate bill amount click here!!!</span></td>
            </tr>
            
            <tr>
                <td><label for="cost">Cost (Rs.)</label></td>
                
                
                <td><input class="field"  type="text" name="cost" id="cost" value="${cost}" readonly="readonly"></td>
            </tr>
            
            <tr>
             <c:if test="${status==false}">
            <tr>
            <td></td>
            <td><input type="submit" name="submit" formaction="" class="submit" >
            </td>
            </tr>
            </c:if>
            <tr>
            <c:if test="${status==true}">
            <td></td>
                <td><input type="submit" name="submit" formaction="AddNewPackage" class="submit"></td>
            </c:if>
            </tr>
           
        </table>
    </form>
    </div>
       </c:otherwise>
       </c:choose>
       </section>
      <%@ include file="footer.jsp" %>
</body>
</html>
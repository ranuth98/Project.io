<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice</title>
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="style/invoice.css" />
</head>
<body class="body">
    <div class="div">
        <header class="header">
            <h1>Invoice</h1>
        </header>
        <hr>
        <p class="title">Courier Service Pvt. Ltd.</p>
        <c:set value="${packages}" var="packages"></c:set>
        <div class="data_div">

            <div class="data">
                <div class="row">Package Id</div>
            
                <div>:${packages.getPackageId()}</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Customer Name </label>
                </div>
              
                <div>:${packages.getUserName().getUserName()}</div>
            </div >
            <div class="data">
                <div class="row">
                    <label>Sender Address </label>
                </div>
              
                <div>:${packages.getSenderAddress()}</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Parcel Type</label>
                </div>
            
                <div>:${packages.getParcelType().getParcelName()}</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Booked Date </label>
                </div>
              
                <div>:${packages.getBookDate()}</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Parcel Weight </label>
                </div>
         
                <div>:${packages.getPackageWeight()}Kg</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Receiver Address </label>
                </div>
              
                <div>:${packages.getReceiverAddress()}</div>
            </div>
            <div class="data">
                <div class="row">
                    <label>Price (incl. of all taxes) </label>
                </div>
           
                <div>:Rs. ${packages.getCost()}</div>
            </div>
        </div>
        <hr>
        <div>
            <input type="submit" class="button" value="Print" onClick="window.print()" />
        </div>
        <footer></footer>
    </div>
</body>
</html>
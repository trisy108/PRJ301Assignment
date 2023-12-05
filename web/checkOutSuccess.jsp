<%-- 
    Document   : checkOutSuccess
    Created on : Jul 11, 2023, 9:38:59 PM
    Author     : Admin
--%>

<%@page import="sytt.registration.RegistrationtblProduct"%>
<%@page import="sytt.cart.CartObj"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
<c:set var="orderID" value="${sessionScope.OrderID}"/>
            <c:set var="datetime" value="${sessionScope.DateTime}"/>
            <c:set var="total" value="${sessionScope.total}"/>
            oderID:${orderID}<br/>
            date:${datetime}<br/>
<c:set var="YourOrderDetailList" value="${sessionScope.YourOrderDetailList}"/>
        <c:if test="${not empty YourOrderDetailList}">
            
            
            <table border="1">
                <thead>
                    <tr>
                    <th>Name of Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>total each item</th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach var="orderDetail" items="${YourOrderDetailList}">
                         <c:set var="sku" value="${orderDetail.sku}"/> 
                         <c:set var="Product" value="${cart.getProductbySku(sku)}"/>
                         
                    <tr>
                        <td>${Product.name}</td>
                        <td>${orderDetail.price}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.total}</td>
                        
                    </tr>  
                    
                    </c:forEach>
                    <tr>
                        <td colspan="3">total amount</td>
                        <td>${total}</td>
                    </tr>
                </tbody>
            </table>

        </c:if>
    </body>
</html>

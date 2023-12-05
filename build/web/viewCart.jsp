<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 1:36:46 PM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="sytt.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Java Book Store</h1>
        <c:set var="session" value="${sessionScope}"/>
        <c:if test="${ session != null}">
            <!-- 2.cust take cart -->
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:set var="searchvalue" value="${param.lastProductSearch}"/>
            <!-- 3.cust get items -->
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${items ne null}">
            <!-- 4.cust pick all items up -->
            <form action="DispatchServlet">
           
                <table border="1">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>name</th>
                        <th>price</th>
                        <th>quantity</th>
                        <th>action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="key" items="${items.keySet()}" varStatus="counter">
                    <tr>
                        <td>${counter.count}
                           </td>
                         <td>${cart.getProductbySku(key).getName()}</td>
                         <td>${cart.getProductbySku(key).getPrice()}</td>
                         <td>${items.get(key)}</td>
                         <td>
                            <input type="checkbox" name="checkItem" 
                                value="${key}" />
                         </td>
                    </tr>
                   </c:forEach>
                    <tr>
                        <td colspan="3">
                             <c:url var="addmorecart" value="DispatchServlet">
                                            <c:param name="btAction" value="Search_Product"></c:param>
                                            <c:param name="txtSearch_Product" value="${searchvalue}"></c:param>
                                        </c:url>
                            <a href="${addmorecart}">add more item to cart</a>
                        </td>
                        <td>
                            <input type="submit" value="remove" name="btAction" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="checkout" name="btAction"  />
                        </td>
                    </tr>
                </tbody>
            </table>
                 </form>
        </c:if>
            <c:if test="${ items == null}">
                <h2> no recomend is mached!!!</h2>
            </c:if> 
    </c:if>           
</c:if>
            <c:if test="${ session == null}">
                <h2> no recomend is mached!!!</h2>
            </c:if>
        <%--<%
            //1.Cust goes his/her cart's place
            if (session != null) {
                //2.Cust takes his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3.Cust get items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4.Cust picks all items up
        %>
        <h2>Your cart includes</h2>
        <form action="DispatchServlet">   
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                            .</td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>

                        </td>
                    </tr>
                    <%
                        }//tranverse each item
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.html">Add More Items to Your Cart</a>
                        </td>
                        <td> html
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%
                        return;
                    }//end items have extisted
                }
            }
        %>--%>
        
    </body>
</html>

<%-- 
    Document   : SearchProduct
    Created on : Jun 24, 2023, 1:08:46 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="sytt.registration.RegistrationtblProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Product!</h1>
        <form action="DispatchServlet" >
            Search: <input type="text" name="txtSearch_Product" 
                           value="${param.txtSearch_Product}" />
            <input type="submit" value="Search_Product" name="btAction" />
        </form><br/>
        <c:set var="searchvalue" value="${param.txtSearch_Product}" ></c:set>
        <c:if test="${not empty searchvalue}">
            <c:set var="result" value="${requestScope.SEARCH_PRODUCT_RESULT}"></c:set>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>select</th>
                            <th>No.</th>
                            <th>quantity</th>
                            <th>name</th>
                            <th>price</th>
                            <th>status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="productDTO" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    <c:set var="sku" value="${productDTO.sku}"></c:set>
                                    <input type="checkbox" name="check" value="${sku}" />
                                </td>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="number" name="quantity" value="${productDTO.quantity}" />
                                </td>
                                <td>${productDTO.name}</td>
                                <td>${productDTO.price}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${productDTO.status}">
                                            stocking
                                        </c:when>
                                        <c:otherwise>
                                            sold out
                                        </c:otherwise>
                                    </c:choose>
                                </td>    
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <input type="submit" value="AddItem" name="btAction" />
                                <input type="hidden" name="lastProductSearch" value="${searchvalue}" />
                            </td>
                            <td>
                                <input type="submit" value="ViewCart" name="btAction" />
                            </td>
                        </tr>
                    </form>

                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                no recomend is matched!!!
            </h2>
        </c:if>    
    </c:if>








    <%--  <form action="DispatchServlet" >
        Search: <input type="text" name="txtSearch_Product" 
                       value="<%= request.getParameter("txtSearch_Product")%>" />
        <input type="submit" value="Search_Product" name="btAction" />
    </form>
         <br/>
    <%
        String searchValue = request.getParameter("txtSearch_Product");
        if (searchValue != null) {
            List<RegistrationtblProduct> result = 
                    (List<RegistrationtblProduct>) request.getAttribute("SEARCH_PRODUCT_RESULT");
            if (result != null) 
            {//search found at least record
    %>
    <form action="DispatchServlet">
    <table border="1">
        <thead>
            <tr>
                <th>select</th>
                <th>No.</th>
                <th>quantity</th>
                <th>name</th>
                <th>price</th>
                <th>status</th>
                
            </tr>
        </thead>
         
        <tbody>
      
            <%
                int count = 0;
                for (RegistrationtblProduct tpProduct : result) {
                   
            %>
            
            <tr>
                <td><input type="checkbox" name="check" value="<%= tpProduct.getSku() %>" /></td>
                
                <td><%= ++count %></td>
                <td> <input type="number" name="quantity" value="<%= request.getParameter("quantity")%>" /> </td>
                <td><%= tpProduct.getName() %></td>
                <td><%= tpProduct.getPrice() %></td>
                <td>
                    <% if (tpProduct.isStatus()) { %>
                        stocking
                    <% } else { %>
                        sold out
                        <% } %>
                </td>
            </tr>                
                      
            <%
                }

                %>
                <tr>
            <td colspan="3">
                <input type="submit" value="AddItem" name="btAction" />
                <input type="hidden" name="lastProductSearch" value="<%= searchValue%>" />
            </td>
            <td>
            <input type="submit" value="ViewCart" name="btAction" />
            </td>
            </tr>
            </tbody>
        </table>
                
            </form>
            
        <%            }
        else { //search did not found
        %> 
        <h2>
            No record is matched!!!!
        </h2>
  
        
        <%
                }
            }//end acess directly or first access this page


        %> --%>


</body>
</html>

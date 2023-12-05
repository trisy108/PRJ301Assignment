<%-- 
    Document   : Search
    Created on : Jun 9, 2023, 1:06:39 PM
    Author     : Admin
--%>

<%@page import="sytt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullname}
        </font>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <form action="DispatchServlet">
            <input type="submit" value="logout" name="btAction" />
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"></c:set>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"></c:set>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>                   
                            <th>Delete</th>                   
                            <th>Update</th>                   
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername"
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword"
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    ${dto.role}
                                    <input type="checkbox" name="chekrole" value="ON" 
                                           ${dto.role ? 'checked="checked"' : ''}/>

                                </td>         
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"></c:param>
                                        <c:param name="pk" value="${dto.username}"></c:param>
                                        <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction"/>
                                    <input type="hidden" name="lastSearchValue" value="${searchvalue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
    </c:if>      
    <%--<% 
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        Cookie newestCookie = cookies[cookies.length - 1];
        String username = newestCookie.getName();
    %>
    <font color="red">
    Welcome, <%= username %>
    <%
        }
        %>
    <h1>Search Page</h1>
    <form action="DispatchServlet">
        Search Value <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue")%>" /><br/>
        <input type="submit" value="Search" name="btAction" />
    </form>
    <br/>
    <%
        String searchValue = request.getParameter("txtSearchValue");

            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>)//DTO la dang serializable nen phai ep kieu tuong minh de chuyen ve dang thuong
                        request.getAttribute("SEARCH_RESULT");

                if (result != null) {//Search fount at least 1 record
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                    </td>
                    <td>
                        <%= dto.getPassword()%>
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <%= dto.isRole()%>
                    </td>
                    <td>
                        <a href="<%=urlRewriting%>">Delete</a>
                    </td>
                </tr>
                <%
                    }//end process each dto
                %>
            </tbody>
        </table>

        <%
        } else {//search did not found
        %>
        <h2>
            No record is matched !!!
        </h2>
        <%
                }

            }//end access directly or first access this page
        %>--%>    
</body>
</html>

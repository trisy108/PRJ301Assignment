<%-- 
    Document   : CreateNewAccount
    Created on : Jun 27, 2023, 2:32:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create account</h1>
        <form action="DispatchServlet">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}" />
            Username: <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6-20character)<br/>
            <c:if test="${not empty errors}">
                <c:set var="usenameLenghterror" value="${errors.userLengthError}" />
                <c:if test="${not empty usenameLenghterror}">
                    <font style="color: red">${usenameLenghterror}</font><br/>
                </c:if>
            </c:if>
            Password: <input type="text" name="txtPassword" value="" />(8-30character)<br/>
            <c:if test="${not empty errors}">
                <c:set var="passwordLengthError" value="${errors.passwordLengthError}" />
                <c:if test="${not empty passwordLengthError}">
                    <font style="color: red">${passwordLengthError}</font><br/>
                </c:if>
            </c:if>
            Confirm: <input type="text" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors}">
                <c:set var="confirmNotmatch" value="${errors.confirmNotmatch}" />
                <c:if test="${not empty confirmNotmatch}">
                    <font style="color: red">${confirmNotmatch}</font><br/>
                </c:if>
            </c:if>
            Full name: <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(2-50character)<br/>
             <c:if test="${not empty errors}">
                <c:set var="fullnameLengthError" value="${errors.fullnameLengthError}" />
                <c:if test="${not empty fullnameLengthError}">
                    <font style="color: red">${fullnameLengthError}</font><br/>
                </c:if>
            </c:if>
            <input type="submit" value="createAcount" name="btAction" />
            <input type="submit" value="reset" name="btAction" />
        </form>
             <c:if test="${not empty errors}">
                <c:set var="usernameExits" value="${errors.usernameExits}" />
                <c:if test="${not empty usernameExits}">
                    <font style="color: red">${usernameExits}</font><br/>
                </c:if>
            </c:if>
    </body>
</html>

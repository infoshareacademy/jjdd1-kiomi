<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 14.05.17
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Managing of promoted brands</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/loginpage.css">

</head>
<body>

<form action="/managebrand" method="post">
    Add promoted part brand: <input type="text" name="promotedBrandToAdd">

    <input type="submit" value="Submit"/>
</form>

<form action="/managebrand" method="post">
    Delete promoted part brand:<input type="text" name="promotedBrandToRemove"/>

    <input type="submit" value="Submit"/>
</form>

<p>Here you will see the list of promoted brands:</p>

<div>
    <c:choose>
        <c:when test="${not empty promotedBrandsList}">
            <ul>
                <c:forEach items="${promotedBrandsList}" var="brands">
                    <li>${brands.brand}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
                 <p>
                    There is no promoted brands.
                </p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>

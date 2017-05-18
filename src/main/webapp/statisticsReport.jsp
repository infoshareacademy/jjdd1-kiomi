<%--<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Title</title>
</head>
<body>


        <table border="1">
            <tr>
                <th>Brand</th>
                <th>Total count</th>
            </tr>

            <c:forEach var="value" items="${brandAndCountMap}">
                <tr>
                    <td><c:out value="${value.key}" /></td>
                    <td><c:out value="${value.value}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <table border="1">
            <tr>
                <th>Model</th>
                <th>Total count</th>
            </tr>

            <c:forEach var="value" items="${modelAndCountMap}">
                <tr>
                    <td><c:out value="${value.key}" /></td>
                    <td><c:out value="${value.value}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <table border="1">
            <tr>
                <th>Type</th>
                <th>Total count</th>
            </tr>

            <c:forEach var="value" items="${typeAndCountMap}">
                <tr>
                    <td><c:out value="${value.key}" /></td>
                    <td><c:out value="${value.value}" /></td>
                </tr>
            </c:forEach>
        </table>

<br>

List of all Statistics:</br></br>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Car Brand</th>
        <th>Car Model</th>
        <th>Car Type</th>
        <th>Part Category</th>
        <th>Part Brand</th>
        <th>Part Name</th>
        <th>Part Serial</th>
        <%--<th>Total</th>--%>
    </tr>

    <c:forEach var="value" items="${listOfAllStatistics}">
        <tr>
            <td><c:out value="${value.id}" /></td>
            <td><c:out value="${value.carBrand}" /></td>
            <td><c:out value="${value.carModel}" /></td>
            <td><c:out value="${value.carType}" /></td>
            <td><c:out value="${value.partCategory}" /></td>
            <td><c:out value="${value.partBrand}" /></td>
            <td><c:out value="${value.partName}" /></td>
            <td><c:out value="${value.partSerial}" /></td>
                <%--<td> /></td>--%>
        </tr>
    </c:forEach>
</table>


</body>
</html>

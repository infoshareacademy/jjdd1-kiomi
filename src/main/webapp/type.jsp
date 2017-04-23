<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>KIOMI</title>
</head>
<body>

Welcome to <strong>KIOMI parts market</strong></br></br>

<%--<form action ="servletModel" method="GET">--%>

    <%--Choose Type ID of the car/part: <input type="text" name="userTypeId"/>--%>
    <%--<input type="submit" value="search"/>--%>

<%--</form>--%>

List of Types of Brand <strong>'<%=request.getParameter("userBrandName")%></strong>' of Model: <strong>'<%=request.getParameter("userModelId")%></strong>' <br/><br/>
<table border="1">
    <tr>
        <th>Type Id</th>
        <th>Brand Id</th>
        <th>Model Id</th>
        <th>Name</th>
        <th>Start month</th>
        <%--<th>Start year</th>--%>
        <th>End month</th>
        <%--<th>End year</th>--%>
        <%--<th>Ccm</th>--%>
        <%--<th>Kw</th>--%>
        <%--<th>Cylinders</th>--%>
        <th>Engine</th>
        <th>Engine txt</th>
        <th>Fuel</th>
        <th>Body</th>
        <th>Axle</th>
        <th>Max weight</th>
        <th>Link</th>
    </tr>

    <c:forEach var="type" items="${listOfTypes}">
        <tr>
            <td>${type.getId()}</td>
            <td>${type.getBrand_id()}</td>
            <td>${type.getModel_id()}</td>
            <td>${type.getName()}</td>
            <td>${type.getStart_month()}</td>
            <%--<td>${type.getStart_year()}</td>--%>
            <td>${type.getEnd_month()}</td>
            <%--<td>${type.getCcm()}</td>--%>
            <%--<td>${type.getKw()}</td>--%>
            <%--<td>${type.getCylinders()}</td>--%>
            <td>${type.getEngine()}</td>
            <td>${type.getEngine_txt()}</td>
            <td>${type.getFuel()}</td>
            <td>${type.getBody()}</td>
            <td>${type.getAxle()}</td>
            <td>${type.getMax_weight()}</td>
            <td>${type.getLink()}</td>
        </tr>
    </c:forEach>
</table>

</br>
Date: <%= new java.util.Date() %>

</body>
</html>

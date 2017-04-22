<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>KIOMI</title>
</head>
<body>

Welcome to <strong>KIOMI parts market</strong></br></br>

<form action ="servletBrand" method="GET">

    Choose Type ID of the car/part: <input type="text" name="userBrandName"/>
    <input type="submit" value="search"/>

</form>

List of Parts <strong>'<%=request.getParameter("userBrandName")%></strong>': <br/><br/>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Start_month</th>
        <th>Start_month</th>
        <th>End month</th>
        <th>End year</th>
        <th>Vehicle_group</th>
        <th>Link</th>
    </tr>
    <c:forEach var="model" items="${listOfModels}">
        <tr>
            <td>${model.getId()}</td>
            <td>${model.getName()}</td>
            <td>${model.getStart_month()}</td>
            <td>${model.getStart_year()}</td>
            <td>${model.getEnd_month()}</td>
            <td>${model.getEnd_year()}</td>
            <td>${model.getVehicle_group()}</td>
            <td>${model.getLink()}</td>
        </tr>
    </c:forEach>
</table>

</br> Date: <%= new java.util.Date() %>

</body>
</html>

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

    Choose Brand of the car/part: <input type="text" name="userBrandName"/>
    <input type="submit" value="search"/>

</form>

<%--List of Brand <strong>'<%=request.getParameter("userBrandName")%></strong>': <br/><br/>--%>

<c:forEach var="brand" items="${listOfBrands}">
    id:  ${brand.getId()}  | name:  ${brand.getName()} | clear name: ${brand.getName_clear()}
    | has image: ${brand.getHas_image()} | link: ${brand.getLink()}<br/>
</c:forEach>

</hr>

</br>
Date: <%= new java.util.Date() %>

</body>
</html>

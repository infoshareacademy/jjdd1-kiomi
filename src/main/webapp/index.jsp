<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>KIOMI</title>
</head>
<body>

Welcome to KIOMI parts market</br></br>


<form action ="servlet" method="GET">

    Brand of the car/part: <input type="text" name="brand"/>
    <input type="submit" value="search"/>

</form>

<c:forEach var="brand" items="${listOFBrands}">
    id:  ${brand.getId()} <br/>
    name:  ${brand.getName()}<br/>
    clear name: ${brand.getName_clear()}<br/>
    has image: ${brand.getHas_image()}<br/>
    link: ${brand.getLink()}<br/>
    ------------- <br/>
</c:forEach>

</hr>





witeczka <%= new java.util.Date() %>



</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <p>
        wybór brandu
    </p>
</div>
<div>
    ${errorMessage}<br>
    ${brand}
    <br>
</div>
<div class="formField">
    <label>Model</label>
    <select name="brand">
        <c:forEach items="${modelList}" var="element">
            <option value="${element.id}" <c:if
                    test="${element.id == param.brand}"> selected</c:if>>${element.name} ${element.id}</option>
        </c:forEach>
    </select>
</div>

</body>
</html>

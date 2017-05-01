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
${errorMessage}<br>
${brand} - ${model}
<br>
<c:if test="${not empty typeList}">
    <div class="formField">

        <label>Typ silnika</label>
        <select name="type">
            <c:forEach items="${typeList}" var="element">
                <option value="${element.id}" >${element.name} ${typeList.size()}</option>
            </c:forEach>
        </select>
    </div>
</c:if>
</body>
</html>

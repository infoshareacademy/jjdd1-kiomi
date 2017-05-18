<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="icon" href="img/tabicon.png">

<html>
<head>
    <title>Wyszukiwarka części</title>

    <style>
        .topHeader {
            width: 100%;
            border-bottom: 1px solid #ddd;
        }

        .formField {
            display: inline-block;
        }

        .menu {
            width: 200px;
            border-right: 1px solid #ddd;
            margin-right: 20px;
            display: inline-block;
            padding: 10px;
            float: left;
        }

        .partList {
            display: inline-block;
            width: calc(100% - 300px);
            padding: 10px;
            float: right;
        }

        .breadcrumbs {
            width: 100%;
        }
        .breadcrumbs li {
            display: inline-block;
            list-style: none;
        }
    </style>
</head>
<body>

<header class="topHeader">
    <h2>Podaj dane do wyszukiwania części</h2>
    <form method="get" action="/index">
        <div class="formField">
            <label>Marka</label>
            <select name="brand">
                <c:forEach items="${brandList}" var="element">
                    <option value="${element.id}" <c:if
                            test="${element.id == param.brand}"> selected</c:if>>${element.name} ${element.id}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${not empty modelList}">
            <div class="formField">

                <label>Model</label>
                <select name="model">
                    <c:forEach items="${modelList}" var="element">
                        <option value="${element.id}" <c:if
                                test="${element.id == param.model}"> selected</c:if>>${element.name} ${element.id}</option>
                    </c:forEach>
                </select>
            </div>
        </c:if>
        <c:if test="${not empty typeList}">
            <div class="formField">

                <label>Typ silnika</label>
                <select name="type">
                    <c:forEach items="${typeList}" var="element">
                        <option value="${element.id}" <c:if
                                test="${element.id == param.type}"> selected</c:if>>${element.name}</option>
                    </c:forEach>
                </select>
            </div>
        </c:if>
        <div class="formField">

            <input type="submit" value="szukaj">
        </div>
    </form>
    <div class="breadcrumbs">
        <ul>

            <c:forEach items="${brandList}" var="element">
                <c:if test="${element.id== param.brand}">
                    <li>>> <a href="${baseURL}?brand=${element.id}">${element.name}</a></li>
                </c:if>
            </c:forEach>
            <c:forEach items="${modelList}" var="element">
                <c:if test="${element.id== param.model}">
                    <li>>> <a href="${baseURL}?brand=${param.brand}&model=${element.id}">${element.name}</a></li>
                </c:if>
            </c:forEach>
            <c:forEach items="${typeList}" var="element">
                <c:if test="${element.id== param.type}">
                    <li>>> <a
                            href="${baseURL}?brand=${param.brand}&model=${param.model}&type=${element.id}">${element.name}</a>
                    </li>
                </c:if>
            </c:forEach>

            <%--<c:forEach items="${menuList}" var="element">--%>
                <%--<c:if test="${element eq paramValues.cat}">--%>
                    <%--<li>>> <a--%>
                            <%--href="${baseURL}?BRAND=${param.BRAND}&model=${param.model}&type=${param.model}">${element.name}nb</a>--%>
                    <%--</li>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
<c:forEach items="${paramValues.cat}" var="el">
<c:forEach items="${menuList}" var="element">
                <c:if test="${element.id ==el}">
                    <li>>> <a
                            href="${baseURL}?brand=${param.brand}&model=${param.model}&type=${param.model}&cat=${element.id}">${element.name}nb</a>
                    </li>
                </c:if>
            </c:forEach>
            </c:forEach>


        </ul>

    </div>
</header>
<div class="main">
    <div class="menu">
        <c:choose>
            <c:when test="${not empty menuList}">
                <ul>
                    <c:forEach items="${menuList}" var="element">
                        <c:choose>
                            <c:when test="${element.has_children==true}">
                                <li><a href="${url}&cat=${element.id}">${element.name}</a></li>
                            </c:when>
                            <c:otherwise>
                            <%--<li>${element.name}</li>--%>
                                <li><a href="${url}&cat=${element.id}&stock=1">${element.name}</a></li>

                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>

                <c:if test="${not empty param.type}">
                    <p>
                        Nie ma kategorii.
                    </p>

                </c:if>

            </c:otherwise>
        </c:choose>
    </div>
    <div class="partList">


        <c:choose>
            <c:when test="${fn:length(partList) gt 1}">
                <ul>
                    <c:forEach items="${partList}" var="element">
                        <!--<li> <a href="https://allegro.pl/listing?string=${element.brand}%20${element.number}&description=1&order=m&bmatch=base-relevance-floki-5-nga-uni-1-2-0222" target="_blank">${element.number} ${element.name}</a></li>-->
                        <li> <a href="/redirect?partbrand=${element.brand}&number=${element.number}" target="_blank">${element.number} ${element.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>
                    Tutaj zobaczysz wyniki wyszukiwania części
                </p>
            </c:otherwise>
        </c:choose>


    </div>
</div>

</body>
</html>
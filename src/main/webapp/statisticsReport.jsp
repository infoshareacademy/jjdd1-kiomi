<%--<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #clear{
            clear:both;
        }
    </style>
</head>
<body>
<b>Statistics Report:</b><br><br>
        <div style="display: inline-block;float:left;margin-right:5px;">
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
        </div>
        <div style="display: inline-block;float:left;margin-right:5px;">
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
        </div>
        <div style="display: inline-block;float:left;margin-right:5px;">
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
        </div>
        <div style="display: inline-block;float:left;margin-right:5px;">
            <table border="1">
                <tr>
                    <th>Part Brand</th>
                    <th>Total count</th>
                </tr>

                <c:forEach var="value" items="${partBrandAndCountMap}">
                    <tr>
                        <td><c:out value="${value.key}"/></td>
                        <td><c:out value="${value.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="display: inline-block;float:left;margin-right:5px;">
            <table border="1">
                <tr>
                    <th>Part Category</th>
                    <th>Total count</th>
                </tr>

                <c:forEach var="value" items="${partCategoryAndCountMap}">
                    <tr>
                        <td><c:out value="${value.key}"/></td>
                        <td><c:out value="${value.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="display: inline-block;float:left;margin-right:5px;">
            <table border="1">
                <tr>
                    <th>Part name</th>
                    <th>Total count</th>
                </tr>

                <c:forEach var="value" items="${partNameAndCountMap}">
                    <tr>
                        <td><c:out value="${value.key}"/></td>
                        <td><c:out value="${value.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
<div id="clear"></div>
<hr>
<b>List of all Statistics:</b></br></br>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Car Brand</th>
        <th>Car Model</th>
        <th>Car Type</th>
        <th>Part Brand</th>
        <th>Part Category</th>
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
            <td><c:out value="${value.partBrand}" /></td>
            <td><c:out value="${value.partCategory}" /></td>
            <td><c:out value="${value.partName}" /></td>
            <td><c:out value="${value.partSerial}" /></td>
                <%--<td> /></td>--%>
        </tr>
    </c:forEach>
</table>


</body>
</html>

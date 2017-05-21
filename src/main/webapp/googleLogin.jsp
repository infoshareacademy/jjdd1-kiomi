<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Autoparts - Zalogu się aby skorzystać z naszej aplikacji!</title>
<link rel="icon" href="img/tabicon.png">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="http://meyerweb.com/eric/tools/css/reset/reset.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="css/loginpage.css">
</head>
<body>
<div class="">
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4" style="display: none">
        Ukryte menu
    </div>
</div>
<div class="">

    <div class="col-xs-12 col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4 col-lg-offset-4 col-lg-4">
        <div class="plate">
            <p class="script"><span>Polish</span></p>
            <p class="shadow text3">AUTOPARTS</p>
            <p class="script"><span>by kiomi</span></p>
            <p class="product-owner"><span>and product owner</span></p>
        </div>
        <div class="col-xs-12">
            <div class="avatar">
                <c:if test="${not empty oauth.picture}">
                    <img src="${oauth.picture}">
                </c:if>

            </div>
            <p class="status">
                <strong><c:if test="${not empty oauth.given_name}">
                    Witaj ${oauth.given_name} ${oauth.family_name}
                </c:if> &nbsp;
                </strong><br>
                <strong>Status:</strong>
                <c:if test="${empty oauth.email}">nie</c:if>zalogowany</p>
            <c:choose>
                <c:when test="${empty oauth.email}">
                    <form method="post">
                        <input name="login" type="hidden" value="1">
                        <input type="submit" value="ZALOGUJ SIĘ" class="btn btn-login btn-lg">
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="caridentitymethod" class="btn btn-login btn-lg">WEJDŹ</a>
                    </br></br>
                    <a href="logout" class="logout-link">Zmień konto/wyloguj</a>
                </c:otherwise>
            </c:choose>
            <c:if test="${not empty error}">
                <div class="errorbox">
                        ${error}
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>

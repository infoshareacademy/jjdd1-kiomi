<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
</html>
<meta charset="UTF-8">
<title>Autoparts</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css" href="http://meyerweb.com/eric/tools/css/reset/reset.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
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
            <div class="boxfield">
                <c:if test="${not empty errorMessage}">
                    <div class="errorbox">
                            ${errorMessage}
                    </div>
                </c:if>
            </div>
            <div class="header-progress-container">
                <ol class="header-progress-list">
                    <li class="header-progress-item done">Marka</li><!--
       -->
                    <li class="header-progress-item done">Model</li><!--
       -->
                    <li class="header-progress-item done">Typ silnika</li>
                </ol>
            </div>
            <div class="lightbox text-center">
                <p class="title">Wybierz typ silnika samochodu</p>
                <p class="btn-margin">
                <div class="selectedData"><i class="fa fa-check" aria-hidden="true"></i> ${brand}</div>
                </p>
                <p class="btn-margin">
                <div class="selectedData"><i class="fa fa-check" aria-hidden="true"></i> ${model}</div>
                </p>
                <p class="btn-margin">
                    <c:choose>
                    <c:when test="${typeList.size()>1}">
                <form>
                    <select name="type" class="form-control">
                        <c:forEach items="${typeList}" var="element">
                            <option value="${element.id}"
                                    <c:if
                                            test="${element.id == param.type}"> selected
                                    </c:if>
                            >${element.name} ${element.id}</option>
                        </c:forEach>
                    </select>
                </form>
                </c:when>
                <c:otherwise>
                    <div class="selectedData"><i class="fa fa-check" aria-hidden="true"></i> ${typeList.get(0).name}</div>
                </c:otherwise>
                </c:choose>
                </p>
                <p class="btn-margin">
                    <a href="formtochoisingmodel" class="btn btn-login btn-lg">Wybierz</a>
                </p>


            </div>
            <div class="lightbox text-left lightbox-top-margin">
                Jakieś dane...
                ${typeList.get(0).name}
            </div>

            <a href="logout" class="logout-link">Zmień konto/wyloguj</a>
        </div>
    </div>
</div>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>

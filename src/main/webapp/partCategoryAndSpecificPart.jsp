<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://java.sun.com/jsf/facelets">
<head>
    <meta charset="UTF-8">
    <title>Autoparts - Części do Twojego pojazdu!</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="css/newWebBody.css"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

    <!-- jQuery js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="icon" href="img/tabicon.png">
</head>
<body>

<div class="container">

    <header>
        <div class="jumbotron" >
            <h1>Autoparts <h4>by team KIOMI</h4></h1>
            <p>"Czy wiesz jaka część jest potrzebna do Twojego auta?"</p>
            <p>"Czy chcesz wiedzieć ile może kosztować zakup części do Twojego pojazdu?"</p>
            <p>"Czy wiesz króry producent oferuje najlepsze części do Twojego motocyklu?"</p>
            <br/>
            <p>Korzystając z naszej usługi znajdziesz odpowiedź na wszystkie pytania dotyczące części do twojego pojazdu.</p>
        </div>
        <br/>
    </header>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Zapraszamy</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Wyszukaj części do Twojego pojazdu<span class="sr-only">(current)</span></a></li>
                    <li><a href="/caridentitymethod" title="Wyszukiwanie pojazd od początku">Nowe wyszukiwanie</a></li>
                    <li><a href="/searchbyaztec" title="Rozpocznij wyszukiwanie po kodzie Aztec">Wyszukiwanie Aztec</a></li>
                    <li><a href="/popularityreport" title="Nasz raport najpopularniejszych części">Raport popularności części</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Witaj ${sessionUserName}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#" title="Funkcjonalność będzie dostępna na podsumowaniu 4-tego sprintu">Panel Administracyjny</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/googlelogin" title="Po wybraniu tej opcji nastąpi przekierowanie na stronę logowania">Wyloguj</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <ol class="breadcrumb">Twój aktualny wybór to:
        <li title="Marka pojazdu"> marka: ${brand}</li>
        <li title="Model pojazdu"> model: ${model}</li>
        <li title="Typ silnika"> typ silnika: ${carType}</li>
    </ol>

    <div class="row">
        <div class="col-lg-6" id="listyKatCz">
            <div class="list-group">
                <a href="#" class="list-group-item" style="font-weight: bold; font-size: 1.2em; text-align: center">Kategorie części</a>
                <c:choose>
                    <c:when test="${not empty partCategories}">
                        <c:forEach items="${partCategories}" var="element">
                            <c:choose>
                                <c:when test="${element.has_children==true}">
                                    <a class="list-group-item" href="choisingpartcategory?cat=${element.id}" align="center" value="${element.id}">${element.name}</a>
                                </c:when>
                                <%--<c:otherwise>--%>
                                    <%--<a class="list-group-item" href="choisingpartcategory?cat=${element.id}&stock=1" align="center" value="${element.id}">${element.name}</a>--%>
                                <%--</c:otherwise>--%>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${not empty param.type}">
                        <a class="list-group-item" align="center" href="#">Lista kategorii jest pusta.</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="col-lg-6" id="listaCzSzcz">
            <div class="list-group">
                <a href="#" class="list-group-item" style="font-weight: bold; font-size: 1.2em; text-align: center">Części szczegółowe</a>
                <c:choose>
                    <c:when test="${fn:length(parts) gt 1}">
                            <c:forEach items="${parts}" var="element">
                                <a class="list-group-item" href="redirect?partcategory=${categoryname}&partname=${element.name}&partserial=${element.number}" target="_blank">
                                        ${element.number} ${element.name}</a>
                            </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <a class="list-group-item" align="center" href="#">Wybierz kategorię aby wyświetlić części</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-default" id="myFooter">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <ul class="nav navbar-nav navbar-left">
                <!--<li><a href="#" title="Funkcjonalność będzie dostępna po 4-tym sprincie szkoleniowym.">Admin</a></li>-->
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" title="Chcesz się z nami skontaktować? Napisz do nas maila" data-toggle="modal" data-target="#daneKONT">Kontakt</a></li>
                <li><a href="#" title="Chcesz dowiedzieć się więcej o team KIOMI?" data-toggle="modal" data-target="#infoKIOMI">Więcej o team KIOMI</a></li>
                <li><a href="#" title="Wersja aplikacji z której korzystasz" data-toggle="modal" data-target="#wersjaAPP">Autoparts</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</div>

<div id="modale">
<!-- Modale -->
<div class="modal fade" id="daneKONT" role="dialog">
    <div class="modal-dialog modal-med">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dane kontaktowe</h4>
            </div>
            <div class="modal-body">
                <p>team KIOMI: teamkiomi@gmail.com</p>
                <p>infoShare Academy: biuro@infoshareacademy.com</p>
                <p>Marketing info: wojtek@infoshareacademy.com</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="infoKIOMI" role="dialog">
    <div class="modal-dialog modal-med">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Informacje o team KIOMI</h4>
            </div>
            <div class="modal-body">
                <p align="center">Zespół powstał podczas kursy Junior Java Developer w infoShare Academy w maju 2017.
                    Efektem intensywnej nauki zagadnień technologicznych oraz ciężkiej pracy członków zespołu jest aplikacja Autoparts.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="wersjaAPP" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Wersja aplikacji Autoparts</h4>
            </div>
            <div class="modal-body">
                <p>Aktualna wersja: v0.3</p>
                <p>Premiera: 15.05.2017r. 16:00, Gdańsk</p>
                <p>Wersja rozwojowa: <a href="https://github.com/infoshareacademy/jjdd1-kiomi/tree/develop">GitHub</a></p>
                <p>Pracują państwo na aplikacji po 3 sprincie szkoleniowym.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</div>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>
</body>
</html>
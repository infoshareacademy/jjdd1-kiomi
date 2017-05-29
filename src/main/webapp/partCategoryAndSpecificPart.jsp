<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://java.sun.com/jsf/facelets">
<head>
    <meta charset="UTF-8">
    <title>Autoparts - Części do Twojego pojazdu!</title>
    <link rel="icon" href="img/tabicon.png">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

    <!-- jQuery js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/loginpage.css"/>

</head>
<body>

<div class="plateb">
    <p class="script"><span>Polish</span></p>
    <p class="shadow text3">AUTOPARTS</p>
    <p class="script"><span>by kiomi</span></p>
    <p class="product-owner"><span>and product owner</span></p>
</div>

<div class="container">
    <div class="lightbox text-center">
        <nav class="navbar navbar-default" id="navbarStyle">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="/index" style="color: #fff">Wyszukaj części do swojego pojazdu, <small>lub:</small></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <%--<li><a href="#" style="font-size: 1.2em; border-left-style: hidden;">Wyszukaj części do Twojego pojazdu<span class="sr-only">(current)</span></a></li>--%>
                        <li><a href="/caridentitymethod" title="Wyszukiwanie pojazd od początku" style="color: #fff">Zmień pojazd</a></li>


                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #fff; font-size: 1.1em;">Witaj ${sessionUserName}<span class="caret"></span></a>
                            <ul class="dropdown-menu" id="dropdownStyle">
                                <li><a href="/logout" title="Po wybraniu tej opcji nastąpi przekierowanie na stronę logowania" style="color: #fff">Wyloguj</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <c:if test="${isAdmin == true}">

            <nav class="navbarStyle navbar navbar-default " id="navbarAdminMenu">
                <div class="container-fluid">

                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/partstatistics" title="Raport popularności części" style="color: #fff">Raport
                                popularności części</a></li>
                            <li><a href="/carstatistics" title="Raport popularności Modeli" style="color: #fff">Raport
                                popularności samochodów</a></li>

                            <li><a href="/promotedbrand" title="Promownae marki" style="color: #fff">Promowane marki</a>
                            </li>

                        </ul>

                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </c:if>
        <ol class="breadcrumb" id="breadcrumbsStyle">Twój aktualny wybór to:
            <li title="Marka pojazdu"> marka: ${brand}</li>
            <li title="Model pojazdu"> model: ${model}</li>
            <li title="Typ silnika"> typ silnika: ${carType}</li>
        </ol>

        <div class="row">
            <div class="col-lg-6" id="listyKatCz">
                <div class="list-group">
                    <div class="list-group-item" id="headKatCz" style="font-weight: bold; font-size: 1.3em; text-align: center">Kategorie oraz podkategorie części</div>
                    <c:choose>
                        <c:when test="${not empty partCategories}">
                            <c:forEach items="${partCategories}" var="element">
                                <c:choose>
                                    <c:when test="${element.has_children==true}">
                                        <a class="list-group-item" id="contaKatCz" href="index?cat=${element.id}" align="center" value="${element.id}">${element.name}</a>
                                    </c:when>
                                    <%--<c:otherwise>--%>
                                    <%--<a class="list-group-item" href="choisingpartcategory?cat=${element.id}&stock=1" align="center" value="${element.id}">${element.name}</a>--%>
                                    <%--</c:otherwise>--%>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${not empty param.type}">
                                <a class="list-group-item" id="contbKatCz" align="center" href="#">Lista kategorii jest pusta.</a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="col-lg-6" id="listaCzSzcz">
                <div class="list-group">
                    <a href="#" class="list-group-item" id="headCzSzcz" style="font-weight: bold; font-size: 1.3em; text-align: center">Części szczegółowe</a>
                    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Wpisz fragment nazwy części aby zawęzić wyniki..">
                    <ul id="myUL">
                        <c:choose>
                            <c:when test="${fn:length(parts) gt 1}">
                                <c:forEach items="${parts}" var="element">
                                    <li><a class="list-group-item" id="contaCzSzcz" href="productdetails?partcategory=${categoryname}&partbrand=${element.brand}&partname=${element.name}&partserial=${element.number}" >${element.name} ${element.brand} (${element.number})</a></li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li><a class="list-group-item" id="contbCzSzcz" align="center" href="#">Wybierz kategorię aby wyświetlić części</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
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
                    <li><a href="#" title="Chcesz się z nami skontaktować? Napisz do nas maila" data-toggle="modal" data-target="#daneKONT" style="color: #fff">Kontakt</a></li>
                    <li><a href="#" title="Chcesz dowiedzieć się więcej o team KIOMI?" data-toggle="modal" data-target="#infoKIOMI" style="color: #fff">Więcej o team KIOMI</a></li>
                    <li><a href="#" title="Wersja aplikacji z której korzystasz" data-toggle="modal" data-target="#wersjaAPP" style="color: #fff">Autoparts</a></li>
                </ul>
            </div><!-- /.container-fluid -->
        </nav>
    </div>
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
                    <p>team KIOMI: kiomi.info@gmail.com</p>
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
                    <p>Aktualna wersja: v0.4</p>
                    <p>Premiera: 29.05.2017r. 16:00, Gdańsk</p>
                    <p>Wersja rozwojowa: <a href="https://github.com/infoshareacademy/jjdd1-kiomi/tree/develop">GitHub</a></p>
                    <p>Pracują państwo na aplikacji po 4 sprincie szkoleniowym.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function myFunction() {
        // Declare variables
        var input, filter, ul, li, a, i;
        input = document.getElementById('myInput');
        filter = input.value.toUpperCase();
        ul = document.getElementById("myUL");
        li = ul.getElementsByTagName('li');

        // Loop through all list items, and hide those who don't match the search query
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("a")[0];
            if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    }
</script>

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
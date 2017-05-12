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
        <div class="jumbotron"  style="background-image: url(img/qweqwer.jpg); background-size: auto; background-clip: border-box">
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
                    <li class="active"><a href="#">Wyszukaj części do Twojego pojazdu: <span class="sr-only">(current)</span></a></li>
                    <li><a href="#" title="Wyszukiwanie pojazd od początku">Nowe wyszukiwanie</a></li>
                    <li><a href="#" title="Rozpocznij wyszukiwanie po kodzie Aztec">Wyszukiwanie Aztec</a></li>
                    <li><a href="#" title="Nasz raport najpopularniejszych części">Raoprt popularności części</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Logout</a></li>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <ol class="breadcrumb">Twój aktualny wybór to:
        <li class="active" title="Marka pojazdu">marka: ${brand}</li>
        <li clsss="active" title="Model pojazdu">model: ${model}</li>
        <li class="active" title="Typ silnika">typ silnika: ${carType}</li>
    </ol>

    <div class="row">
        <div class="col-lg-6"  id="listyKatCz">
            <div class="list-group">
                <a href="#" class="list-group-item" style="font-weight: bold; font-size: 1.2em; text-align: center">Kategorie części</a>
                <%--<a href="#" class="list-group-item">$(iterowana lista części)</a>--%>
                <c:choose>
                    <c:when test="${not empty menuList}">
                        <c:forEach items="${menuList}" var="element">
                            <c:choose>
                                <c:when test="${element.has_children==true}">
                                    <li><a class="list-group-item" href="${url}&cat=${element.id}">${element.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="list-group-item" href="${url}&cat=${element.id}&stock=1">${element.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
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
        </div>

        <div class="col-lg-6" id="listaCzSzcz">
            <div class="list-group">
                <a href="#" class="list-group-item" style="font-weight: bold; font-size: 1.2em; text-align: center">Części szczegółowe</a>
                <%--<a href="#" class="list-group-item">$(iterowana lista części)</a>--%>
                <c:choose>
                    <c:when test="${fn:length(partList) gt 1}">
                        <ul>
                            <c:forEach items="${partList}" var="element">
                                <li> <a class="list-group-item" href="https://allegro.pl/listing?string=${element.brand}%20${element.number}
                                &description=1&order=m&bmatch=base-relevance-floki-5-nga-uni-1-2-0222" target="_blank">
                                        ${element.number} ${element.name}</a></li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <a class="list-group-item" href="#">Wybierz kategorię aby wyświetlić części</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-default" id="myFooter">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <ul class="nav navbar-nav navbar-left">
                <!--<li><a href="#">Logout</a></li>-->
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" title="Chcesz się z nami skontaktować? Napisz do nas maila">Kontakt</a></li>
                <li><a href="#" title="Chcesz dowiedzieć się więcej o team KIOMI?">Więcej o team KIOMI</a></li>
                <li><a href="#" title="Wersja aplikacji z której korzystasz">v.0.3</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
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
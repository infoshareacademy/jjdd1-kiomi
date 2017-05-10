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
<style>
    .fancyNav{
        /* Affects the UL element */
        overflow: hidden;
        display: inline-block;

        border-radius: 4px;
        -moz-border-radius: 4px;
        -webkit-border-radius: 4px;

        box-shadow: 0 0 4px rgba(255, 255, 255, 0.6);
        -moz-box-shadow: 0 0 4px rgba(255, 255, 255, 0.6);
        -webkit-box-shadow: 0 0 4px rgba(255, 255, 255, 0.6);
    }

    .fancyNav li{
        /* Specifying a fallback color and we define CSS3 gradients for the major browsers: */

        background-color: #f0f0f0;
        background-image: -webkit-gradient(linear,left top, left bottom,from(#fefefe), color-stop(0.5,#f0f0f0), color-stop(0.51, #e6e6e6));
        background-image: -moz-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
        background-image: -o-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
        background-image: -ms-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
        background-image: linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);

        border-right: 1px solid rgba(9, 9, 9, 0.125);

        /* Adding a 1px inset highlight for a more polished efect: */

        box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;
        -moz-box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;
        -webkit-box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;

        position:relative;

        float: left;
        list-style: none;
    }
    .fancyNav li:after{

        /* This creates a pseudo element inslide each LI */

        content:'.';
        text-indent:-9999px;
        overflow:hidden;
        position:absolute;
        width:100%;
        height:100%;
        top:0;
        left:0;
        z-index:1;
        opacity:0;

        /* Gradients! */

        background-image:-webkit-gradient(linear, left top, right top, from(rgba(168,168,168,0.5)),color-stop(0.5,rgba(168,168,168,0)), to(rgba(168,168,168,0.5)));
        background-image:-moz-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
        background-image:-o-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
        background-image:-ms-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
        background-image:linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));

        /* Creating borders with box-shadow. Useful, as they don't affect the size of the element. */

        box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;
        -moz-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;
        -webkit-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;

        /* This will create a smooth transition for the opacity property */

        -moz-transition:0.25s all;
        -webkit-transition:0.25s all;
        -o-transition:0.25s all;
        transition:0.25s all;
    }
    /* Treating the first LI and li:after elements separately */

    .fancyNav li:first-child{
        border-radius: 4px 0 0 4px;
    }

    .fancyNav li:first-child:after,
    .fancyNav li.selected:first-child:after{
        box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
        -moz-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
        -webkit-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;

        border-radius:4px 0 0 4px;
    }

    .fancyNav li:last-child{
        border-radius: 0 4px 4px 0;
    }

    /* Treating the last LI and li:after elements separately */

    .fancyNav li:last-child:after,
    .fancyNav li.selected:last-child:after{
        box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
        -moz-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
        -webkit-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;

        border-radius:0 4px 4px 0;
    }

    .fancyNav li:hover:after,
    .fancyNav li.selected:after,
    .fancyNav li:target:after{
        /* This property triggers the CSS3 transition */
        opacity:1;
    }
    .fancyNav:hover li.selected:after,
    .fancyNav:hover li:target:after{
        /* Hides the targeted li when we are hovering on the UL */
        opacity:0;
    }

    .fancyNav li.selected:hover:after,
    .fancyNav li:target:hover:after{
        opacity:1 !important;
    }

    /* Styling the anchor elements */

    .fancyNav li a{
        color: #5d5d5d;
        display: inline-block;
        font: 20px/1 Lobster,Arial,sans-serif;
        padding: 12px 35px 14px;
        position: relative;
        text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.6);
        z-index:2;
        text-decoration:none !important;
        white-space:nowrap;
    }

    .fancyNav a.homeIcon{
        background:url('../img/home.png') no-repeat center center;
        display: block;
        overflow: hidden;
        padding-left: 12px;
        padding-right: 12px;
        text-indent: -9999px;
        width: 16px;
    }
</style>
</head>
<body>

<div class="col-xs-12 col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4 col-lg-offset-4 col-lg-4">
    <div class="plate" style="margin-top: 20px">
        <p class="script"><span>Polish</span></p>
        <p class="shadow text3">AUTOPARTS</p>
        <p class="script"><span>by kiomi</span></p>
        <p class="product-owner"><span>and product owner</span></p>
    </div>
</div>

<div class="ccc col-xs-12" style="background: #fff; color: #333; padding:40px 0">
    <nav class="text-center">
        <ul class="fancyNav">
            <li id="home"><a href="#home"><i class="fa fa-home"></i> </a></li>
            <li id="news"><a href="#news">Wybierz samochód</a></li>
            <li id="about"><a href="#about">Raport popularności części</a></li>
            <li id="services"><a href="#services">Raport popularności samochodów</a></li>
            <li id="contact"><a href="#contact">Wyloguj</a></li>
        </ul>
    </nav>
    <div class="text-center">Aktualnie wybrany samochód: ${brand} ${model} ${carType}</div>
<h3 class="text-center">Raport popularności części</h3>
    <!-- Styles -->
    <style>
        #chartdiv {
            width: 70%;
            height: 500px;
            margin:0 auto
        }

        .amcharts-export-menu-top-right {
            top: 10px;
            right: 0;
        }
    </style>

    <!-- Resources -->
    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="https://www.amcharts.com/lib/3/serial.js"></script>
    <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

    <!-- Chart code -->
    <script>
        var chart = AmCharts.makeChart("chartdiv", {
            "type": "serial",
            "theme": "light",
            "marginRight": 70,
            "dataProvider": [{
                "id": "1",
                "country": "USA",
                "visits": 3025,
                "color": "#FF0F00"
            }, {
                "id": "1",
                "country": "China",
                "visits": 1882,
                "color": "#FF6600"
            }, {
                "id": "1",
                "country": "Japan",
                "visits": 1809,
                "color": "#FF9E01"
            }, {
                "id": "1",
                "country": "Germany",
                "visits": 1322,
                "color": "#FCD202"
            }, {
                "id": "1",
                "country": "UK",
                "visits": 1122,
                "color": "#F8FF01"
            }, {
                "id": "1",
                "country": "France",
                "visits": 1114,
                "color": "#B0DE09"
            }, {
                "id": "1",
                "country": "India",
                "visits": 984,
                "color": "#04D215"
            }, {
                "id": "1",
                "country": "Spain",
                "visits": 711,
                "color": "#0D8ECF"
            }, {
                "id": "1",
                "country": "Netherlands",
                "visits": 665,
                "color": "#0D52D1"
            }, {
                "id": "1",
                "country": "Russia",
                "visits": 580,
                "color": "#2A0CD0"
            }, {
                "id": "1",
                "country": "South Korea",
                "visits": 443,
                "color": "#8A0CCF"
            }, {
                "id": "1",
                "country": "Canada",
                "visits": 441,
                "color": "#CD0D74"
            }],
            "valueAxes": [{
                "axisAlpha": 0,
                "position": "left",
                "title": "Ilość klinkęć w link"
            }],
            "startDuration": 1,
            "graphs": [{
                "balloonText": "<b>[[country]]: [[value]]</b>",
                "fillColorsField": "color",
                "fillAlphas": 0.9,
                "lineAlpha": 0.2,
                "type": "column",
                "valueField": "visits"
            }],
            "chartCursor": {
                "categoryBalloonEnabled": false,
                "cursorAlpha": 0,
                "zoomable": false
            },
            "categoryField": "id",
            "categoryAxis": {
                "gridPosition": "start",
                "labelRotation": 45
            },
            "export": {
                "enabled": true
            }

        });
    </script>

    <!-- HTML -->
    <div id="chartdiv"></div>
    <table class="table table-striped text-center table-bordered table-hover" style="color:#333; width: 70%; margin: 0 auto"> <thead>
<tr>
    <th style="width:20px; text-align: center">#</th>
    <th>Nazwa części</th>
    <th>Ilość kliknięć</th>
    <th>Miesiąc</th>
</tr>
</thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>22</td>
        <td>Marzec</td>
    </tr>
    </tbody>
</table>
</div>
<div>
    <div class="col-xs-12 col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4 col-lg-offset-4 col-lg-4" style="padding:30px 0px; text-align: center">
        Copyright &copy; 2017 by Kiomi Team
    </div>
</div>


</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Autoparts - Wybierz swój pojazd!</title>
<link rel="icon" href="img/tabicon.png">
<meta charset="UTF-8">
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

    <div class="col-xs-12 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6 col-lg-offset-4 col-lg-4">
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
            <br/><br/>
            <div class="header-progress-container">
                <ol class="header-progress-list">
                    <li class="header-progress-item done">Marka</li><!--
       -->
                    <li class="header-progress-item done">Model</li><!--
       -->
                    <li class="header-progress-item todo">Typ silnika</li>
                </ol>
            </div>
            <%--choisingcartype--%>
            <form method="post" action="${action}">
                <div class="lightbox text-center">
                    <p class="title">Wybierz model samochodu</p>
                    <p class="btn-margin">
                    <div class="selectedData"><i class="fa fa-check" aria-hidden="true"></i> ${brand}</div>
                    <input type="hidden" name="brand" value="${brandId}">
                    </p>
                    <c:choose>
                        <c:when test="${modelList.size()>0}">
                            <p class="btn-margin">
                                <select name="model" class="form-control" id="model">
                                    <c:forEach items="${modelList}" var="element">
                                        <option value="${element.id}"
                                                <c:if
                                                        test="${element.id == param.model}"> selected
                                                </c:if>
                                        >${element.name} (${element.start_year} - ${element.end_year})
                                        </option>
                                    </c:forEach>
                                </select>
                            </p>
                            <p class="btn-margin">
                                    <%--<a href="formtochoisingcartype" class="btn btn-login btn-lg">Wybierz</a>--%>
                                <input type="hidden" name="aztec" value="${param.aztec}">
                                <input type="submit" class="btn btn-login btn-lg" value="Wybierz">
                            </p>
                        </c:when>
                        <c:otherwise>
                            <a href="choisingbrand" class="btn btn-login btn-lg">Wyszukaj ponownie</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
            <%--<div class="lightbox text-left lightbox-top-margin">--%>
            <%--Jakieś dane...--%>
            <%--&lt;%&ndash;${modelList.get(0).name}&ndash;%&gt;--%>
            <%--</div>--%>
            </br></br>
            <a href="logout" class="logout-link">Zmień konto/wyloguj</a>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<script>
    $('#model').on('change', function () {
        var selectedId = this.value;
        var url = "http://infoshareacademycom.2find.ru/api/v2/find/";
//        String url2="?lang=polish";

        $.ajax({
            url: "data.json",
            //force to handle it as text
            dataType: "text",
            success: function (data) {
                var json = $.parseJSON(data);
                $(json).data.each(function (i, val) {
                    $.each(val, function (k, v) {
                        console.log(k + " : " + v);
                    });
                });

            }
        });
    });

    //    var items = data.items.map(function (item) {
    //        return item.key + ': ' + item.value;
    //    });
    //    var json = $.parseJSON(j);
    //    $(json).each(function (i, val) {
    //        $.each(val, function (k, v) {
    //            console.log(k + " : " + v);
    //        });
    //    });
    //    $.getJSON('/functions.php', {get_param: 'value'}, function (data) {
    //        $.each(data, function (index, element) {
    //            $('body').append($('<div>', {
    //                text: element.name
    //            }));
    //        });
    //    });
    //    alert(this.value);

</script>
</body>
</html>

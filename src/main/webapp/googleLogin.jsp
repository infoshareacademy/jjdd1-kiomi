<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>
    <%--<script src="https://apis.google.com/js/platform.js" async defer></script>--%>
    <%--<meta name = "google-signin-client_id" content = "10399147182-bi3b5jgcaomu9kcigdhr5nqheitqa511.apps.googleusercontent.com">--%>
</head>
<body>
Zaloguj się
<form method=”GET” action=”googleplus”>
    <button type=”submit”>Login</button>
</form>
<%--<div id="content"></div>--%>
<%--<script>--%>
    <%--function onSignIn(googleUser) {--%>
        <%--var profile = googleUser.getBasicProfile();--%>
        <%--var p = document.createElement('p');--%>
        <%--p.innerHTML +='<img src="'+profile.getImageUrl()+'" width=40px>' ;--%>
        <%--p.innerHTML +='<br>' +profile.getFamilyName();--%>
        <%--p.innerHTML +='<br>' +profile.getName();--%>
        <%--p.innerHTML +='<br>' +profile.getGivenName();--%>
        <%--p.innerHTML +='<br>Mail:' +profile.getEmail();--%>
        <%--document.getElementById('content').appendChild(p);--%>
        <%--var id_token = googleUser.getAuthResponse().id_token;--%>
    <%--};--%>
<%--</script>--%>
<%--<div class="g-signin2" data-onsuccess="onSignIn"></div>--%>

</body>
</html>

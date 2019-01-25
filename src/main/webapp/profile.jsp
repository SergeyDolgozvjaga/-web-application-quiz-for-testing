<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="my_profile"/></title>
    <link href="<c:url value='/css/profile/profile.css'/>" rel="stylesheet">
</head>

<body>

<br>
<div style="text-align: center; margin: 0 auto; border: 3px solid #00905d;width:400px;padding:10px;">
    <br>
    <i18nTag:localization message="user_login"/>:<br> ${loggedUser.login}<br>
    <i18nTag:localization message="user_name"/>:<br> ${loggedUser.userName}<br/>
    <i18nTag:localization message="age"/>:<br> ${loggedUser.age}<br>
    <i18nTag:localization message="email"/>:<br> ${loggedUser.email}<br>
</div>
<br>
<a class="button" href="${pageContext.request.contextPath}/user/profileUpdate.jsp"><i18nTag:localization message="update_profile"/></a>
<br><br><br><br><br><br><br><br><br><br><br><br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>







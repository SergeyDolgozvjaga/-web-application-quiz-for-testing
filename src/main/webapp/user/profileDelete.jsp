<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="delete_profile"/></title>
    <link href="<c:url value='/css/operations/profileDelete.css'/>" rel="stylesheet">

</head>

<body>

<div class="profile-delete">
    <h3><i18nTag:localization message="profile_del_question"/></h3>
</div>

<div style="text-align: center; margin: 0 auto; border: 3px solid #00905d;width:150px;padding:10px;">
    <form style="display: inline" method="post" action="${pageContext.request.contextPath}/user/profileDelete.jsp">
        <button type="submit" class="btn-link"><i18nTag:localization message="yes"/></button>
    </form>
    <form style="display: inline" method="get" action="${pageContext.request.contextPath}/profile.jsp">
        <button type="submit" class="btn-link"><i18nTag:localization message="no"/></button>
    </form>
</div>

</body>
</html>


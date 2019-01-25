<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="<c:url value='/css/user/userResult.css'/>" rel="stylesheet">
<html>
<head>
    <title><i18nTag:localization message="result"/></title>

</head>
<body>

<div class="result">
    <h1><i18nTag:localization message="result"/> : ${percentAmount}%</h1>
</div>

</body>
</html>
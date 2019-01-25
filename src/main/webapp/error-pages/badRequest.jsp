<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <title><i18nTag:localization message="400_title"/></title>
    <link href="<c:url value='/css/error-pages/badRequest.css'/>" rel="stylesheet">

    <jsp:include page="../../header.jsp"></jsp:include>
</head>

<body>
<div class="message_400">
    <h1>
        <i18nTag:localization message="400_message"/>
    </h1>
</div>

<br>
<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>

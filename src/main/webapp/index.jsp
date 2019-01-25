<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="main_page"/></title>
    <link href="css/profile/index.css" rel="stylesheet">
    <jsp:include page="header.jsp"></jsp:include>
</head>

<body>
    <div class="advice">
            <img src="image/quiz.png" width="50%" height="50%">
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

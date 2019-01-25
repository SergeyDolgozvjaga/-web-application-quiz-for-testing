<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="create_test"/></title>

    <link href="<c:url value='/css/admin/test-sector/testSectorCreate.css'/>" rel="stylesheet">

</head>

<body>
<center>
<div style="text-align: center; margin: 0 auto; border: 3px solid #EEE8AA;width:600px;padding:10px;">
    <form method="post" action="${pageContext.request.contextPath}/admin/tests/create">
        <i18nTag:localization message="subject_name"/>:<br>
        <input type="text" name="subject" placeholder="<i18nTag:localization message="subject_name"/>" value="" required><br>
        <i18nTag:localization message="test_name"/>:<br>
        <input type="text" name="name" placeholder="<i18nTag:localization message="test_name"/>" value="" required><br>
        <i18nTag:localization message="difficult_level"/>:<br>
        <input type="text" name="difficultLevel" placeholder="<i18nTag:localization message="difficult_level"/>" value="" required><br>
        <i18nTag:localization message="time_solve_test"/>:<br>
        <input type="text" name="solveTime" placeholder="<i18nTag:localization message="time_solve_test"/>" value="" required><br>
        <hr>
        <i18nTag:localization message="submit"/>:<br>
        <button type="submit" class="btn-link"><i18nTag:localization message="submit"/></button>
    </form>
</div>
</center>

<center>
    <a class="button" href="${pageContext.request.contextPath}/admin/tests"><i18nTag:localization message="go_back"/></a><br>
</center>

</body>
</html>

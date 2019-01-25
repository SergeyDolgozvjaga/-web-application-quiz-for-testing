<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="create_question"/></title>
    <link href="<c:url value='/css/admin/test-question/testQuestionCreate.css'/>" rel="stylesheet">

</head>

<body>


<div style="text-align: center; margin: 0 auto; border: 3px solid #00905d;width:1000px;padding:10px;">
    <form method="post" action="${pageContext.request.contextPath}/admin/tests/questions/create" accept-charset="UTF-8">

        <br><i18nTag:localization message="test_id"/>: <input type="text" name="testID" value="${param.testID}" readonly><br>
        <h4><i18nTag:localization message="question"/>:</h4>
        <br>
        <textarea name="question" rows="6" required></textarea>
        <hr>
        <input type="submit" value="<i18nTag:localization message="submit"/>">
    </form>
</div>

<br>
</body>
</html>

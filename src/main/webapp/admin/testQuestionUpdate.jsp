<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="change_a_question"/></title>
    <link href="<c:url value='/css/admin/test-question/testQuestionUpdate.css'/>" rel="stylesheet">
</head>

<body>
<center>
    <form style="width: 450px" method="post" action="${pageContext.request.contextPath}/admin/question/update" accept-charset="UTF-8">
        <br>
        <i18nTag:localization message="question_id"/>:<br>
        <input type="text" name="testID" value="${questionID}" readonly><br>
        <i18nTag:localization message="question"/>:<br>
        <textarea class="form-control" rows="6" name="question" required></textarea>
        <hr>
        <input type="hidden" name="questionID" value="${questionID}">
        <input type="submit" value="<i18nTag:localization message="submit"/>">
    </form>
</center>

</body>
</html>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="create_answer"/></title>
    <link href="<c:url value='/css/admin/test-answer/testAnswerCreate.css'/>" rel="stylesheet">
</head>

<body>

<center>
<br>
<div style="text-align: center; margin: 0 auto; border: 3px solid #00905d;width:1000px;padding:10px;">
    <form method="post" action="${pageContext.request.contextPath}/admin/tests/answer/create" accept-charset="UTF-8">
        <i18nTag:localization message="question_id"/>:<br>
        <input type="text" name="questionID" value="${param.questionID}" readonly><br>
        <h4><i18nTag:localization message="answer_The_Question"/>:</h4><br>
        <textarea name="answerTheQuestion" rows="6" required></textarea><br>

        <div class="results">
            <input type="hidden" name="IDQuestion" value="${param.questionID}"><br>
            <input type="hidden" name="IDTest" value="${testID}"><br>
            <i18nTag:localization message="is_Answer_Correct"/><br>
            <input type="checkbox" name="isReallyAnswerCorrect" value="true"><br>
        </div>
        <input type="submit" value="<i18nTag:localization message="submit"/>">
    </form>
</div>
</center>

<br><br>

<center>
<a class="button" href="${pageContext.request.contextPath}/admin/questions?testID=${testID}">
    <i18nTag:localization message="go_back"/>
</center>

</body>
</html>


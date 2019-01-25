<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="answer_list"/></title>
    <link href="<c:url value='/css/admin/test-answer/testAnswerCreate.css'/>" rel="stylesheet">
</head>

<body>
<center>
    <table class="table" style="width:450px">
        <thead>
        <tr>
            <th><i18nTag:localization message="answer_id"/></th>
            <th><i18nTag:localization message="answer_The_Question"/></th>
            <th><i18nTag:localization message="is_Answer_Correct"/></th>
        </tr>
        </thead>

        <c:forEach var="answer" items="${answerList}">
            <tbody>
            <tr>
                <th>${answer.answerID}</th>
                <th>${answer.answerTheQuestion}</th>
                <th>${answer.isAnswerCorrect}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</center>

<center>
    <i18nTag:localization message="go_back"/><br>
    <a class="button" href="${pageContext.request.contextPath}/admin/questions?testID=${testID}">
        <i18nTag:localization message="go_back"/>
    </a>
</center>

</body>
</html>

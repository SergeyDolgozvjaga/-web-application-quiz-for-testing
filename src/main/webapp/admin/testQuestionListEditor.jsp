<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="question_list"/></title>
    <link href="<c:url value='/css/admin/test-question/testQuestionListEditor.css'/>" rel="stylesheet">
<body>

<center>
    <c:set var="test" value="${testID}"/>
    <table class="table" style="width:500px">
        <thead>
        <tr>
            <th><i18nTag:localization message="answer_add"/></th>
            <th><i18nTag:localization message="answer_list"/></th>
            <th><i18nTag:localization message="question_id"/></th>
            <th><i18nTag:localization message="question_name"/></th>
            <th><i18nTag:localization message="change_a_question"/></th>
            <th><i18nTag:localization message="question_delete"/></th>

        </tr>
        </thead>
        <c:forEach var="question" items="${questions}">
            <tbody>
            <tr>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/tests/answer/create" method="get">
                        <input type="hidden" name="questionID" value="${question.questionID}">
                        <input type="hidden" name="testID" value="${test}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="answer_add"/></button>
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/answer/list" method="post">
                        <input type="hidden" name="questionID" value="${question.questionID}">
                        <input type="hidden" name="testID" value="${test}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="answer_list"/></button>
                    </form>
                </th>
                <th>${question.questionID}</th>
                <th>${question.question}</th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/question/update" method="get">
                        <input type="hidden" name="questionID" value="${question.questionID}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="change_a_question"/></button>
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/question/delete" method="post">
                        <input type="hidden" name="questionID" value="${question.questionID}">
                        <input type="hidden" name="testID" value="${test}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="question_delete"/></button>
                    </form>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</center>

<center>
    <a class="button" href="${pageContext.request.contextPath}/admin/tests">
        <i18nTag:localization message="back"/>
    </a>
</center>

</body>
</html>

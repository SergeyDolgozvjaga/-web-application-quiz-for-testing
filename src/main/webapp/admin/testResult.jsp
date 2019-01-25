<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <title><i18nTag:localization message="test_results"/></title>
    <link href="<c:url value='/css/admin/test-result/testResult.css'/>" rel="stylesheet">

</head>
<body>

<p><i18nTag:localization message="test_results"/></p>

<div class="results">
    <table class="table" style="width:450px">
        <thead>
        <tr>
            <th><i18nTag:localization message="result_id"/></th>
            <th><i18nTag:localization message="user_id"/></th>
            <th><i18nTag:localization message="test_id"/></th>
            <th><i18nTag:localization message="is_test_done"/></th>
            <th><i18nTag:localization message="time_solve_test"/></th>
        </tr>
        </thead>
        <c:forEach var="result" items="${results}">
            <tbody>
            <tr>
                <th>${result.resultID}</th>
                <th>${result.userID}</th>
                <th>${result.testID}</th>
                <th>${result.isTestDone}</th>
                <th>${result.resultPoints}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>


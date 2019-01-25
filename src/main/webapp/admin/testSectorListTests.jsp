<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18n:localization message="admin_test_list"/></title>
    <link href="<c:url value='/css/admin/test-sector/testSectorListTests.css'/>" rel="stylesheet">
</head>

<body>


<center>
    <table class="table" style="width:500px">
        <thead>
        <tr>
            <th><i18nTag:localization message="test_id"/></th>
            <th><i18nTag:localization message="subject_name"/></th>
            <th><i18nTag:localization message="test_name"/></th>
            <th><i18nTag:localization message="difficult_level"/></th>
            <th><i18nTag:localization message="time_solve_test"/></th>
            <th><i18nTag:localization message="change_test"/></th>
            <th><i18nTag:localization message="delete_test"/></th>
            <th><i18nTag:localization message="add_question"/></th>
            <th><i18nTag:localization message="question_list"/></th>
        </tr>
        </thead>
        <c:forEach var="testsResult" items="${tests}">
            <tbody>
            <tr>
                <th>${testsResult.testID}</th>
                <th>${testsResult.subject}</th>
                <th>${testsResult.name}</th>
                <th>${testsResult.difficultLevel}</th>
                <th>${testsResult.solveTime}</th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/tests/update" method="get">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="change_test"/></button>
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/tests/delete" method="post">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="delete_test"/></button>
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/tests/questions/create" method="get">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="add_question"/></button>
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/admin/questions" method="get">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18nTag:localization message="question_list"/></button>
                    </form>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</center>



</body>
</html>





<div class="testsResult">
    <table>
        <thead>
        <tr>
            <th><i18nTag:localization message="test_id"/></th>
            <th><i18nTag:localization message="subject_name"/></th>
            <th><i18nTag:localization message="test_name"/></th>
            <th><i18nTag:localization message="difficult_level"/></th>
            <th><i18nTag:localization message="time_solve_test"/></th>
            <th><i18nTag:localization message="change_test"/></th>
            <th><i18nTag:localization message="delete_test"/></th>
        </tr>
        </thead>

        <c:forEach var="testsResult" items="${tests}">
            <tbody>
            <tr>
                <th>${testsResult.testID}</th>
                <th>${testsResult.subject}</th>
                <th>${testsResult.name}</th>
                <th>${testsResult.difficultLevel}</th>
                <th>${testsResult.solveTime}</th>
                <th>
                    <!-- test list update -->
                    <form action="${pageContext.request.contextPath}/admin/tests/update" method="get">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18n:localization message="change"/></button>
                    </form>
                </th>
                <th>
                    <!-- testID delete -->
                    <form action="${pageContext.request.contextPath}/admin/tests" method="post">
                        <input type="hidden" name="testID" value="${testsResult.testID}">
                        <button type="submit" class="btn-link"><i18n:localization message="delete"/></button>
                    </form>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
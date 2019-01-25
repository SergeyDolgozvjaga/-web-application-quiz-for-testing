<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="user_test_list"/></title>
    <link href="<c:url value='/css/user/userTests.css'/>" rel="stylesheet">
    <jsp:include page="userHeader.jsp"></jsp:include>
</head>

<body>

<center>
<br>
<div class="sorting">
    <div class="test-sort">
        <form method="get" action="${pageContext.request.contextPath}/user/tests">
            <p><b><i18nTag:localization message="how_you_want_sort_tests"/>?</b></p>
            <select name="sort" size="1">
                <option value="subject"><i18nTag:localization message="subject_name"/></option>
                <option value="name"><i18nTag:localization message="test_name"/></option>
                <option value="difficult_level"><i18nTag:localization message="difficult_level"/></option>
                <option value="questions"><i18nTag:localization message="amount_of_questions"/></option>
            </select>
            <input type="submit" value="<i18nTag:localization message="submit"/>">
        </form>
        <br>
    </div>
</div>
</center>

<br><br>

<center>
<div class="test-result">
        <table>
            <thead>
            <tr>
                <th><i18nTag:localization message="subject_name"/></th>
                <th><i18nTag:localization message="test_name"/></th>
                <th><i18nTag:localization message="difficult_level"/></th>
                <th><i18nTag:localization message="time_solve_test"/></th>
                <th><i18nTag:localization message="user_testing"/></th>
            </tr>
            </thead>
            <c:forEach var="testsResult" items="${allTests}">
                <tbody>
                <tr>
                    <th>${testsResult.subject}</th>
                    <th>${testsResult.name}</th>
                    <th>${testsResult.difficultLevel}</th>
                    <th>${testsResult.solveTime}</th>
                    <th>
                        <form action="${pageContext.request.contextPath}/home/tests/testing" method="post">
                            <input type="hidden" name="testID" value="${testsResult.testID}">
                            <button type="submit" class="button"><i18nTag:localization message="start"/></button>
                        </form>
                    </th>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <br>
</div>
</center>
<br>

</body>
</html>

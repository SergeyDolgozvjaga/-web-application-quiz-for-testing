<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="user_home_page"/></title>
    <link href="<c:url value='/css/user/userHome.css'/>" rel="stylesheet">
    <jsp:include page="userHeader.jsp"></jsp:include>

</head>

<body>

<br>
<div class="sorting">
    <div class="test-sort">
        <form method="get" action="${pageContext.request.contextPath}/user/userHome.jsp">
            <p><b><i18nTag:localization message="how_you_want_sort_tests"/>?</b></p>
            <select name="sort" size="1">
                <option value="subject"><i18nTag:localization message="subject_name"/></option>
                <option value="name"><i18nTag:localization message="test_name"/></option>
                <option value="difficult_level"><i18nTag:localization message="difficult_level"/></option>
                <option value="questions"><i18nTag:localization message="amount_of_questions"/></option>
            </select>
            <input type="submit" value="<i18nTag:localization message="submit"/>">
        </form>
    </div>
</div>
<br>

<center>
<div class="test-result">
        <table style="width:550px">
            <thead>
            <tr>
                <th><i18nTag:localization message="subject_name"/></th>
                <th><i18nTag:localization message="test_name"/></th>
                <th><i18nTag:localization message="difficult_level"/></th>
                <th><i18nTag:localization message="result_points"/></th>
            </tr>
            </thead>
            <c:forEach var="testsResult" items="${testsResultList}">
                <tbody>
                <tr>
                    <th>${testsResult.testSector.subject}</th>
                    <th>${testsResult.testSector.name}</th>
                    <th>${testsResult.testSector.difficultLevel}</th>
                    <th>${testsResult.resultPoints}</th>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <br><br>
</div>
</center>

<br>

</body>
</html>









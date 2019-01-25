<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="admin_home_page"/></title>
    <link href="<c:url value='/css/admin/profile/adminHome.css'/>" rel="stylesheet">
    <jsp:include page="/user/userHeader.jsp"/>
</head>

<body>

<center>
    <jsp:include page="menuHeader.jsp"/>
<div class="admin-home-page">
    <p style="color: #EF3B3A;"><i18nTag:localization message="${error}"/></p>
    <table>
        <thead>
        <tr>
            <th><i18nTag:localization message="user_id"/></th>
            <th><i18nTag:localization message="user_login"/></th>
            <th><i18nTag:localization message="user_name"/></th>
            <th><i18nTag:localization message="email"/></th>
            <th><i18nTag:localization message="banned_status"/></th>
            <th><i18nTag:localization message="change_ban_status"/></th>
        </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tbody>
            <tr>
                <th>${user.userID}</th>
                <th>${user.login}</th>
                <th>${user.userName}</th>
                <th>${user.email}</th>
                <th>${user.isBanned}</th>
                <th>
                    <!-- change ban status -->
                    <form action="${pageContext.request.contextPath}/admin/ban" method="post">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <button type="submit" class="btn"><i18nTag:localization message="сhange"/></button>
                    </form>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<a class="button" href="${pageContext.request.contextPath}/admin/createNewAdmin.jsp"><i18nTag:localization message="admin_create_new"/></a>
<br>
</center>

</body>
</html>


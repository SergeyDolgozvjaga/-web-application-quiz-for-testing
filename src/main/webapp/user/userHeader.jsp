<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../../css/user/userHeader.css" rel="stylesheet">

<header>
    <div class="head">
            <a class="button" href="${pageContext.request.contextPath}/user/userHome.jsp">
                <i18nTag:localization message="user_home_page"/></a>
            <a class="button" href="${pageContext.request.contextPath}/profile.jsp">
                <i18nTag:localization message="my_profile"/></a>
           <a class="button" href="${pageContext.request.contextPath}/logout.jsp">
                <i18nTag:localization message="logout_profile"/></a>
    </div>
</header>

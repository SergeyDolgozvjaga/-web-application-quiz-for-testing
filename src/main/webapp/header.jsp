<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>

    <jsp:include page="language.jsp"></jsp:include>

    <div class="head">
        <i18nTag:localization message="login"/> :
        <a href="${pageContext.request.contextPath}/login.jsp"> <i18nTag:localization message="login"/> </a>
        <br>
        <i18nTag:localization message="registration"/> :
        <a href="${pageContext.request.contextPath}/registration.jsp"> <i18nTag:localization message="registration"/> </a>
    </div>

</header>


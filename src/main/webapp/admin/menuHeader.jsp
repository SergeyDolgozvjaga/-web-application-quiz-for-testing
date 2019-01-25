<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>


<div style="padding: 10px;">
    <a class="button" href="${pageContext.request.contextPath}/admin/adminHome.jsp"><i18nTag:localization message="user_home_page"/></a>
    <a class="button" href="${pageContext.request.contextPath}/admin/testSectorCreate.jsp"><i18nTag:localization message="create_test"/> </a>
    <a class="button" href="${pageContext.request.contextPath}/admin/testSectorListTests.jsp"><i18nTag:localization message="user_test_list"/></a>
    <a class="button" href="${pageContext.request.contextPath}/admin/testResult.jsp"><i18nTag:localization message="test_results"/></a>
</div>
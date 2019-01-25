<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/css/profile/language.css'/>" rel="stylesheet">


<div id="footer">
    <div class="language">
        <form action="change-locale" method="post">

            <i18nTag:localization message="language"/>
            <br>
            <input type="submit" name="RU" value="RU"/>
            <input type="submit" name="EN" value="EN"/>
            <input type="submit" name="UA" value="UA"/>
            <input type="submit" name="DE" value="DE"/>
        </form>
    </div>
</div>
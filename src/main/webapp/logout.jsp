<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="logout_profile"/></title>
</head>

<body>
<br>
<div class="logout-page">
    <div class="form">
        <form class="logout-form" method="post" action="logout" accept-charset="UTF-8">
            <p><i18nTag:localization message="logout_profile"/></p>
            <input type="submit" name="Submit">
        </form>
    </div>
</div>

</body>
</html>

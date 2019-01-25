<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="user_testing"/></title>
    <link href="<c:url value='/css/user/userTesting.css'/>" rel="stylesheet">
</head>

<body>

<center>
<div class="timer" >
    <h2><i18nTag:localization message="time_solve_test"/>: <span id="time"></span></h2>
</div>

<form action="${pageContext.request.contextPath}/user/userResult.jsp" method="post">
    <c:forEach var="question" items="${questions}">
        <div class="question"><i18nTag:localization message="question"/> : ${question.question}</div>
        <c:forEach var="answer" items="${question.testAnswers}">
            <div class="body">
                <div style="text-align: center" class="checkbox">
                    <label>
                        <input type="checkbox" name="${answer.answerID}" value="true">
                            ${answer.answerTheQuestion}
                    </label>
                </div>
            </div>
        </c:forEach>
        <hr>
        <br>
    </c:forEach>
    <button type="submit" class="btn-link"><i18nTag:localization message="submit"/></button>
</form>
<br>
</center>

</body>

<script>
    function setTime(minute) {
        var seconds = 60;

        function tick() {
            var counter = document.getElementById("time");
            var current_minutes = minute - 1;
            seconds--;
            counter.innerHTML = current_minutes.toString() + ":" + (seconds < 10 ? "0" : "") + String(seconds);
            if (seconds > 0) {
                setTimeout(tick, 1000);
            } else {
                if (minute > 1) {
                    setTime(minute - 1);
                } else {
                    document.forms[0].submit();
                }
            }
        }
        tick();
    }
    var minutes = ${test.testTime};
    setTime(minutes);
</script>

</html>





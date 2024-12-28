<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="mailcode" method="post" id="form">
        <p>Please enter your email.</p>
        <div id="feedback"></div>
        <p>
            Email:<input type="text" name="email" id="email">
        </p>
        <input type="submit" name="mailCode" value="Sent MailCode">
    </form>

    <c:if test="${sessionScope.mailFrom == 'register'}">
        <p>Already have an account?</p>
        <a href="signOnForm">Sign In!</a>
    </c:if>
    <c:if test="${sessionScope.mailFrom == 'findPassword'}">
        <p>Remember your password?</p>
        <a href="signOnForm">Sign In!</a>
    </c:if>

</div>

<script src="js/check-mail.js"></script>

<%@ include file="../common/bottom.jsp"%>

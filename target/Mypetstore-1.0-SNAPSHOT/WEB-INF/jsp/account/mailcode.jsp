<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="mailcode" method="post">
        <p>Please enter your email.</p>
        <c:if test="${requestScope.mailcodeMsg != null}">
            <p> <font color="red">${requestScope.mailcodeMsg}</font> </p>
        </c:if>
        <p>
            Email:<input type="text" name="email">
        </p>
        <input type="submit" name="mailCode" value="Sent MailCode">
    </form>
    <c:if test="${sessionScope.isFindPassword == 'false'}">
        Already have an account?
    </c:if>
    <c:if test="${sessionScope.isFindPassword == 'true'}">
        Remember your password?
    </c:if>
    <a href="signOnForm">Sign In!</a>
</div>

<%@ include file="../common/bottom.jsp"%>

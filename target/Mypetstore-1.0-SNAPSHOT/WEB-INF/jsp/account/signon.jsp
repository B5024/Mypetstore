<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p> <font color="red">${requestScope.signOnMsg}</font> </p>
        </c:if>
        <p>
            Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password"> <br />
            Forget your password?
            <a href="mailcodeForm?isFindPassword=true">Find password</a>
        </p>
        <input type="submit" name="Login" value="Login">
    </form>
    Need a username and password?
    <a href="mailcodeForm?isFindPassword=false">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>



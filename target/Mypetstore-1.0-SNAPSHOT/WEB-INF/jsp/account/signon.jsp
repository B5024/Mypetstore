<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="signOn" method="post" id="form">
        <p>Please enter your username and password.</p>
        <div id="feedback"></div>
        <p>
            Username:<input type="text" name="username" id="username"> <br />
            Password:<input type="password" name="password" id="password"> <br />
            Forget your password?
            <a href="mailcodeForm?mailFrom=findPassword" id="findPassword">Find password</a>
        </p>
        <input type="submit" name="Login" value="Login">
    </form>
    Need a username and password?
    <a href="mailcodeForm?mailFrom=register" id="register">Register Now!</a>

</div>

<script src="js/check-signon.js"></script>

<%@ include file="../common/bottom.jsp"%>



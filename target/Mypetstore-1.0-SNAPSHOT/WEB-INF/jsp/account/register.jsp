<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post">
        <p>Please enter your details to create an account.</p>
        <c:if test="${requestScope.registerMsg != null}">
            <p> <font color="red">${requestScope.registerMsg}</font> </p>
        </c:if>
        <p>
            First Name:<input type="text" name="firstname" required> <br />
            Last Name:<input type="text" name="lastname" required> <br />
            Email:<input type="email" name="email" required> <br />
            Username:<input type="text" name="username" required> <br />
            Password:<input type="password" name="password" required> <br />
            Confirm Password:<input type="password" name="confirmPassword" required> <br />
            Address Line 1:<input type="text" name="addr1" required> <br />
            Address Line 2:<input type="text" name="addr2"> <br />
            City:<input type="text" name="city" required> <br />
            State:<input type="text" name="state" required> <br />
            Zip Code:<input type="text" name="zip" required> <br />
            Country:<input type="text" name="country" required> <br />
            Phone:<input type="tel" name="phone" required>
        </p>
        <input type="submit" name="Register" value="Register">
    </form>
    Already have an account?
    <a href="signOnForm">Sign In!</a>

</div>


<%@ include file="../common/bottom.jsp"%>

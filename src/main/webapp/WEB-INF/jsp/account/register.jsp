<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post">
        <p>Please enter your details to create an account.</p>
        <c:if test="${requestScope.registerMsg != null}">
            <p> <font color="red">${requestScope.registerMsg}</font> </p>
        </c:if>
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstname" required></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastname" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><c:out value="${sessionScope.email}" /></td>
            </tr>
            <tr>
                <td>Email Code:</td>
                <td><input type="text" name="emailcode" required></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type="password" name="confirmPassword" required></td>
            </tr>
            <tr>
                <td>Address Line 1:</td>
                <td><input type="text" name="addr1" required></td>
            </tr>
            <tr>
                <td>Address Line 2:</td>
                <td><input type="text" name="addr2"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city" required></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="state" required></td>
            </tr>
            <tr>
                <td>Zip Code:</td>
                <td><input type="text" name="zip" required></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country" required></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="tel" name="phone" required></td>
            </tr>
        </table>

        <input type="submit" name="Register" value="Register">
    </form>
    Already have an account?
    <a href="signOnForm">Sign In!</a>

</div>


<%@ include file="../common/bottom.jsp"%>

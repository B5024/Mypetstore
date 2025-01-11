<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post" id="form">
        <p>Please enter your details to create an account.</p>
        <div id="feedback"></div>
        <table id="register_info">
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstname"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastname"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><c:out value="${sessionScope.email}" /></td>
            </tr>
            <tr>
                <td>Email Code:</td>
                <td><input type="text" name="emailcode" id="emailcode"></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" id="username"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type="password" name="confirmPassword" id="confirmPassword"></td>
            </tr>
            <tr>
                <td>Address Line 1:</td>
                <td><input type="text" name="addr1"></td>
            </tr>
            <tr>
                <td>Address Line 2:</td>
                <td><input type="text" name="addr2"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="state"></td>
            </tr>
            <tr>
                <td>Zip Code:</td>
                <td><input type="text" name="zip"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="tel" name="phone"></td>
            </tr>
        </table>

        <input type="submit" name="Register" value="Register">
    </form>
    Already have an account?
    <a href="signOnForm">Sign In!</a>

</div>

<script src="js/check-register.js"></script>

<%@ include file="../common/bottom.jsp"%>

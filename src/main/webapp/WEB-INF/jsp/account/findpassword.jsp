<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="findPassword" method="post">
        <p>Please enter your email.</p>
        <c:if test="${requestScope.findpasswordMsg != null}">
            <p> <font color="red">${requestScope.findpasswordMsg}</font> </p>
        </c:if>
        <table>
            <tr>
                <td>Email code:</td>
                <td><input type="text" name="emailcode" required></td>
            </tr>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password" required></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type="text" name="confirmPassword" required></td>
            </tr>
        </table>
        <input type="submit" name="findPassword" value="findPassword">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>

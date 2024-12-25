<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="editAccount" method="post">

        <c:if test="${requestScope.editAccountMsg != null}">
            <p> <font color="red">${requestScope.editAccountMsg}</font> </p>
        </c:if>

    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.loginAccount.getUsername()}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="text" name="password" ></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="text" name="confirmPassword" ></td>
        </tr>
    </table>
    <h3>Account Information</h3>

    <table>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="firstname" value="${sessionScope.loginAccount.getFirstName()}"></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="lastname" value="${sessionScope.loginAccount.getLastName()}"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" value="${sessionScope.loginAccount.getEmail()}"></td>
        </tr>
        <tr>
            <td>Address Line 1:</td>
            <td><input type="text" name="addr1" value="${sessionScope.loginAccount.getAddress1()}"></td>
        </tr>
        <tr>
            <td>Address Line 2:</td>
            <td><input type="text" name="addr2" value="${sessionScope.loginAccount.getAddress2()}"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="city" value="${sessionScope.loginAccount.getCity()}"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="state" value="${sessionScope.loginAccount.getState()}"></td>
        </tr>
        <tr>
            <td>Zip Code:</td>
            <td><input type="text" name="zip" value="${sessionScope.loginAccount.getZip()}"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" name="country" value="${sessionScope.loginAccount.getCountry()}"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="tel" name="phone" value="${sessionScope.loginAccount.getPhone()}"></td>
        </tr>
    </table>

    <h3>Profile Information</h3>

    <table>
        <tr>
            <td>Language Preference:</td>
            <td>
                <select name="languagePreference">
                    <option value="english" selected>English</option>
                    <option value="japanese">Japanese</option>
                    <option value="chinese">Chinese</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Favourite Category:</td>
            <td>
                <select name="favouriteCategoryId">
                    <option value="CATS" selected>Cats</option>
                    <option value="BIRDS">Birds</option>
                    <option value="FISH">Fish</option>
                    <option value="DOGS">Dogs</option>
                    <option value="REPTILES">Reptiles</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Enable MyList</td>
            <td>
                <input type="radio" name="listOption" value="true" id="listOptionYes" checked>
                <label for="listOptionYes">Yes</label>

                <input type="radio" name="listOption" value="false" id="listOptionNo" >
                <label for="listOptionNo">No</label>
            </td>
        </tr>
        <tr>
            <td>Enable MyBanner</td>
            <td>
                <input type="radio" name="bannerOption" value="true" id="bannerYes" checked>
                <label for="bannerYes">Yes</label>

                <input type="radio" name="bannerOption" value="false" id="bannerNo" >
                <label for="bannerNo">No</label>
            </td>
        </tr>

    </table>

    <input type="submit" name="editAccount" value="Save Account Information" />

</form>
</div>


<%@ include file="../common/bottom.jsp"%>
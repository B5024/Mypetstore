<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="confirmOrder" method="post">
        <table>
            <tr>
                <th colspan=2>Order</th>
            </tr>
            <tr>
                <th>Billing Address</th>
            </tr>
            <tr>
                <td>First Name:</td>
                <td>
                    ${sessionScope.order1.billToFirstName}
                </td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td>
                    ${sessionScope.order1.billToLastName}
                </td>
            </tr>
            <tr>
                <td>Address1:</td>
                <td>
                    ${sessionScope.order1.billAddress1}
                </td>
            </tr>
            <tr>
                <td>Address2:</td>
                <td>
                    ${sessionScope.order1.billAddress2}
                </td>
            </tr>
            <tr>
                <td>City:</td>
                <td>
                    ${sessionScope.order1.billCity}
                </td>
            </tr>
            <tr>
                <td>State:</td>
                <td>
                    ${sessionScope.order1.billState}
                </td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td>
                    ${sessionScope.order1.billZip}
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    ${sessionScope.order1.billCountry}
                </td>
            </tr>


            <tr>
                <th>Shipping Address</th>
            </tr>
            <tr>
                <td>First Name:</td>
                <td>
                    ${sessionScope.order1.shipToFirstName}
                </td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td>
                    ${sessionScope.order1.shipToLastName}
                </td>
            </tr>
            <tr>
                <td>Address1:</td>
                <td>
                    ${sessionScope.order1.shipAddress1}
                </td>
            </tr>
            <tr>
                <td>Address2:</td>
                <td>
                    ${sessionScope.order1.shipAddress2}
                </td>
            </tr>
            <tr>
                <td>City:</td>
                <td>
                    ${sessionScope.order1.shipCity}
                </td>
            </tr>
            <tr>
                <td>State:</td>
                <td>
                    ${sessionScope.order1.shipState}
                </td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td>
                    ${sessionScope.order1.shipZip}
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    ${sessionScope.order1.shipCountry}
                </td>
            </tr>
        </table>

        <input type="submit" name="confirmOrder" value="Confirm" />

    </form>

</div>

<%@ include file="../common/bottom.jsp"%>

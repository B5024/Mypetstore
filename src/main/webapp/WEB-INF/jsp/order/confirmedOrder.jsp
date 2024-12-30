<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="confirmedOrder" method="post">
        <table>
            <tr>
                <th colspan=5>Order</th>
            </tr>
            <tr>
                <th colspan=5>Payment Details</th>
            </tr>
            <tr>
                <td>Card Type:</td>
                <td colspan=4>
                    ${sessionScope.order1.cardType}
                </td>
            </tr>
            <tr>
                <td>Card Number:</td>
                <td colspan=4>
                   ${sessionScope.order1.creditCard}
                </td>
            </tr>
            <tr>
                <td>Expiry Date (MM/YYYY):</td>
                <td colspan=4>${sessionScope.order1.expiryDate}</td>
            </tr>
            <tr>
                <th colspan=5>Billing Address</th>
            </tr>
            <tr>
                <td>First Name:</td>
                <td colspan=4>
                    ${sessionScope.order1.billToFirstName}
                </td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td colspan=4>
                    ${sessionScope.order1.billToLastName}
                </td>
            </tr>
            <tr>
                <td>Address1:</td>
                <td colspan=4>
                    ${sessionScope.order1.billAddress1}
                </td>
            </tr>
            <tr>
                <td>Address2:</td>
                <td colspan=4>
                    ${sessionScope.order1.billAddress2}
                </td>
            </tr>
            <tr>
                <td>City:</td>
                <td colspan=4>
                    ${sessionScope.order1.billCity}
                </td>
            </tr>
            <tr>
                <td>State:</td>
                <td colspan=4>
                    ${sessionScope.order1.billState}
                </td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td colspan=4>
                    ${sessionScope.order1.billZip}
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td colspan=4>
                    ${sessionScope.order1.billCountry}
                </td>
            </tr>

            <tr>
                <td colspan=5>
                    Shipping Address
                </td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipToFirstName}
                </td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipToLastName}
                </td>
            </tr>
            <tr>
                <td>Address1:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipAddress1}
                </td>
            </tr>
            <tr>
                <td>Address2:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipAddress2}
                </td>
            </tr>
            <tr>
                <td>City:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipCity}
                </td>
            </tr>
            <tr>
                <td>State:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipState}
                </td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipZip}
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td colspan=4>
                    ${sessionScope.order1.shipCountry}
                </td>
            </tr>
            <tr>
                <td>Courier:</td>
                <td colspan=4>UPS</td>
            </tr>
            <tr>
                <td>Status</td>
                <td colspan=4>P</td>
            </tr>
            <tr>
                <th>Item ID</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total Cost</th>
            </tr>
<%--            这里有小问题--%>
            <c:if test="${sessionScope.cart.numberOfItems == 0}">
                <tr>
                    <td colspan="5"><b>Your cartItems is empty.</b></td>
                </tr>
            </c:if>
            <c:forEach var="cartItem"
                    items="${sessionScope.cartItems}">
                <tr>
                    <td>${cartItem.item.itemId}</td>
                    <td>${cartItem.item.attribute1} ${cartItem.item.product.name}</td>
                    <td>${cartItem.quantity}</td>
                    <td>${cartItem.item.listPrice}</td>
                    <td>${cartItem.total}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan=5>Total:$${sessionScope.ChooseLastTotal}</td>
            </tr>
        </table>

    </form>

</div>
<%@ include file="../common/bottom.jsp"%>

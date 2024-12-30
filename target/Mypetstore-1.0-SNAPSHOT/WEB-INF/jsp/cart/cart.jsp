
<%@ include file="../common/top.jsp"%>

<%--<div id="BackLink"><stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">--%>
<%--    Return to Main Menu</stripes:link></div>--%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="overlay" class="overlay"></div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>

        <form action="updateCart" method="post" id="updateForm">
            <table>
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <c:forEach var="cartItem"
                           items="${sessionScope.cart.cartItems}">
                    <tr>
                        <td>
                            <input type="checkbox" data-item-id=${cartItem.item.itemId} id="choose">
                            <a href="itemForm?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                        </td>
                        <td>${cartItem.item.product.productId}</td>
                        <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                        </td>
                        <td>${cartItem.inStock}</td>
                        <td>
                            <input type ="text"
                                   name ="${cartItem.item.itemId}"
                                   value="${cartItem.quantity}"
                                   id   ="quantity">
                        </td>
                        <td id = "listPrice"><fmt:formatNumber value="${cartItem.item.listPrice}"
                                              pattern="$#,##0.00"
                                              />
                        </td>
                        <td id = "totalCost"><fmt:formatNumber value="${cartItem.total}"
                                              pattern="$#,##0.00" />
                        </td>
                        <td>
                            <a href="#"
                               class="Button"
                               id="removeBtn"
                               data-item-id="${cartItem.item.itemId}">Remove
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7" >
                        <span id="subTotal">
                            Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}"
                                                         pattern="$#,##0.00" />
                        </span>
                        <input type="submit"
                               value="Update Cart" id="updateBtn">
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>

        <div id="paymentNewOrder" class="newOrder">
            <div class="newOrder-content">
                <form action="confirmOrder" method="post" id="paymentForm">
                    <table>
                        <tr>
                            <th colspan=2>Payment Details</th>
                        </tr>
                        <tr>
                            <td>Card Type:</td>
                            <td>
                                <select name="cardType">
                                    <option value="Visa" selected="selected">Visa</option>
                                    <option value="MasterCard">MasterCard</option>
                                    <option value="American Express">American Express</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Card Number:</td>
                            <td>
                                <input type="text" name="creditCard" /> * Use a fake number!
                            </td>
                        </tr>
                        <tr>
                            <td>Expiry Date (MM/YYYY):</td>
                            <td><input type="text" name="expiryDate" /></td>
                        </tr>
                        <tr>
                            <th colspan=2>Billing Address</th>
                        </tr>

                        <tr>
                            <td>First name:</td>
                            <td><input type="text" name="billToFirstName" id="firstName"/></td>
                        </tr>
                        <tr>
                            <td>Last name:</td>
                            <td><input type="text" name="billToLastName" id="lastName"/></td>
                        </tr>
                        <tr>
                            <td>Address 1:</td>
                            <td><input type="text" size="40" name="billAddress1" id="Address1"/></td>
                        </tr>
                        <tr>
                            <td>Address 2:</td>
                            <td><input type="text" size="40" name="billAddress2" id="Address2"/></td>
                        </tr>
                        <tr>
                            <td>City:</td>
                            <td><input type="text" name="billCity" id="City"/></td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td><input type="text" size="4" name="billState" id="State"/></td>
                        </tr>
                        <tr>
                            <td>Zip:</td>
                            <td><input type="text" size="10" name="billZip" id="Zip"/></td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td><input type="text" size="15" name="billCountry" id="Country"/></td>
                        </tr>

                        <tr>
                            <td colspan=2>
                                <input type="checkbox" name="shippingAddressRequired"/>
                                Ship to different address...
                            </td>
                        </tr>

                    </table>

                    <div class="buttons">
                        <button type="button" class="continue" id="continueNewOrderButton">Continue</button>
                        <button type="button" class="cancel" id="cancelNewOrderButton">Cancel</button>
                    </div>
                </form>
            </div>
        </div>

        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <button type="button" id="openNewOrderButton">Proceed to Checkout</button>
        </c:if>

        <div id="paymentConfirmOrder" class="confirmOrder">
            <div class="confirmOrder-content">
                <table>
                    <tr>
                        <th colspan=2>Order</th>
                    </tr>
                    <tr>
                        <th colspan=2>Billing Address</th>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td>
                            <span id="confirmFirstName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td>
                            <span id="confirmLastName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Address1:</td>
                        <td>
                            <span id="confirmAddress1"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Address2:</td>
                        <td>
                            <span id="confirmAddress2"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td>
                            <span id="confirmCity"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td>
                            <span id="confirmState"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Zip:</td>
                        <td>
                            <span id="confirmZip"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td>
                            <span id="confirmCountry"></span>
                        </td>
                    </tr>


                    <tr>
                        <th colspan=2>Shipping Address</th>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td>
                            <span id="confirmShipFirstName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td>
                            <span id="confirmShipLastName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Address1:</td>
                        <td>
                            <span id="confirmShipAddress1"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Address2:</td>
                        <td>
                            <span id="confirmShipAddress2"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td>
                            <span id="confirmShipCity"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td>
                            <span id="confirmShipState"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Zip:</td>
                        <td>
                            <span id="confirmShipZip"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td>
                            <span id="confirmShipCountry"></span>
                        </td>
                    </tr>
                </table>

                <input type="hidden" id="aFirstName" value="${sessionScope.loginAccount.firstName}" />
                <input type="hidden" id="aLastName" value="${sessionScope.loginAccount.lastName}" />
                <input type="hidden" id="aAddress1" value="${sessionScope.loginAccount.address1}" />
                <input type="hidden" id="aAddress2" value="${sessionScope.loginAccount.address2}" />
                <input type="hidden" id="aCity" value="${sessionScope.loginAccount.city}" />
                <input type="hidden" id="aState" value="${sessionScope.loginAccount.state}" />
                <input type="hidden" id="aZip" value="${sessionScope.loginAccount.zip}" />
                <input type="hidden" id="aCountry" value="${sessionScope.loginAccount.country}" />


                <button id="confirmOrderButton">Confirm</button>
            </div>
        </div>

    </div>
</div>


        <script>
            const newOrder = document.getElementById('paymentNewOrder');
            const confirmOrder = document.getElementById('paymentConfirmOrder');
            const openNewOrderButton = document.getElementById('openNewOrderButton');
            const continueNewOrderButton = document.getElementById('continueNewOrderButton');
            const cancelNewOrderButton = document.getElementById('cancelNewOrderButton');
            const overlay = document.getElementById('overlay');
            const confirmOrderButton = document.getElementById('confirmOrderButton');

            const confirmFirstName =document.getElementById('confirmFirstName');
            const confirmLastName =document.getElementById('confirmLastName');
            const confirmAddress1 =document.getElementById('confirmAddress1');
            const confirmAddress2 =document.getElementById('confirmAddress2');
            const confirmCity =document.getElementById('confirmCity');
            const confirmState =document.getElementById('confirmState');
            const confirmZip =document.getElementById('confirmZip');
            const confirmCountry =document.getElementById('confirmCountry');
            const confirmShipFirstName =document.getElementById('confirmShipFirstName');
            const confirmShipLastName =document.getElementById('confirmShipLastName');
            const confirmShipAddress1 =document.getElementById('confirmShipAddress1');
            const confirmShipAddress2 =document.getElementById('confirmShipAddress2');
            const confirmShipCity =document.getElementById('confirmShipCity');
            const confirmShipState =document.getElementById('confirmShipState');
            const confirmShipZip =document.getElementById('confirmShipZip');
            const confirmShipCountry =document.getElementById('confirmShipCountry');


            openNewOrderButton.addEventListener('click', () => {
                newOrder.style.display = 'flex';
                overlay.style.display='block';
            });


            continueNewOrderButton.addEventListener('click',()=>{

                const firstName = document.getElementById('firstName').value;
                const lastName = document.getElementById('lastName').value;
                const Address1 = document.getElementById('Address1').value;
                const Address2 = document.getElementById('Address2').value;
                const City = document.getElementById('City').value;
                const State = document.getElementById('State').value;
                const Zip = document.getElementById('Zip').value;
                const Country = document.getElementById('Country').value;

                const shipFirstName = document.getElementById('aFirstName').value;
                const shipLastName = document.getElementById('aLastName').value;
                const shipAddress1 = document.getElementById('aAddress1').value;
                const shipAddress2 = document.getElementById('aAddress2').value;
                const shipCity = document.getElementById('aCity').value;
                const shipState = document.getElementById('aState').value;
                const shipZip = document.getElementById('aZip').value;
                const shipCountry = document.getElementById('aCountry').value;

                confirmFirstName.textContent=firstName;
                confirmLastName.textContent=lastName;
                confirmAddress1.textContent=Address1;
                confirmAddress2.textContent=Address2;
                confirmCity.textContent=City;
                confirmState.textContent=State;
                confirmZip.textContent=Zip;
                confirmCountry.textContent=Country;

                confirmShipFirstName.textContent=shipFirstName;
                confirmShipLastName.textContent=shipLastName;
                confirmShipAddress1.textContent=shipAddress1;
                confirmShipAddress2.textContent=shipAddress2;
                confirmShipCity.textContent=shipCity;
                confirmShipState.textContent=shipState;
                confirmShipZip.textContent=shipZip;
                confirmShipCountry.textContent=shipCountry;


                newOrder.style.display = 'none';
                confirmOrder.style.display = 'flex';

            });

            confirmOrderButton.addEventListener('click', () => {
                const form =document.getElementById('paymentForm');
                confirmOrder.style.display = 'none';
                overlay.style.display='none';

                form.submit();
            });

            cancelNewOrderButton.addEventListener('click', () => {
                newOrder.style.display = 'none';
                overlay.style.display='none';
            });

            window.addEventListener('click', (event) => {
                if (event.target === newOrder) {
                    newOrder.style.display = 'none';
                    overlay.style.display='none';
                }
            });
        </script>


<div id="MyList">
    <c:if test="${sessionScope.loginAccount != null}">
        <c:if test="${!empty sessionScope.loginAccount.listOption}">
            <%@ include file="includeMyList.jsp"%>
        </c:if>
    </c:if>
</div>

<div id="Separator">&nbsp;</div>
</div>

<script src="./js/cart.js?version=0.0.2"></script>
<script src="./js/remote_Item.js?version=0.0.1"></script>
<script src="./js/chooseItem.js"></script>
<%@ include file="../common/bottom.jsp"%>
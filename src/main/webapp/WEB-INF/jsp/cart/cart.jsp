
<%@ include file="../common/top.jsp"%>

<%--<div id="BackLink"><stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">--%>
<%--    Return to Main Menu</stripes:link></div>--%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

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
                            <input type="checkbox" value="${cartItem.item.itemId}">
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
                        <td id><fmt:formatNumber value="${cartItem.item.listPrice}"
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



        <c:if test="${sessionScope.cart.numberOfItems > 0}">
<%--            newOrder--%>
            <a href="newOrderForm"
               class="Button"
               id="checkoutBtn">Proceed to Checkout
</a>
</c:if>
</div>

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
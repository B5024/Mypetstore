</div>
<%--这里匹配的是Context标签--%>
    <div id="Footer">

        <div id="PoweredBy">&nbsp<a href="https://www.csu.edu.cn">www.csu.edu.cn</a>
        </div>

        <div id="Banner">
            <c:if test="${sessionScope.loginAccount != null }">
                <c:if test="${sessionScope.loginAccount.bannerOption}">
                    ${sessionScope.loginAccount.bannerName}
                </c:if>
            </c:if>
        </div>
    </div>

<script src="https://cdn.staticfile.net/jquery/1.10.2/jquery.min.js"></script>
<script src="./js/cart.js?version=0.0.2"></script>
<script src="js/productAuto.js"></script>

</body>

</html>
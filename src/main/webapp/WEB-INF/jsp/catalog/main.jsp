<%@ include file="../common/top.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
        <c:if test="${sessionScope.loginAccount != null}">
            Welcome${sessionScope.loginAccount.username}!!
        </c:if>
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif"/></a><br />
            Saltwater, Freshwater <br />
            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a><br />
            Various Breeds <br />
            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" /></a><br />
            Various Breeds, Exotic Varieties <br />
            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a><br />
            Lizards, Turtles, Snakes <br />
            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a><br />
            Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap" id="mainImg">
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" id="BIRDS"/>
                <area alt="Fish" coords="2,180,72,250"
                      href="categoryForm?categoryId=FISH" shape="RECT" id="FISH"/>
                <area alt="Dogs" coords="60,250,130,320"
                      href="categoryForm?categoryId=DOGS" shape="RECT" id="DOGS"/>
                <area alt="Reptiles" coords="140,270,210,340"
                      href="categoryForm?categoryId=REPTILES" shape="RECT" id="REPTILES"/>
                <area alt="Cats" coords="225,240,295,310"
                      href="categoryForm?categoryId=CATS" shape="RECT" id="CATS"/>
                <area alt="Birds" coords="280,180,350,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" id="BIRDS2"/>
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" />
        </div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<script src="js/productDesc.js"></script>

<%@ include file="../common/bottom.jsp"%>


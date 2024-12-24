<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib uri="http://jakarta.apache.org/taglibs/standard/scriptfree" prefix="c"%>--%>
<%--<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>JPetStore Demo</title>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>

    <div id="Head">
        <div id="Logo">
            <div id="LogoContent">
                <a href="mainForm"><img src="images/logo-topbar.gif" /></a>
            </div>
        </div>
        <div id="Menu">
            <div id="MenuContents">
                <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
                <img align="middle" src="images/separator.gif" />
                <c:if test="${sessionScope.loginAccount == null}">
                    <a href="signOnForm">Sign In</a>
                    <img align="middle" src="images/separator.gif" />
                </c:if>

                <c:if test="${sessionScope.loginAccount != null}">
                    <a href="signOut">Sign Out</a>
                    <img align="middle" src="images/separator.gif" />
                    <a href="editAccountForm">My Account</a>
                    <img align="middle" src="images/separator.gif" />
                </c:if>
                <a href="help.html">?</a>
            </div>
        </div>

        <div id="Search">
            <div id="SearchContent">
                <form action="SubmitDataForm" method="post">
                    <input type="text" name="keyword" size="14" id="keyword">
                    <input type="submit" value="Search">
                </form>
                <div id="productAutoComplete">
                    <ul id="productAutoList">
                        <li class="productAutoItem">Labrador Retriever</li>
                        <li class="productAutoItem">Golden Retriever</li>
                        <li class="productAutoItem">Amazon Parrot</li>
                        <li class="productAutoItem">Rattlesnake</li>
                        <li class="productAutoItem">Tiger Shark</li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="QuickLinks">
                <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif"></a>
                <a href="#"><img src="images/separator.gif" /></a>
                <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
                <a href="#"><img src="images/separator.gif" /></a>
                <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
                <a href="#"><img src="images/separator.gif" /></a>
                <a href="categoryForm?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
                <a href="#"><img src="images/separator.gif" /></a>
                <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
            </div>
    </div>

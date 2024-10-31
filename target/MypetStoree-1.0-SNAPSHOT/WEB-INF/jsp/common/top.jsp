<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<a href="#"></a>
<!doctype html>

<html lang="en">

<head>

    <meta charset="UTF-8">
    <title>JPetStore Demo</title>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen"/>

</head>
<body>
<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="#"><img src="images/logo-topbar.gif" /></a>
    </div>
        <div id="Menu">
            <div id="MenuContent">
                <a href="#"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
                <img align="middle" src="images/separator.gif" />

                <c:if
                    test="${sessionScope.accountBean == null}">
                    <a href="#">Sign In</a>
            </c:if> <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${!sessionScope.accountBean.authenticated}">
                    <a href="#">Sign In</a>
                </c:if>
            </c:if> <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${sessionScope.accountBean.authenticated}">
                    <a href="#">Sign Out</a>
                    <img align="middle" src="images/separator.gif" />
                    <a href="#">My Account</a>
                </c:if>
            </c:if> <img align="middle" src="images/separator.gif" /> <a
                    href="help.html">?</a></div>
        </div>

        <div id="Search">
            <div id="SearchContent">
            </div>
        </div>

        <div id="QuickLinks">
            <a href="#"><img src="images/sm_fish.gif" /></a>
            <a href="#"><img src="images/sm_dogs.gif" /></a>
            <a href="#"><img src="images/sm_reptiles.gif" /></a>
            <a href="#"><img src="images/separator.gif" /></a>
            <a href="#"><img src="images/sm_cats.gif" /></a>
            <a href="#"><img src="images/separator.gif" /></a>
            <a href="#"><img src="images/sm_birds.gif" /></a>

        </div>
    </div>
    <div id="Content">
    </div>
</div>

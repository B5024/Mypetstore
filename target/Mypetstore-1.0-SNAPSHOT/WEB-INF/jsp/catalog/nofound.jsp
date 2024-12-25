<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <span>_____________________<img src="images/bird2.gif" style="align-content: center"></span>
        <p style="align-content: center">sorry!!! we don't found <span style="color: red">${sessionScope.keyword}</span>.
            search again or return to main menu</p>
</div>

<%@ include file="../common/bottom.jsp"%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="mailcode" method="post" id="form">
        <p>Please enter new email.</p>
        <div id="feedback" class="feedback"></div>
        <p>
            Email:<input type="text" name="email" id="email">
        </p>
        <input type="submit" name="mailCode" value="Sent MailCode">
    </form>
    <p>
        Code:<input type="text" name="code" id="code">
    </p>
    <button type="button" name="mailCode" id="confirm">Confirm</button>
</div>

<script src="js/check-changeMail.js"></script>

<%@ include file="../common/bottom.jsp"%>

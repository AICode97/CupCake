<%-- 
    Document   : ChangePassword
    Created on : 04-03-2019, 13:04:12
    Author     : William Sehested Huusfeldt
--%>

<%@include file = "header.jsp" %>

<h1>Change your password:</h1>

<form action="${pageContext.request.contextPath}/CommandController">
    <input type="hidden" name="command" value="changePassword">
    <div class="form-group">
        <label>Current password</label>
        <input type="text" class="form-control" name="currentPassword" placeholder="Current password...">
    </div>
    <div class="form-group">
        <label>New password</label>
        <input type="password" class="form-control" name="newPassword" placeholder="New password...">
    </div>
    <div class="form-group">
        <label>New password</label>
        <input type="password" class="form-control" name="newPassword2" placeholder="Type your new password again...">
    </div
    <button type="submit" class="btn btn-primary">Change password</button>
</form>

<%@include file = "footer.jsp" %>
